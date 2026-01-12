package com.iqexception.fxhelper.misc.service.impl;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.misc.MsgSubscribeRequest;
import com.iqexception.fxhelper.api.misc.SendWxMessageRequest;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.dto.wx.CommonResponse;
import com.iqexception.fxhelper.common.util.MessageUtil;
import com.iqexception.fxhelper.misc.constant.BizErrorCode;
import com.iqexception.fxhelper.misc.constant.WxMessageSendResult;
import com.iqexception.fxhelper.misc.dal.ext.FxWxMessageExtDao;
import com.iqexception.fxhelper.misc.dal.ext.FxWxMsgSubsExtDao;
import com.iqexception.fxhelper.misc.dal.generator.tables.pojos.FxWxMessage;
import com.iqexception.fxhelper.misc.dal.generator.tables.pojos.FxWxMsgSubs;
import com.iqexception.fxhelper.misc.service.WxApi;
import com.iqexception.fxhelper.misc.service.WxMsgService;
import com.iqexception.fxhelper.misc.service.builder.MsgSubsBuilder;
import com.iqexception.fxhelper.misc.service.builder.WxMessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class WxMsgServiceImpl extends BaseService implements WxMsgService {

    private final WxApi wxApi;

    private final FxWxMsgSubsExtDao wxMsgSubsExtDao;

    private final FxWxMessageExtDao wxMessageExtDao;

    private final WxMessageBuilder messageBuilder;

    public WxMsgServiceImpl(MessageSource messageSource,
                            JsonMapper jsonMapper,
                            StringRedisTemplate redisTemplate,
                            WxApi wxApi,
                            FxWxMsgSubsExtDao wxMsgSubsExtDao,
                            FxWxMessageExtDao wxMessageExtDao,
                            WxMessageBuilder messageBuilder) {
        super(messageSource, jsonMapper, redisTemplate);
        this.wxApi = wxApi;
        this.wxMsgSubsExtDao = wxMsgSubsExtDao;
        this.wxMessageExtDao = wxMessageExtDao;
        this.messageBuilder = messageBuilder;
    }

    @Override
    public BaseResponse sendMessage(SendWxMessageRequest request) {

        //TODO async

        FxWxMsgSubs msgSubs = wxMsgSubsExtDao.fetchByKey(
                request.getOpenId(),
                request.getTemplateId());
        if (msgSubs == null || msgSubs.getCount() <= 0) {
            LOG.warn(MessageUtil.message(BizErrorCode.MESSAGE_NOT_SUBSCRIBED, messageSource));
            return response(BizErrorCode.MESSAGE_NOT_SUBSCRIBED);
        }

        FxWxMessage message = messageBuilder.build(request);
        wxMessageExtDao.insert(message);

        CommonResponse response = wxApi.sendMessage(
                request.getTemplateId(),
                request.getOpenId(),
                request.getPage(),
                jsonMapper.deserialize(request.getDataJsonString(), Object.class));
        if (response.getErrorCode() == CommonResponse.SUCCESS) {
            wxMsgSubsExtDao.decrCount(msgSubs.getId());
            wxMessageExtDao.updateStatus(message.getId(), WxMessageSendResult.SUCCEED.getVal());
            return responseOk();
        } else {
            wxMessageExtDao.updateStatus(message.getId(), WxMessageSendResult.SUCCEED.getVal(), response.getErrorMessage());
            LOG.warn("send wx message failed！ response:{}", jsonMapper.serialize(response));
            return response(ErrorCode.REQUEST_ERROR);
        }
    }

    @Override
    public BaseResponse msgSubscribe(MsgSubscribeRequest request) {
        FxWxMsgSubs wxMsgSubs = wxMsgSubsExtDao.fetchByKey(TLVarManager.getOpenId(),
                request.getParam().getTemplateId());
        if (wxMsgSubs == null) {
            wxMsgSubs = MsgSubsBuilder.build(TLVarManager.getOpenId(),
                    request.getParam().getTemplateId());
            wxMsgSubsExtDao.insert(wxMsgSubs);
        } else {
            wxMsgSubsExtDao.incrCount(wxMsgSubs.getId());
        }
        return responseOk();
    }
}
