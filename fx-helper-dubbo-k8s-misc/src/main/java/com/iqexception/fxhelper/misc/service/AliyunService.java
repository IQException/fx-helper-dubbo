package com.iqexception.fxhelper.misc.service;


import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse;

public interface AliyunService {
    GetUploadPolicyResponse getUploadPolicy(BaseRequest request);
}
