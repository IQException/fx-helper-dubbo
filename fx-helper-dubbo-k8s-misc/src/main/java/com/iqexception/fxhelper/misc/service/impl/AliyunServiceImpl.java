package com.iqexception.fxhelper.misc.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse;
import com.iqexception.fxhelper.api.misc.GetUploadPolicyResult;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.nacos.AliConfig;
import com.iqexception.fxhelper.common.util.CommonUtil;
import com.iqexception.fxhelper.misc.service.AliyunService;
import com.iqexception.fxhelper.misc.support.ConfigHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.iqexception.fxhelper.common.util.ResponseUtil.statusOk;

@Service
public class AliyunServiceImpl implements AliyunService {

    private final OSS ossClient;
    private final AliConfig aliConfig;

    public AliyunServiceImpl(ConfigHelper configHelper) {
        aliConfig = configHelper.getAliConfig();
        ossClient = new OSSClientBuilder().build(aliConfig.getEndpoint(),
                aliConfig.getAccessKeyId(), aliConfig.getAccessKeySecret());
    }

    @Override
    public GetUploadPolicyResponse getUploadPolicy(BaseRequest request) {

        long expireTime = System.currentTimeMillis() + 60 * 1000;

        PolicyConditions policyCons = new PolicyConditions();
        policyCons.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 8 * 1024 * 1024);
        String key = CommonUtil.getObjectName(TLVarManager.getUserId());
        policyCons.addConditionItem(MatchMode.Exact, PolicyConditions.COND_KEY, key);
        policyCons.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_CONTENT_TYPE, "image");


        String postPolicy = ossClient.generatePostPolicy(new Date(expireTime), policyCons);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        GetUploadPolicyResult result = GetUploadPolicyResult.newBuilder()
                .setAccessid(aliConfig.getAccessKeyId())
                .setKey(key)
                .setPolicy(encodedPolicy)
                .setSignature(postSignature)
                .setHost("https://" + aliConfig.getBucketName() + "." + aliConfig.getEndpoint())
                .setExpire(String.valueOf(expireTime / 1000))
                .build();

        return GetUploadPolicyResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(result)
                .build();


    }
}
