package com.iqexception.fxhelper.agg.service.builder;

import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.api.order.OrderInfo;
import com.iqexception.fxhelper.api.rest.agg.*;
import com.iqexception.fxhelper.api.shop.ShopInfo;
import com.iqexception.fxhelper.api.user.UserInfo;
import com.iqexception.fxhelper.common.util.ResponseUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AggBuilder {
    public static GetShopOrderListResponse buildGetShopOrderListResponse(List<OrderInfo> orderList, Map<Long, UserInfo> userMap) {
        return GetShopOrderListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopOrderListResult(orderList, userMap))
                .build();
    }

    private static GetShopOrderListResult buildGetShopOrderListResult(List<OrderInfo> orderList, Map<Long, UserInfo> userMap) {
        return GetShopOrderListResult.newBuilder()
                .addAllOrderInfos(buildShopOrderList(orderList, userMap))
                .build();
    }

    private static List<ShopOrderInfo> buildShopOrderList(List<OrderInfo> orderList, Map<Long, UserInfo> userMap) {
        return orderList.stream().map(e ->
                {
                    ShopOrderInfo.Builder builder = ShopOrderInfo.newBuilder()
                            .setId(e.getOrderId())
                            .setShopId(e.getShopId())
                            .setStatus(e.getStatus())
                            .setAmount(e.getAmount())
                            .setCapture(e.getCapture())
                            .setCreatedAt(e.getCreatedAt())
                            .setUpdatedAt(e.getUpdatedAt())
                            .setUserId(e.getUserId())
                            .setNickName(userMap.get(e.getUserId()).getNickname())
                            .setAvatar(userMap.get(e.getUserId()).getAvatar());

                    if (e.hasFxTime()) {
                        builder.setFxTime(e.getFxTime());
                    }
                    if (e.hasFailMsg()) {
                        builder.setFailMsg(e.getFailMsg());
                    }
                    return builder.build();
                })
                .collect(Collectors.toList());
    }


    public static GetShopResponse buildGetShopResponse(com.iqexception.fxhelper.api.shop.GetShopResult shop,
                                                       GetAccountResult account, int orderCount, BigDecimal orderTotalAmount) {

        return GetShopResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopResult(shop, account, orderCount, orderTotalAmount))
                .build();
    }

    private static GetShopResult buildGetShopResult(com.iqexception.fxhelper.api.shop.GetShopResult shop,
                                                    GetAccountResult account, int orderCount, BigDecimal orderTotalAmount) {

        return GetShopResult.newBuilder()
                .setShopId(shop.getShopId())
                .setOwnerUserId(shop.getOwnerUserId())
                .setShopName(shop.getShopName())
                .setLogo(shop.getLogo())
                .setIntro(shop.getIntro())
                .setAddress(shop.getAddress())
                .setPhone(shop.getPhone())
                .setStatus(shop.getStatus())
                .setCreatedAt(shop.getCreatedAt())
                .setUpdatedAt(shop.getUpdatedAt())
                .setBalance(account.getBalance())
                .setOrderCount(orderCount)
                .setOrderTotalAmount(orderTotalAmount.setScale(1, RoundingMode.HALF_UP).toString())
                .build();

    }

    public static GetUserOrderListResponse buildGetUserOrderListResponse(List<OrderInfo> orderList, Map<Long, ShopInfo> shopMap) {
        return GetUserOrderListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetUserOrderListResult(orderList, shopMap))
                .build();
    }

    private static GetUserOrderListResult buildGetUserOrderListResult(List<OrderInfo> orderList, Map<Long, ShopInfo> shopMap) {
        return GetUserOrderListResult.newBuilder()
                .addAllOrderInfos(buildUserOrderList(orderList, shopMap))
                .build();
    }

    private static List<UserOrderInfo> buildUserOrderList(List<OrderInfo> orderList, Map<Long, ShopInfo> shopMap) {
        return orderList.stream().map(e ->
                {
                    UserOrderInfo.Builder builder = UserOrderInfo.newBuilder()
                            .setId(e.getOrderId())
                            .setShopId(e.getShopId())
                            .setStatus(e.getStatus())
                            .setAmount(e.getAmount())
                            .setCapture(e.getCapture())
                            .setCreatedAt(e.getCreatedAt())
                            .setUpdatedAt(e.getUpdatedAt())
                            .setUserId(e.getUserId())
                            .setShopLogo(shopMap.get(e.getShopId()).getLogo())
                            .setShopName(shopMap.get(e.getShopId()).getShopName());

                    if (e.hasFxTime()) {
                        builder.setFxTime(e.getFxTime());
                    }
                    if (e.hasFailMsg()) {
                        builder.setFailMsg(e.getFailMsg());
                    }
                    return builder.build();
                }).collect(Collectors.toList());
    }
}
