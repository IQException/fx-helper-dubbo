package com.iqexception.fxhelper.order.dal.ext;

import com.alibaba.nacos.shaded.com.google.common.base.Preconditions;
import com.iqexception.fxhelper.order.dal.generator.tables.daos.FxOrderDao;
import com.iqexception.fxhelper.order.dal.generator.tables.pojos.FxOrder;
import com.iqexception.fxhelper.order.dal.generator.tables.records.FxOrderRecord;
import jakarta.annotation.Nullable;
import org.jooq.Configuration;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.iqexception.fxhelper.order.dal.generator.tables.FxOrder.FX_ORDER;
import static org.jooq.impl.DSL.sum;

@Repository
public class FxOrderExtDao extends FxOrderDao {

    public FxOrderExtDao(Configuration configuration) {
        super(configuration);
    }

    public List<FxOrder> fetch(@Nullable Long shopId,
                               @Nullable Long userId,
                               @Nullable Integer status,
                               @Nullable LocalDateTime from,
                               @Nullable LocalDateTime to,
                               @Nullable Integer offset,
                               @Nullable Integer limit) {

        Preconditions.checkArgument(shopId != null || userId != null, "shopId and userId can not be null at the same time!");

        //FIXME
        SelectConditionStep<FxOrderRecord> conditionStep = ctx().selectFrom(getTable()).where();

        if (shopId != null) {
            conditionStep = conditionStep.and(FX_ORDER.SHOP_ID.eq(shopId));
        }
        if (status != null) {
            conditionStep = conditionStep.and(FX_ORDER.STATUS.eq(status));
        }
        if (userId != null) {
            conditionStep = conditionStep.and(FX_ORDER.USER_ID.eq(userId));
        }
        if (from != null) {
            conditionStep = conditionStep.and(FX_ORDER.CREATED_AT.gt(from));
        }
        if (to != null) {
            conditionStep = conditionStep.and(FX_ORDER.CREATED_AT.lessOrEqual(to));
        }
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 20;
        }

        return conditionStep
                .orderBy(FX_ORDER.STATUS.asc(), FX_ORDER.UPDATED_AT.desc())
                .offset(offset)
                .limit(limit)
                .fetch(mapper());
    }

    public FxOrder fetchOne(Long shopId, String serialNo) {
        return ctx().selectFrom(getTable())
                .where(FX_ORDER.SHOP_ID.eq(shopId))
                .and(FX_ORDER.SERIAL_NO.eq(serialNo))
                .fetchOne(mapper());
    }

    public int updateStatus(Long orderId, int status) {

        return ctx().update(getTable())
                .set(FX_ORDER.STATUS, status)
                .where(FX_ORDER.ID.eq(orderId))
                .execute();
    }

    public int count(Long shopId, Integer status) {
        SelectConditionStep<FxOrderRecord> conditionStep = ctx().selectFrom(getTable())
                .where(FX_ORDER.SHOP_ID.eq(shopId));
        if (status != null) {
            conditionStep = conditionStep.and(FX_ORDER.STATUS.eq(status));
        }
        return ctx().fetchCount(conditionStep);


    }

    public BigDecimal totalAmount(Long shopId, Integer status) {
        SelectConditionStep<Record1<BigDecimal>> conditionStep = ctx().select(sum(FX_ORDER.AMOUNT))
                .from(getTable())
                .where(FX_ORDER.SHOP_ID.eq(shopId));
        if (status != null) {
            conditionStep = conditionStep.and(FX_ORDER.STATUS.eq(status));
        }
        return conditionStep.fetchOneInto(BigDecimal.class);

    }
}
