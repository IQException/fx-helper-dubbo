package com.iqexception.fxhelper.order.service.impl;

import com.iqexception.fxhelper.api.order.*;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.PbUtil;
import com.iqexception.fxhelper.order.dal.ext.FxOrderExtDao;
import com.iqexception.fxhelper.order.dal.generator.tables.pojos.FxOrder;
import com.iqexception.fxhelper.order.service.OrderService;
import com.iqexception.fxhelper.order.service.builder.OrderBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {


    private final FxOrderExtDao orderExtDao;


    public OrderServiceImpl(MessageSource messageSource,
                            JsonMapper jsonMapper,
                            FxOrderExtDao orderExtDao) {
        super(messageSource, jsonMapper);
        this.orderExtDao = orderExtDao;
    }

    @Override
    public GetOrderTotalAmountResponse getOrderTotalAmount(GetOrderTotalAmountRequest request) {
        BigDecimal amount = orderExtDao.totalAmount(request.getShopId(), request.getStatus());
        amount = amount == null ? BigDecimal.ZERO : amount;
        return GetOrderTotalAmountResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(GetOrderTotalAmountResult.newBuilder().setAmount(amount.setScale(1, RoundingMode.HALF_UP).toString()))
                .build();

    }

    @Override
    public GetOrderListResponse getOrderList(GetOrderListRequest request) {
        List<FxOrder> orders = orderExtDao.fetch(request.hasShopId() ? request.getShopId() : null,
                request.hasUserId() ? request.getUserId() : null,
                request.hasStatus() ? request.getStatus() : null,
                request.hasFrom() ? PbUtil.toLocalDateTime(request.getFrom()) : null,
                request.hasTo() ? PbUtil.toLocalDateTime(request.getTo()) : null,
                request.hasOffset() ? request.getOffset() : null,
                request.hasLimit() ? request.getLimit() : null);
        return OrderBuilder.buildGetOrderListResponse(orders);

    }

    @Override
    public GetOrderCountResponse getOrderCount(GetOrderCountRequest request) {
        int count = orderExtDao.count(request.getShopId(), request.getStatus());
        return GetOrderCountResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(GetOrderCountResult.newBuilder().setCount(count))
                .build();
    }
}
