package com.iqexception.fxhelper.order;

import com.iqexception.fxhelper.api.order.*;
import com.iqexception.fxhelper.order.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FxHelperOrderServiceImpl extends DubboOrderServiceTriple.OrderServiceImplBase {

    private final OrderService orderService;

    public FxHelperOrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    public GetOrderTotalAmountResponse getOrderTotalAmount(GetOrderTotalAmountRequest request) {
        return orderService.getOrderTotalAmount(request);
    }

    public GetOrderListResponse getOrderList(GetOrderListRequest request) {
        return orderService.getOrderList(request);
    }

    public GetOrderCountResponse getOrderCount(GetOrderCountRequest request) {
        return orderService.getOrderCount(request);
    }
}
