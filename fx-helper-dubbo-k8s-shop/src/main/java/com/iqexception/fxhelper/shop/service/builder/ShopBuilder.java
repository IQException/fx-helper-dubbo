package com.iqexception.fxhelper.shop.service.builder;


import com.iqexception.fxhelper.api.shop.*;
import com.iqexception.fxhelper.common.util.PbUtil;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxShop;

import java.util.List;
import java.util.stream.Collectors;

public class ShopBuilder {
    public static GetUserShopListResponse buildGetUserShopListResponse(List<FxShop> shops) {


        return GetUserShopListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetUserShopListResult(shops))
                .build();

    }

    private static GetUserShopListResult buildGetUserShopListResult(List<FxShop> shops) {
        return GetUserShopListResult.newBuilder()
                .addAllShopInfos(shops.stream()
                        .map(ShopBuilder::buildShopInfo)
                        .collect(Collectors.toList()))
                .build();
    }

    private static ShopInfo buildShopInfo(FxShop fxShop) {

        return ShopInfo.newBuilder()
                .setShopId(fxShop.getShopId())
                .setOwnerUserId(fxShop.getOwnerUserId())
                .setShopName(fxShop.getShopName())
                .setLogo(fxShop.getLogo())
                .setIntro(fxShop.getIntro())
                .setAddress(fxShop.getAddress())
                .setPhone(fxShop.getPhone())
                .setCreatedAt(PbUtil.toTimestamp(fxShop.getCreatedAt()))
                .setUpdatedAt(PbUtil.toTimestamp(fxShop.getUpdatedAt()))
                .setStatus(fxShop.getStatus())
                .build();
    }

    public static GetShopListResponse buildGetShopListResponse(List<FxShop> shops) {

        return GetShopListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopListResult(shops))
                .build();
    }

    private static GetShopListResult buildGetShopListResult(List<FxShop> shops) {
        return GetShopListResult.newBuilder()
                .addAllShopInfos(shops.stream()
                        .map(ShopBuilder::buildShopInfo)
                        .collect(Collectors.toList()))
                .build();
    }

    public static GetShopResponse buildGetShopResponse(FxShop shop) {
        return GetShopResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopResult(shop))
                .build();
    }

    private static GetShopResult buildGetShopResult(FxShop shop) {
        return GetShopResult.newBuilder()
                .setShopId(shop.getShopId())
                .setOwnerUserId(shop.getOwnerUserId())
                .setShopName(shop.getShopName())
                .setLogo(shop.getLogo())
                .setIntro(shop.getIntro())
                .setAddress(shop.getAddress())
                .setPhone(shop.getPhone())
                .setStatus(shop.getStatus())
                .setCreatedAt(PbUtil.toTimestamp(shop.getCreatedAt()))
                .setUpdatedAt(PbUtil.toTimestamp(shop.getUpdatedAt()))
                .build();
    }
}
