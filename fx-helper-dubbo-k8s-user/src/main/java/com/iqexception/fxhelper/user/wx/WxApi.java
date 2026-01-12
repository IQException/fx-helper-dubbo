package com.iqexception.fxhelper.user.wx;

import com.google.common.collect.ImmutableMap;
import com.iqexception.fxhelper.common.nacos.WxConfig;
import com.iqexception.fxhelper.common.util.HttpClientUtil;
import com.iqexception.fxhelper.user.nacos.ConfigHelper;
import com.iqexception.fxhelper.user.wx.model.Code2SessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.iqexception.fxhelper.user.constant.WxConstants.*;

@Component
@EnableScheduling
public class WxApi {

    private static final Logger LOG = LoggerFactory.getLogger(WxApi.class);

    private final RestTemplate restTemplate;


    private final WxConfig wxConfig;

    public WxApi(@Qualifier("restTemplate") RestTemplate restTemplate,
                 ConfigHelper configHelper) {
        this.restTemplate = restTemplate;
        this.wxConfig = configHelper.getWxConfig();
    }

    public Code2SessionResponse code2Session(String code) {

        String url = HttpClientUtil.getUrl("https://api.weixin.qq.com/sns/jscode2session",
                APP_ID_KEY, APP_SECRET_KEY, "js_code", GRANT_TYPE_KEY);

        Map<String, String> params = ImmutableMap.of(APP_ID_KEY, wxConfig.getAppId(),
                APP_SECRET_KEY, wxConfig.getAppSecret(),
                "js_code", code,
                GRANT_TYPE_KEY, "authorization_code");

        return restTemplate.getForObject(url, Code2SessionResponse.class, params);

    }


}
