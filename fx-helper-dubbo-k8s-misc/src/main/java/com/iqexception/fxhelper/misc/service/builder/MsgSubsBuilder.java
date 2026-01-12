package com.iqexception.fxhelper.misc.service.builder;


import com.iqexception.fxhelper.misc.dal.generator.tables.pojos.FxWxMsgSubs;

public class MsgSubsBuilder {
    public static FxWxMsgSubs build(String openId, String templateId) {

        FxWxMsgSubs msgSubs = new FxWxMsgSubs();
        msgSubs.setCount(1);
        msgSubs.setOpenId(openId);
        msgSubs.setTemplateId(templateId);
        return msgSubs;
    }
}
