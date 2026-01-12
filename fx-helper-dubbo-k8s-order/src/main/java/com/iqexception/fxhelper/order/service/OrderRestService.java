package com.iqexception.fxhelper.order.service;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.order.CreateOrderRequest;
import com.iqexception.fxhelper.api.rest.order.CreateOrderResponse;
import com.iqexception.fxhelper.api.rest.order.PayOrderRequest;

public interface OrderRestService {
    CreateOrderResponse createOrder(CreateOrderRequest request);

    BaseResponse payOrder(PayOrderRequest request);
}
