package com.iqexception.fxhelper.misc;

import com.google.protobuf.Empty;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.misc.DubboMiscServiceTriple;
import com.iqexception.fxhelper.api.misc.GetAccessTokenResponse;
import com.iqexception.fxhelper.api.misc.GetAccessTokenResult;
import com.iqexception.fxhelper.api.misc.SendWxMessageRequest;
import com.iqexception.fxhelper.misc.service.WxApi;
import com.iqexception.fxhelper.misc.service.WxMsgService;
import org.apache.dubbo.config.annotation.DubboService;

import static com.iqexception.fxhelper.common.util.ResponseUtil.statusOk;

@DubboService
public class FxHelperMiscService extends DubboMiscServiceTriple.MiscServiceImplBase {

    private final WxApi wxApi;

    private final WxMsgService wxMsgService;

    public FxHelperMiscService(WxApi wxApi, WxMsgService wxMsgService) {
        this.wxApi = wxApi;
        this.wxMsgService = wxMsgService;
    }

    public GetAccessTokenResponse getAccessToken(Empty request) {

        return GetAccessTokenResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(GetAccessTokenResult.newBuilder()
                        .setAccessToken(wxApi.getAccessToken()))
                .build();
    }

    // TODO: async_send_message
    public BaseResponse sendWxMessage(SendWxMessageRequest request) {
        return wxMsgService.sendMessage(request);
    }
}
