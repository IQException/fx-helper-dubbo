package com.iqexception.fxhelper.misc.service;


import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.misc.MsgSubscribeRequest;
import com.iqexception.fxhelper.api.misc.SendWxMessageRequest;

public interface WxMsgService {
    BaseResponse sendMessage(SendWxMessageRequest request);

    BaseResponse msgSubscribe(MsgSubscribeRequest request);
}
