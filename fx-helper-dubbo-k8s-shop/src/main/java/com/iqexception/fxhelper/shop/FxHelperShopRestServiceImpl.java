package com.iqexception.fxhelper.shop;

import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.shop.*;
import com.iqexception.fxhelper.shop.service.ShopRestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperShopRestServiceImpl extends DubboShopRestServiceTriple.ShopRestServiceImplBase {

    private final ShopRestService shopRestService;

    public FxHelperShopRestServiceImpl(ShopRestService shopRestService) {
        this.shopRestService = shopRestService;
    }
    @PostMapping("/create")
    public CreateResponse create(@RequestBody CreateRequest request) {
        return shopRestService.create(request);
    }
    @PostMapping("/update")
    public BaseResponse update(@RequestBody UpdateRequest request) {
        return shopRestService.update(request);
    }
    @PostMapping("/get_shop_list")
    public GetShopListResponse getShopList(@RequestBody BaseRequest request) {
        return shopRestService.getShopList(request);
    }
    @PostMapping("/get_public_info")
    public GetShopPublicInfoResponse getShopPublicInfo(@RequestBody GetShopPublicInfoRequest request) {
        return shopRestService.getShopPublicInfo(request);
    }
    @PostMapping("/get_detail_info")
    public GetShopDetailInfoResponse getShopDetailInfo(@RequestBody GetShopDetailInfoRequest request) {
        return shopRestService.getShopDetailInfo(request);
    }
    @PostMapping("/get_qr_codes")
    public GetQrCodesResponse getQrCodes(@RequestBody GetQrCodesRequest request) {
        return shopRestService.getQrCodes(request);
    }
    @PostMapping("/switch")
    public BaseResponse switchFx(@RequestBody SwitchFxRequest request) {
        return shopRestService.switchFx(request);
    }
}
