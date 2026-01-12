package com.iqexception.fxhelper.misc.service.builder;

import com.iqexception.fxhelper.api.misc.SendWxMessageRequest;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.misc.dal.generator.tables.pojos.FxWxMessage;
import org.springframework.stereotype.Component;

@Component
public class WxMessageBuilder {
    private final JsonMapper jsonMapper;

    public WxMessageBuilder(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public FxWxMessage build(SendWxMessageRequest param) {

        FxWxMessage message = new FxWxMessage();

        message.setTemplateId(param.getTemplateId());
        message.setOpenId(param.getOpenId());
        message.setPage(param.getPage());
        message.setMsgBody(jsonMapper.serialize(param.getDataJsonString()));

        return message;
    }
}
