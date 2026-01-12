package com.iqexception.fxhelper.shop.wx;

import com.iqexception.fxhelper.common.BizException;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.shop.stub.MiscServiceStub;
import com.iqexception.fxhelper.shop.wx.model.GetQRCodeRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class WxApi {

    private static final Logger LOG = LoggerFactory.getLogger(WxApi.class);

    private final RestTemplate restTemplate;

    private final MiscServiceStub miscServiceStub;

    public WxApi(@Qualifier("restTemplate") RestTemplate restTemplate,
                 MiscServiceStub miscServiceStub) {
        this.restTemplate = restTemplate;
        this.miscServiceStub = miscServiceStub;
    }

    public List<String> getQrCodes(String page, List<String> scenes) {

        String accessToken = miscServiceStub.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            LOG.error(" failed to get access token in getQrCodes! ");
            throw new BizException(ErrorCode.SYSTEM_ERROR);
        }

        List<String> qrCodes = new ArrayList<>();

        scenes.forEach(scene -> {
            GetQRCodeRequest request = new GetQRCodeRequest(page, scene);
            request.setCheckPath(false);
            //FIXME develop/trial/release
            request.setEnvVersion("trial");
            ResponseEntity<byte[]> response = restTemplate.postForEntity(
                    "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken,
                    request, byte[].class);

            byte[] buffer = response.getBody();
            String contentType = response.getHeaders().getFirst("Content-Type");
            if (contentType != null && contentType.contains("image/")) {
                qrCodes.add(new String(Base64.getEncoder().encode(buffer), StandardCharsets.UTF_8));
            } else {
                LOG.error("getQrCodes failed! response:{}", buffer == null ? "" : new String(buffer, StandardCharsets.UTF_8));
                throw new BizException(ErrorCode.PARAM_ERROR);
            }

        });

        return qrCodes;

    }
}
