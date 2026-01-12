package com.iqexception.fxhelper.misc;

import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.misc.DubboMiscRestServiceTriple;
import com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse;
import com.iqexception.fxhelper.api.misc.MsgSubscribeRequest;
import com.iqexception.fxhelper.misc.service.AliyunService;
import com.iqexception.fxhelper.misc.service.WxMsgService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.remoting.http12.HttpMethods;
import org.apache.dubbo.remoting.http12.rest.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperMiscRestService extends DubboMiscRestServiceTriple.MiscRestServiceImplBase {

    private final WxMsgService wxMsgService;

    private final AliyunService aliyunService;

    public FxHelperMiscRestService(WxMsgService wxMsgService, AliyunService aliyunService) {
        this.wxMsgService = wxMsgService;
        this.aliyunService = aliyunService;
    }

    @PostMapping("/wx/msg_subscribe")
    public BaseResponse msgSubscribe(@RequestBody MsgSubscribeRequest request) {
        return wxMsgService.msgSubscribe(request);
    }

    @PostMapping("/get_upload_policy")
    public GetUploadPolicyResponse getUploadPolicy(@RequestBody BaseRequest request) {
        return aliyunService.getUploadPolicy(request);
    }
}
