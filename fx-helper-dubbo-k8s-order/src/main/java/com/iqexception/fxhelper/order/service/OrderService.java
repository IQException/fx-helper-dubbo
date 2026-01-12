package com.iqexception.fxhelper.order.service;

import com.iqexception.fxhelper.api.order.*;

public interface OrderService {

    GetOrderTotalAmountResponse getOrderTotalAmount(GetOrderTotalAmountRequest request);

    GetOrderListResponse getOrderList(GetOrderListRequest request);

    GetOrderCountResponse getOrderCount(GetOrderCountRequest request);
}
