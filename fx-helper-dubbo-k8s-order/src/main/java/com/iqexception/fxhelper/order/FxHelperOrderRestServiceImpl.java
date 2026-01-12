package com.iqexception.fxhelper.order;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.order.CreateOrderRequest;
import com.iqexception.fxhelper.api.rest.order.CreateOrderResponse;
import com.iqexception.fxhelper.api.rest.order.DubboOrderRestServiceTriple;
import com.iqexception.fxhelper.api.rest.order.PayOrderRequest;
import com.iqexception.fxhelper.order.service.OrderRestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperOrderRestServiceImpl extends DubboOrderRestServiceTriple.OrderRestServiceImplBase {

    private final OrderRestService orderRestService;

    public FxHelperOrderRestServiceImpl(OrderRestService orderRestService) {
        this.orderRestService = orderRestService;
    }
    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderRestService.createOrder(request);
    }
    @PostMapping("/pay")
    public BaseResponse payOrder(@RequestBody PayOrderRequest request) {
        return orderRestService.payOrder(request);
    }
}
