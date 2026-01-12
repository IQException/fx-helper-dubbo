package com.iqexception.fxhelper.shop.service.builder;


import com.iqexception.fxhelper.api.rest.shop.*;
import com.iqexception.fxhelper.common.util.PbUtil;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxShop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShopRestBuilder {
    public static FxShop build(Long userId, CreateParam param) {

        FxShop shop = new FxShop();
        shop.setOwnerUserId(userId);
        shop.setAddress(param.getAddress());
        shop.setShopName(param.getName());
        shop.setIntro(param.getIntro());
        shop.setLogo(param.getLogo());
        shop.setPhone(param.getPhone());

        return shop;
    }

    public static void build(FxShop shop, UpdateParam param) {
        shop.setLogo(param.getLogo());
        shop.setPhone(param.getPhone());
        shop.setShopName(param.getName());
        shop.setIntro(param.getIntro());
        shop.setAddress(param.getAddress());
        shop.setUpdatedAt(LocalDateTime.now());
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
                        .map(ShopRestBuilder::buildShopInfo)
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

    public static GetShopPublicInfoResponse buildGetShopPublicInfoResponse(FxShop shop) {

        return GetShopPublicInfoResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopPublicInfoResult(shop))
                .build();
    }

    private static GetShopPublicInfoResult buildGetShopPublicInfoResult(FxShop shop) {

        return GetShopPublicInfoResult.newBuilder()
                .setShopName(shop.getShopName())
                .setLogo(shop.getLogo())
                .setAddress(shop.getAddress())
                .setIntro(shop.getIntro())
                .build();
    }


    public static GetShopDetailInfoResponse buildGetShopDetailInfoResponse(FxShop shop) {

        return GetShopDetailInfoResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetShopDetailInfoResult(shop))
                .build();
    }

    private static GetShopDetailInfoResult buildGetShopDetailInfoResult(FxShop shop) {

        return GetShopDetailInfoResult.newBuilder()
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

    public static GetQrCodesResponse buildGetQrCodesResponse(List<String> qrCodes) {
        GetQrCodesResult result = GetQrCodesResult.newBuilder()
                .addAllQrCodes(qrCodes)
                .build();
        return GetQrCodesResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(result)
                .build();
    }
}
