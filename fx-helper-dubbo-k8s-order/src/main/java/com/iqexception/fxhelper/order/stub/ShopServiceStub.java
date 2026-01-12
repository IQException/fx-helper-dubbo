package com.iqexception.fxhelper.order.stub;

import com.iqexception.fxhelper.api.shop.*;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShopServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(ShopServiceStub.class);

    @DubboReference
    private ShopService shopApi;

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
                LOGGER.error("get shop failed! shopId:{},response:{}", shopId, response);
            }
        } catch (Exception e) {
            LOGGER.error("get shop error! shopId:{}", shopId, e);
        }
        return null;
    }


    public String getSerialNoQuietly(Long shopId, String serialNo) {
        try {
            GetSerialNoResponse response = getSerialNo(shopId, serialNo);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getSerialNo();
            } else {
                LOGGER.error("get serialno failed! shopId:{},serialno:{},response:{}", shopId, serialNo, response);
            }
        } catch (Exception e) {
            LOGGER.error("get serialno failed! shopId:{},serialno:{}", shopId, serialNo, e);
        }
        return null;
    }

    public GetSerialNoResponse getSerialNo(Long shopId, String serialNo) {
        return shopApi.getSerialNo(
                GetSerialNoRequest.newBuilder()
                        .setShopId(shopId)
                        .setSerialNo(serialNo)
                        .build());
    }
}
