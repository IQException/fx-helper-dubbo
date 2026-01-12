package com.iqexception.fxhelper.shop.service.impl;

import com.iqexception.fxhelper.api.shop.*;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.shop.constant.BizErrorCode;
import com.iqexception.fxhelper.shop.dal.ext.FxSerialNoExtDao;
import com.iqexception.fxhelper.shop.dal.ext.FxShopExtDao;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxSerialNo;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxShop;
import com.iqexception.fxhelper.shop.service.ShopService;
import com.iqexception.fxhelper.shop.service.builder.ShopBuilder;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl extends BaseService implements ShopService {


    private final FxShopExtDao shopExtDao;

    private final FxSerialNoExtDao serialNoExtDao;

    public ShopServiceImpl(MessageSource messageSource,
                           JsonMapper jsonMapper,
                           StringRedisTemplate redisTemplate,
                           RmqHelper rmqHelper,
                           FxShopExtDao shopExtDao,
                           FxSerialNoExtDao serialNoExtDao) {
        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.shopExtDao = shopExtDao;

        this.serialNoExtDao = serialNoExtDao;

    }

    @Override
    public GetUserShopListResponse getUserShopList(GetUserShopListRequest request) {
        List<FxShop> shops = shopExtDao.fetchByOwnerUserId(request.getUserId());

        return ShopBuilder.buildGetUserShopListResponse(shops);
    }

    @Override
    public GetShopListResponse getShopList(GetShopListRequest request) {

        List<FxShop> shops = shopExtDao.fetchByShopId(request.getShopIdsList().toArray(new Long[0]));

        return ShopBuilder.buildGetShopListResponse(shops);

    }

    @Override
    public GetSerialNoResponse getSerialNo(GetSerialNoRequest request) {
        FxSerialNo serialNo = serialNoExtDao.fetchOne(request.getShopId(), request.getSerialNo());
        if (serialNo == null)
            return GetSerialNoResponse.newBuilder()
                    .setStatus(status(BizErrorCode.SERIAL_NO_NOT_EXIST))
                    .build();
        return GetSerialNoResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(GetSerialNoResult.newBuilder().setSerialNo(serialNo.getSerialNo()))
                .build();
    }

    @Override
    public GetShopResponse getShop(GetShopRequest request) {
        FxShop shop = shopExtDao.fetchOneByShopId(request.getShopId());
        if (shop == null) {
            return GetShopResponse.newBuilder()
                    .setStatus(status(BizErrorCode.SHOP_NOT_EXIST))
                    .build();
        }
        return ShopBuilder.buildGetShopResponse(shop);
    }


}
