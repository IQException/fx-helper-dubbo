package com.iqexception.fxhelper.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.common.ResponseStatus;
import com.iqexception.fxhelper.api.rest.shop.*;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.shop.constant.BizErrorCode;
import com.iqexception.fxhelper.shop.constant.WxConstants;
import com.iqexception.fxhelper.shop.dal.ext.FxSerialNoExtDao;
import com.iqexception.fxhelper.shop.dal.ext.FxShopExtDao;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxSerialNo;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxShop;
import com.iqexception.fxhelper.shop.service.ShopRestService;
import com.iqexception.fxhelper.shop.service.builder.ShopRestBuilder;
import com.iqexception.fxhelper.shop.stub.UserServiceStub;
import com.iqexception.fxhelper.shop.wx.WxApi;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopRestServiceImpl extends BaseService implements ShopRestService {

    private final WxApi wxApi;

    private final UserServiceStub userServiceStub;

    private final FxShopExtDao shopExtDao;

    private final FxSerialNoExtDao serialNoExtDao;

    public ShopRestServiceImpl(MessageSource messageSource,
                               JsonMapper jsonMapper,
                               StringRedisTemplate redisTemplate,
                               RmqHelper rmqHelper,
                               WxApi wxApi,
                               FxShopExtDao shopExtDao,
                               UserServiceStub userServiceStub,
                               FxSerialNoExtDao serialNoExtDao) {
        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.wxApi = wxApi;
        this.shopExtDao = shopExtDao;
        this.userServiceStub = userServiceStub;
        this.serialNoExtDao = serialNoExtDao;

    }

    @Override
    public CreateResponse create(CreateRequest request) {

        FxShop shop = ShopRestBuilder.build(TLVarManager.getUserId(), request.getParam());

        shopExtDao.insert(shop);
        // 支付密码暂放在用户域（支付密码这块设计得有点问题，因为支付密码得跟着用户走，应当在创建用户的时候设置（或第一次支付时）；先这么着吧）
        //FIXME 发消息
        userServiceStub.updatePaySecretQuietly(TLVarManager.getUserId(),
                request.getParam().getPaySecret());

        return CreateResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(CreateResult.newBuilder().setShopId(shop.getShopId()))
                .build();

    }

    @Override
    public BaseResponse update(UpdateRequest request) {

        FxShop shop = shopExtDao.fetchOneByShopId(request.getParam().getShopId());

        if (shop == null || !TLVarManager.getUserId().equals(shop.getOwnerUserId()))
            return response(ErrorCode.PARAM_ERROR);

        ShopRestBuilder.build(shop, request.getParam());
        shopExtDao.update(shop);

        return responseOk();
    }

    @Override
    public GetShopListResponse getShopList(BaseRequest request) {
        List<FxShop> shops = shopExtDao.fetchByOwnerUserId(TLVarManager.getUserId());

        return ShopRestBuilder.buildGetShopListResponse(shops);
    }

    @Override
    public GetShopPublicInfoResponse getShopPublicInfo(GetShopPublicInfoRequest request) {

        FxShop shop = shopExtDao.fetchOneByShopId(request.getParam().getShopId());

        if (shop == null)
            return GetShopPublicInfoResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();

        return ShopRestBuilder.buildGetShopPublicInfoResponse(shop);
    }

    @Override
    public GetShopDetailInfoResponse getShopDetailInfo(GetShopDetailInfoRequest request) {
        FxShop shop = shopExtDao.fetchOneByShopId(request.getParam().getShopId());
        if (shop == null) {
            return GetShopDetailInfoResponse.newBuilder()
                    .setStatus(status(BizErrorCode.SHOP_NOT_EXIST))
                    .build();
        }
        return ShopRestBuilder.buildGetShopDetailInfoResponse(shop);
    }

    @Override
    public GetQrCodesResponse getQrCodes(GetQrCodesRequest request) {
        ResponseStatus status = validateShopAndOwner(
                request.getParam().getShopId(),
                TLVarManager.getUserId());
        if (ResponseUtil.isFail(status))
            return GetQrCodesResponse.newBuilder()
                    .setStatus(status)
                    .build();
        List<FxSerialNo> serialNos = Lists.newArrayList();

        for (int i = 0; i < request.getParam().getNumber(); i++) {

            FxSerialNo serialNo = new FxSerialNo();
            serialNo.setShopId(request.getParam().getShopId());
            byte[] hashBytes = Hashing.crc32().hashBytes(
                    UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)).asBytes();
            serialNo.setSerialNo(new String(Base64.getEncoder().encode(hashBytes), StandardCharsets.UTF_8));
            serialNos.add(serialNo);
        }

        serialNoExtDao.insert(serialNos);

        List<String> qrCodes = wxApi.getQrCodes(WxConstants.PAGE_CONSUMER_LANDING,
                serialNos.stream().map(e -> request.getParam().getShopId() + "#" + e.getSerialNo())
                        .collect(Collectors.toList()));

        return ShopRestBuilder.buildGetQrCodesResponse(qrCodes);
    }

    @Override
    public BaseResponse switchFx(SwitchFxRequest request) {
        ResponseStatus status = validateShopAndOwner(
                request.getParam().getShopId(),
                TLVarManager.getUserId());
        if (ResponseUtil.isFail(status)) {
            return response(status);
        }

        shopExtDao.updateStatus(request.getParam().getShopId(),
                request.getParam().getSwitchValue());

        return responseOk();
    }

    private ResponseStatus validateShopAndOwner(Long shopId, Long userId) {

        FxShop shop = shopExtDao.fetchOneByShopId(shopId);
        if (shop == null || !shop.getOwnerUserId().equals(userId))
            return status(ErrorCode.PARAM_ERROR);

        return ResponseUtil.statusOk();
    }
}
