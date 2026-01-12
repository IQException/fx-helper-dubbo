package com.iqexception.fxhelper.shop;

import com.iqexception.fxhelper.api.shop.*;
import com.iqexception.fxhelper.shop.service.ShopService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FxHelperShopServiceImpl extends DubboShopServiceTriple.ShopServiceImplBase{

    private final ShopService shopService;

    public FxHelperShopServiceImpl(ShopService shopService) {
        this.shopService = shopService;
    }

    public GetUserShopListResponse getUserShopList(GetUserShopListRequest request) {
        return shopService.getUserShopList(request);
    }

    public GetShopListResponse getShopList(GetShopListRequest request) {
        return shopService.getShopList(request);
    }

    public GetSerialNoResponse getSerialNo(GetSerialNoRequest request) {
        return shopService.getSerialNo(request);
    }

    public GetShopResponse getShop(GetShopRequest request) {
        return shopService.getShop(request);
    }

}
