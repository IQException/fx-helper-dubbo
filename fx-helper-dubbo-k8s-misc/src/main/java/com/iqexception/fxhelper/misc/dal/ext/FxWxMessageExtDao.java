package com.iqexception.fxhelper.misc.dal.ext;

import com.iqexception.fxhelper.misc.dal.generator.tables.daos.FxWxMessageDao;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

import static com.iqexception.fxhelper.misc.dal.generator.tables.FxWxMessage.FX_WX_MESSAGE;

@Repository
public class FxWxMessageExtDao extends FxWxMessageDao {

    public FxWxMessageExtDao(Configuration configuration) {
        super(configuration);
    }

    public int updateStatus(Long id, int status, String errorMessage) {
        return ctx().update(getTable())
                .set(FX_WX_MESSAGE.STATUS, status)
                .set(FX_WX_MESSAGE.FAIL_MSG, errorMessage)
                .where(FX_WX_MESSAGE.ID.eq(id))
                .execute();

    }

    public int updateStatus(Long id, int status) {
        return updateStatus(id, status, null);
    }
}
