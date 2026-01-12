package com.iqexception.fxhelper.agg.stub;

import com.iqexception.fxhelper.api.shop.*;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ShopServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(ShopServiceStub.class);

    @DubboReference
    private ShopService shopApi;

    private final JsonMapper jsonMapper;

    public ShopServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public GetShopResponse getShop(Long shopId) {
        return shopApi.getShop(
                GetShopRequest.newBuilder()
                        .setShopId(shopId)
                        .build());
    }

    public GetShopResult getShopQuietly(Long shopId) {
        try {
            GetShopResponse response = getShop(shopId);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult();
            } else {
                LOGGER.error("get shop failed! shopId:{},response:{}", shopId, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("get shop error! shopId:{}", shopId, e);
        }
        return null;
    }

    public GetShopListResponse getShopList(List<Long> shopIds) {

        return shopApi.getShopList(
                GetShopListRequest.newBuilder()
                        .addAllShopIds(shopIds)
                        .build());
    }

    public List<ShopInfo> getShopListQuietly(List<Long> shopIds) {

        try {
            GetShopListResponse response = getShopList(shopIds);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getShopInfosList();
            } else {
                LOGGER.error("get shop list failed! response:{}", jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();

    }

}
