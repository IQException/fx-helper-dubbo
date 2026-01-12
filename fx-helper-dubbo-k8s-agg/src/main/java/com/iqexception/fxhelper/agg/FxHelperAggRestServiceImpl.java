package com.iqexception.fxhelper.agg;

import com.iqexception.fxhelper.agg.service.AggService;
import com.iqexception.fxhelper.api.rest.agg.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperAggRestServiceImpl extends DubboAggRestServiceTriple.AggRestServiceImplBase {

    private final AggService service;

    public FxHelperAggRestServiceImpl(AggService service) {
        this.service = service;
    }
    @PostMapping("/get_shop")
    public GetShopResponse getShop(@RequestBody GetShopRequest request) {
        return service.getShop(request);
    }
    @PostMapping("/list_shop_orders")
    public GetShopOrderListResponse getShopOrderList(@RequestBody GetShopOrderListRequest request) {
        return service.getShopOrderList(request);
    }
    @PostMapping("list_user_orders")
    public GetUserOrderListResponse getUserOrderList(@RequestBody GetUserOrderListRequest request) {
        return service.getUserOrderList(request);
    }
}
