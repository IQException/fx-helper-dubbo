package com.iqexception.fxhelper.agg.service;


import com.iqexception.fxhelper.api.rest.agg.*;

public interface AggService {

    GetShopOrderListResponse getShopOrderList(GetShopOrderListRequest request);

    GetShopResponse getShop(GetShopRequest request);

    GetUserOrderListResponse getUserOrderList(GetUserOrderListRequest request);
}
