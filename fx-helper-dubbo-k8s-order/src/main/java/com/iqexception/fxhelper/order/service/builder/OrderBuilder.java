package com.iqexception.fxhelper.order.service.builder;


import com.iqexception.fxhelper.api.order.GetOrderListResponse;
import com.iqexception.fxhelper.api.order.GetOrderListResult;
import com.iqexception.fxhelper.api.order.OrderInfo;
import com.iqexception.fxhelper.common.util.PbUtil;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.order.dal.generator.tables.pojos.FxOrder;

import java.math.RoundingMode;
import java.util.List;

public class OrderBuilder {

    public static GetOrderListResponse buildGetOrderListResponse(List<FxOrder> orders) {
        return GetOrderListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetOrderListResult(orders))
                .build();
    }

    private static GetOrderListResult buildGetOrderListResult(List<FxOrder> orders) {
        return GetOrderListResult.newBuilder()
                .addAllOrderInfos(orders.stream().map(OrderBuilder::buildOrderInfo).collect(java.util.stream.Collectors.toList()))
                .build();
    }

    private static OrderInfo buildOrderInfo(FxOrder fxOrder) {
        OrderInfo.Builder builder = OrderInfo.newBuilder()
                .setOrderId(fxOrder.getId())
                .setShopId(fxOrder.getShopId())
                .setUserId(fxOrder.getUserId())
                .setCapture(fxOrder.getCapture())
                .setAmount(fxOrder.getAmount().setScale(1, RoundingMode.HALF_UP).toString())
                .setStatus(fxOrder.getStatus())
                .setCreatedAt(PbUtil.toTimestamp(fxOrder.getCreatedAt()))
                .setUpdatedAt(PbUtil.toTimestamp(fxOrder.getUpdatedAt()));
        if (fxOrder.getFxTime() != null) {
            builder.setFxTime(PbUtil.toTimestamp(fxOrder.getFxTime()));
        }
        if (fxOrder.getFailMsg() != null) {
            builder.setFailMsg(fxOrder.getFailMsg());
        }
        return builder.build();

    }
}
