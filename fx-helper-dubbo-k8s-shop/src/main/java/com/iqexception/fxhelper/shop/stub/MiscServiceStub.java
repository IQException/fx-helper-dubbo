package com.iqexception.fxhelper.shop.stub;

import com.google.protobuf.Empty;
import com.iqexception.fxhelper.api.misc.GetAccessTokenResponse;
import com.iqexception.fxhelper.api.misc.MiscService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class MiscServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @DubboReference
    private MiscService miscApi;

    private final JsonMapper jsonMapper;

    public MiscServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public String getAccessToken() {

        try {
            GetAccessTokenResponse response = miscApi.getAccessToken(Empty.newBuilder().build());
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getAccessToken();
            } else {
                LOGGER.error("get access token failed! response:{}", jsonMapper.serialize(response));
            }
        } catch (RestClientException e) {
            LOGGER.error("get access token failed!", e);
        }

        return null;
    }

}
