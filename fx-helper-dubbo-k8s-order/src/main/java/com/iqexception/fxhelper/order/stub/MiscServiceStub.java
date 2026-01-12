package com.iqexception.fxhelper.order.stub;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.misc.MiscService;
import com.iqexception.fxhelper.api.misc.SendWxMessageRequest;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MiscServiceStub {


    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @DubboReference
    private MiscService miscApi;

    public BaseResponse sendMessage(String templateId, String openId, String page, String message) {
        return miscApi.sendWxMessage(SendWxMessageRequest.newBuilder()
                .setTemplateId(templateId)
                .setOpenId(openId)
                .setPage(page)
                .setDataJsonString(message)
                .build());
    }


    public boolean sendMessageQuietly(String templateId, String openId, String page, String message) {
        try {
            BaseResponse response = sendMessage(templateId, openId, page, message);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return true;
            } else {
                LOGGER.error("send message failed! templateId:{},openId:{},response:{}", templateId, openId, response);
            }
        } catch (Exception e) {
            LOGGER.error("send message error! templateId:{},openId:{}", templateId, openId, e);
        }
        return false;
    }
}
