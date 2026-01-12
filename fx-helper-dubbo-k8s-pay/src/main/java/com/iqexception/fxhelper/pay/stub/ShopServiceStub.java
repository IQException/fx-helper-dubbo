package com.iqexception.fxhelper.pay.stub;

import com.iqexception.fxhelper.api.shop.GetUserShopListRequest;
import com.iqexception.fxhelper.api.shop.GetUserShopListResponse;
import com.iqexception.fxhelper.api.shop.ShopInfo;
import com.iqexception.fxhelper.api.shop.ShopService;
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

    public GetUserShopListResponse getShopList(Long userId) {

        return shopApi.getUserShopList(
                GetUserShopListRequest.newBuilder()
                        .setUserId(userId)
                        .build());

    }

    public List<ShopInfo> getShopListQuietly(Long userId) {

        try {
            GetUserShopListResponse response = getShopList(userId);
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
