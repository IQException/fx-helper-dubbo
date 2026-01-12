package com.iqexception.fxhelper.shop.service;

import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.shop.*;

public interface ShopRestService {
    CreateResponse create(CreateRequest request);

    BaseResponse update(UpdateRequest request);

    GetShopListResponse getShopList(BaseRequest request);

    GetShopPublicInfoResponse getShopPublicInfo(GetShopPublicInfoRequest request);

    GetShopDetailInfoResponse getShopDetailInfo(GetShopDetailInfoRequest request);

    GetQrCodesResponse getQrCodes(GetQrCodesRequest request);

    BaseResponse switchFx(SwitchFxRequest request);
}
