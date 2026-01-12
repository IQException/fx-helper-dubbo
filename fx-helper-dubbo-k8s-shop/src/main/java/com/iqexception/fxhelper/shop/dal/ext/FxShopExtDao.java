package com.iqexception.fxhelper.shop.dal.ext;


import com.iqexception.fxhelper.shop.dal.generator.tables.daos.FxShopDao;
import com.iqexception.fxhelper.shop.dal.generator.tables.pojos.FxShop;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class FxShopExtDao extends FxShopDao {


    public FxShopExtDao(Configuration configuration) {
        super(configuration);
    }

    public FxShop fetchOne(String name, String address) {

        return ctx().selectFrom(getTable())
                .where(com.iqexception.fxhelper.shop.dal.generator.tables.FxShop.FX_SHOP.SHOP_NAME.eq(name))
                .and(com.iqexception.fxhelper.shop.dal.generator.tables.FxShop.FX_SHOP.ADDRESS.eq(address))
                .fetchOne(mapper());

    }

    public int updateStatus(Long shopId, Integer switchValue) {

        return ctx().update(getTable())
                .set(com.iqexception.fxhelper.shop.dal.generator.tables.FxShop.FX_SHOP.STATUS, switchValue)
                .where(com.iqexception.fxhelper.shop.dal.generator.tables.FxShop.FX_SHOP.SHOP_ID.eq(shopId))
                .execute();
    }
}
