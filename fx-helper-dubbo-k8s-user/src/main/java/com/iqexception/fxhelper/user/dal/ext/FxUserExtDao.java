package com.iqexception.fxhelper.user.dal.ext;

import com.iqexception.fxhelper.user.dal.generator.tables.daos.FxUserDao;
import com.iqexception.fxhelper.user.service.builder.UserBuilder;
import com.iqexception.fxhelper.user.dal.generator.tables.FxUser;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class FxUserExtDao extends FxUserDao {
    public FxUserExtDao(Configuration configuration) {
        super(configuration);
    }

    public int updatePaySecret(Long userId, String secret) {

        return ctx().update(getTable())
                .set(FxUser.FX_USER.PAY_SECRET, secret)
                .where(FxUser.FX_USER.USER_ID.eq(userId))
                .execute();
    }
    public int updateSessionKey(Long userId, String sessionkey) {

        return ctx().update(getTable())
                .set(FxUser.FX_USER.SESSION_KEY,sessionkey)
                .where(FxUser.FX_USER.USER_ID.eq(userId))
                .execute();
    }
}
