package com.iqexception.fxhelper.misc.service;

import com.google.common.collect.ImmutableMap;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import com.iqexception.fxhelper.common.dto.wx.CommonResponse;
import com.iqexception.fxhelper.common.nacos.WxConfig;
import com.iqexception.fxhelper.common.util.HttpClientUtil;
import com.iqexception.fxhelper.misc.service.dto.GetAccessTokenResponse;
import com.iqexception.fxhelper.misc.service.dto.SendMessageRequest;
import com.iqexception.fxhelper.misc.support.ConfigHelper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.iqexception.fxhelper.misc.constant.WxConstants.*;


@Component
@EnableScheduling
public class WxApi {

    private static final Logger LOG = LoggerFactory.getLogger(WxApi.class);
    private volatile String ACCESS_TOKEN = null;

    private final RestTemplate restTemplate;

    private final JsonMapper jsonMapper;

    private final WxConfig wxConfig;

    public WxApi(@Qualifier("restTemplate") RestTemplate restTemplate, JsonMapper jsonMapper, ConfigHelper configHelper) {
        this.restTemplate = restTemplate;
        this.jsonMapper = jsonMapper;
        this.wxConfig = configHelper.getWxConfig();
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    @PostConstruct
    private void getAccessTokenJob() {
        int delay = 30;

        try {
            GetAccessTokenResponse response = doGetAccessToken();
            if (StringUtils.isNotBlank(response.getAccessToken())) {
                ACCESS_TOKEN = response.getAccessToken();
                delay = response.getExpiresIn() > 300 ? 300 : response.getExpiresIn();
            }
            LOG.info("access token:{},delay:{}", jsonMapper.serialize(response), delay);
        } finally {
            Executors.newScheduledThreadPool(1).schedule(this::getAccessTokenJob, delay, TimeUnit.SECONDS);
        }
    }

    private GetAccessTokenResponse doGetAccessToken() {

        LOG.info("doGetAccessToken: {}", LocalDateTime.now().format(CommonConstants.BASIC_FORMATTER));

        String url = HttpClientUtil.getUrl("https://api.weixin.qq.com/cgi-bin/token",
                APP_ID_KEY, APP_SECRET_KEY, GRANT_TYPE_KEY);

        Map<String, String> params = ImmutableMap
                .of(APP_ID_KEY, wxConfig.getAppId(),
                        APP_SECRET_KEY, wxConfig.getAppSecret(),
                        GRANT_TYPE_KEY, "client_credential");

        return restTemplate.getForObject(url, GetAccessTokenResponse.class, params);
    }

    public CommonResponse sendMessage(String templateId,
                                      String openId,
                                      String page,
                                      Object data) {


        SendMessageRequest request = new SendMessageRequest();
        request.setData(data);
        request.setPage(page);
        request.setTemplateId(templateId);
        request.setToUser(openId);
        //FIXME 根据当前环境选择developer/trial/formal
        request.setMpState("trial");
        request.setLang("zh_CN");

        return restTemplate.postForObject(
                "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + ACCESS_TOKEN,
                request, CommonResponse.class);
    }


}
