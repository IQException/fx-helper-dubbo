package com.iqexception.fxhelper.agg.service.impl;

import com.iqexception.fxhelper.agg.service.AggService;
import com.iqexception.fxhelper.agg.service.builder.AggBuilder;
import com.iqexception.fxhelper.agg.stub.AccountServiceStub;
import com.iqexception.fxhelper.agg.stub.OrderServiceStub;
import com.iqexception.fxhelper.agg.stub.ShopServiceStub;
import com.iqexception.fxhelper.agg.stub.UserServiceStub;
import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.api.order.OrderInfo;
import com.iqexception.fxhelper.api.rest.agg.*;
import com.iqexception.fxhelper.api.shop.ShopInfo;
import com.iqexception.fxhelper.api.user.UserInfo;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.constant.OrderStatus;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AggServiceImpl extends BaseService implements AggService {

    private final UserServiceStub userServiceStub;

    private final OrderServiceStub orderServiceStub;

    private final ShopServiceStub shopServiceStub;

    private final AccountServiceStub accountServiceStub;


    public AggServiceImpl(MessageSource messageSource,
                          JsonMapper jsonMapper,
                          UserServiceStub userServiceStub,
                          OrderServiceStub orderServiceStub,
                          ShopServiceStub shopServiceStub,
                          AccountServiceStub accountServiceStub) {
        super(messageSource, jsonMapper);
        this.userServiceStub = userServiceStub;
        this.orderServiceStub = orderServiceStub;
        this.shopServiceStub = shopServiceStub;
        this.accountServiceStub = accountServiceStub;
    }

    @Override
    public GetShopOrderListResponse getShopOrderList(GetShopOrderListRequest request) {
        com.iqexception.fxhelper.api.shop.GetShopResult shop = shopServiceStub.getShopQuietly(request.getParam().getShopId());
        if (shop == null || shop.getOwnerUserId() != TLVarManager.getUserId())
            return GetShopOrderListResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();

        GetShopOrderListParam param = request.getParam();
        List<OrderInfo> orderList = orderServiceStub.getOrderListQuietly(
                param.getShopId(),
                null,
                param.hasStatus() ? param.getStatus() : null ,
                param.hasFrom() ? param.getFrom() : null,
                param.hasTo() ? param.getTo() : null,
                param.hasOffset() ? param.getOffset() : null,
                param.hasLimit() ? param.getLimit() : null);

        List<Long> userIds = orderList.stream().map(OrderInfo::getUserId)
                .distinct().toList();

        if (!userIds.isEmpty()) {
            Map<Long, UserInfo> userMap = userServiceStub.getUserListQuietly(userIds)
                    .stream().collect(Collectors.toMap(UserInfo::getUserId, e -> e));
            return AggBuilder.buildGetShopOrderListResponse(orderList, userMap);

        }
        return GetShopOrderListResponse.newBuilder()
                .setStatus(statusOk())
                .build();
    }

    @Override
    public GetShopResponse getShop(GetShopRequest request) {
        com.iqexception.fxhelper.api.shop.GetShopResult shop = shopServiceStub.getShopQuietly(request.getParam().getShopId());

        Long userId = TLVarManager.getUserId();
        if (shop == null || userId != shop.getOwnerUserId())
            return GetShopResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();

        GetAccountResult account = accountServiceStub.getAccountQuietly(userId);

        int orderCount = orderServiceStub.getOrderCountQuietly(shop.getShopId(), OrderStatus.SUCCEED.getVal());

        BigDecimal orderTotalAmount = orderServiceStub.getOrderTotalAmountQuietly(shop.getShopId(), OrderStatus.SUCCEED.getVal());

        return AggBuilder.buildGetShopResponse(shop, account, orderCount, orderTotalAmount);
    }

    @Override
    public GetUserOrderListResponse getUserOrderList(GetUserOrderListRequest request) {

        GetUserOrderListParam param = request.getParam();
        List<OrderInfo> orderList = orderServiceStub.getOrderListQuietly(
                null,
                TLVarManager.getUserId(),
                param.hasStatus() ? param.getStatus() : null ,
                param.hasFrom() ? param.getFrom() : null,
                param.hasTo() ? param.getTo() : null,
                param.hasOffset() ? param.getOffset() : null,
                param.hasLimit() ? param.getLimit() : null);

        List<Long> shopIds = orderList.stream().map(OrderInfo::getShopId)
                .distinct().toList();

        if (!shopIds.isEmpty()) {
            Map<Long, ShopInfo> shopMap = shopServiceStub.getShopListQuietly(shopIds)
                    .stream().collect(Collectors.toMap(ShopInfo::getShopId, e -> e));
            return AggBuilder.buildGetUserOrderListResponse(orderList, shopMap);
        }

        return GetUserOrderListResponse.newBuilder()
                .setStatus(statusOk())
                .build();
    }
}
