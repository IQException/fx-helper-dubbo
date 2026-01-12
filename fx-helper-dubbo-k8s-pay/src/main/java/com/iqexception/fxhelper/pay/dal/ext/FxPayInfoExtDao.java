package com.iqexception.fxhelper.pay.dal.ext;

import com.iqexception.fxhelper.pay.dal.generator.tables.daos.FxPayInfoDao;
import com.iqexception.fxhelper.pay.dal.generator.tables.pojos.FxPayInfo;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class FxPayInfoExtDao extends FxPayInfoDao {

    public FxPayInfoExtDao(Configuration configuration) {
        super(configuration);
    }

    public FxPayInfo fetchOneByBizId(String value) {
        return fetchOne(com.iqexception.fxhelper.pay.dal.generator.tables.FxPayInfo.FX_PAY_INFO.BIZ_ID, value);
    }
/*
    public int update(Long id, String fromTrxId, String status) {
        return ctx().update(getTable())
                .set(FX_PAY_INFO.FROM_TRX_ID, fromTrxId)
                .where(FX_PAY_INFO.ID.eq(id))
                .execute();

    }*/
}
