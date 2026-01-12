package com.iqexception.fxhelper.shop.service;


import com.iqexception.fxhelper.api.shop.*;

public interface ShopService {


    GetUserShopListResponse getUserShopList(GetUserShopListRequest request);

    GetShopListResponse getShopList(GetShopListRequest request);

    GetSerialNoResponse getSerialNo(GetSerialNoRequest request);

    GetShopResponse getShop(GetShopRequest request);
}
