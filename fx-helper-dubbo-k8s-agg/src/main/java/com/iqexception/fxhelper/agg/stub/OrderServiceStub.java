package com.iqexception.fxhelper.agg.stub;

import com.google.protobuf.Timestamp;
import com.iqexception.fxhelper.api.order.*;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
public class OrderServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceStub.class);

    private final JsonMapper jsonMapper;

    @DubboReference
    private OrderService orderApi;

    public OrderServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }


    public GetOrderListResponse getOrderList(Long shopId, Long userId, Integer status,
                                             Timestamp from, Timestamp to,
                                             Integer offset, Integer limit) {
        GetOrderListRequest.Builder builder = GetOrderListRequest.newBuilder();
        if (shopId != null) {
            builder.setShopId(shopId);
        }
        if (userId != null) {
            builder.setUserId(userId);
        }
        if (status != null) {
            builder.setStatus(status);
        }
        if (from != null) {
            builder.setFrom(from);
        }
        if (to != null) {
            builder.setTo(to);
        }
        builder.setOffset(offset == null ? 0 : offset);
        builder.setLimit(limit == null ? 20 : limit);


        return orderApi.getOrderList(builder.build());
    }

    public List<OrderInfo> getOrderListQuietly(Long shopId, Long userId, Integer status,
                                               Timestamp from, Timestamp to,
                                               Integer offset, Integer limit) {
        try {
            GetOrderListResponse response = getOrderList(shopId, userId, status, from, to, offset, limit);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getOrderInfosList();
            } else {
                LOGGER.error("get order list failed! shopId:{},userId:{},status:{},from:{},to:{},offset:{},limit:{},response:{}",
                        shopId, userId, status, from, to, offset, limit, jsonMapper.serialize(response));
            }
        } catch (RestClientException e) {
            LOGGER.error("get order list failed! shopId:{},userId:{},status:{},from:{},to:{},offset:{},limit:{}",
                    shopId, userId, status, from, to, offset, limit, e);
        }
        return Collections.emptyList();
    }

    public GetOrderCountResponse getOrderCount(Long shopId, int status) {

        return orderApi.getOrderCount(
                GetOrderCountRequest.newBuilder()
                        .setShopId(shopId)
                        .setStatus(status)
                        .build());
    }

    public int getOrderCountQuietly(Long shopId, int status) {

        try {
            GetOrderCountResponse response = getOrderCount(shopId, status);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getCount();
            } else {
                LOGGER.error("get order count failed! shopId:{},status:{},response:{}",
                        shopId, status, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("get order count failed! shopId:{},status:{}", shopId, status);
        }

        return 0;
    }

    public GetOrderTotalAmountResponse getOrderTotalAmount(Long shopId, int status) {
        return orderApi.getOrderTotalAmount(
                GetOrderTotalAmountRequest.newBuilder()
                        .setShopId(shopId)
                        .setStatus(status)
                        .build());
    }

    public BigDecimal getOrderTotalAmountQuietly(Long shopId, int status) {

        try {
            GetOrderTotalAmountResponse response = getOrderTotalAmount(shopId, status);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return new BigDecimal(response.getResult().getAmount());
            } else {
                LOGGER.error("get order total amount failed! shopId:{},status:{},response:{}",
                        shopId, status, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("get order total amount failed! shopId:{},status:{}", shopId, status);
        }
        return BigDecimal.ZERO;
    }
}
