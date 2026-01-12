package com.iqexception.fxhelper.order.service.impl;

import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.order.CreateOrderRequest;
import com.iqexception.fxhelper.api.rest.order.CreateOrderResponse;
import com.iqexception.fxhelper.api.rest.order.CreateOrderResult;
import com.iqexception.fxhelper.api.rest.order.PayOrderRequest;
import com.iqexception.fxhelper.api.shop.GetShopResult;
import com.iqexception.fxhelper.api.user.GetUserResult;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.constant.OrderStatus;
import com.iqexception.fxhelper.common.constant.ShopStatus;
import com.iqexception.fxhelper.order.constant.BizErrorCode;
import com.iqexception.fxhelper.order.dal.ext.FxOrderExtDao;
import com.iqexception.fxhelper.order.dal.generator.tables.pojos.FxOrder;
import com.iqexception.fxhelper.order.service.OrderRestService;
import com.iqexception.fxhelper.order.service.builder.OrderRestBuilder;
import com.iqexception.fxhelper.order.stub.AccountServiceStub;
import com.iqexception.fxhelper.order.stub.PayServiceStub;
import com.iqexception.fxhelper.order.stub.ShopServiceStub;
import com.iqexception.fxhelper.order.stub.UserServiceStub;
import com.iqexception.fxhelper.order.wx.WxMsgService;
import org.jooq.tools.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderRestServiceImpl extends BaseService implements OrderRestService {


    private final FxOrderExtDao orderExtDao;

    private final PayServiceStub payServiceStub;

    private final AccountServiceStub accountServiceStub;

    private final UserServiceStub userServiceStub;

    private final ShopServiceStub shopServiceStub;

    private final WxMsgService wxMsgService;

    public OrderRestServiceImpl(MessageSource messageSource,
                                JsonMapper jsonMapper,
                                FxOrderExtDao orderExtDao,
                                PayServiceStub payServiceStub,
                                AccountServiceStub accountServiceStub,
                                UserServiceStub userServiceStub,
                                ShopServiceStub shopServiceStub,
                                WxMsgService wxMsgService) {
        super(messageSource, jsonMapper);
        this.orderExtDao = orderExtDao;
        this.payServiceStub = payServiceStub;
        this.accountServiceStub = accountServiceStub;
        this.userServiceStub = userServiceStub;
        this.shopServiceStub = shopServiceStub;
        this.wxMsgService = wxMsgService;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        GetShopResult shop = shopServiceStub.getShopQuietly(request.getParam().getShopId());
        if (shop == null)
            return CreateOrderResponse.newBuilder()
                    .setStatus(status(ErrorCode.REQUEST_ERROR))
                    .build();
        if (shop.getStatus() == ShopStatus.CLOSED.getVal())
            return CreateOrderResponse.newBuilder()
                    .setStatus(status(BizErrorCode.SHOP_CLOSED))
                    .build();

        String serialNo = shopServiceStub.getSerialNoQuietly(request.getParam().getShopId(), request.getParam().getSerialNo());
        if (StringUtils.isBlank(serialNo))
            return CreateOrderResponse.newBuilder()
                    .setStatus(status(BizErrorCode.ORDER_NOT_EXIST))
                    .build();

        FxOrder order = orderExtDao.fetchOne(request.getParam().getShopId(), request.getParam().getSerialNo());
        if (order != null)
            return CreateOrderResponse.newBuilder()
                    .setStatus(status(BizErrorCode.ORDER_DUPLICATE))
                    .build();

        order = OrderRestBuilder.buildOrder(request.getParam(), TLVarManager.getUserId());

        orderExtDao.insert(order);

        return CreateOrderResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(CreateOrderResult.newBuilder().setOrderId(order.getId()))
                .build();

    }

    @Override
    public BaseResponse payOrder(PayOrderRequest request) {
        FxOrder order = orderExtDao.fetchOneById(request.getParam().getOrderId());
        if (order == null)
            return response(ErrorCode.PARAM_ERROR);

        GetShopResult shop = shopServiceStub.getShopQuietly(order.getShopId());
        if (shop == null) {
            return response(ErrorCode.REQUEST_ERROR);
        }
        if (shop.getStatus() == ShopStatus.CLOSED.getVal())
            return response(BizErrorCode.SHOP_CLOSED);

        GetUserResult consumer = userServiceStub.getUserQuietly(order.getUserId());

        GetAccountResult shopOwner = accountServiceStub.getAccountQuietly(TLVarManager.getUserId());
        if (shopOwner == null) {
            return response(ErrorCode.REQUEST_ERROR);
        }

        payServiceStub.acct2wxQuietly(shopOwner.getAccountId(), consumer.getOpenId(), order.getAmount());
        //FIXME 暂时默认都成功
        //TODO 分布式事务
        orderExtDao.updateStatus(order.getId(), OrderStatus.SUCCEED.getVal());
        wxMsgService.sendReceiptMessage(
                shop.getShopName(),
                consumer.getOpenId(), order.getAmount(),
                order.getId(), LocalDateTime.now());

        return responseOk();
    }
}
