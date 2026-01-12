package com.iqexception.fxhelper.common.util;

import org.springframework.web.util.UriComponentsBuilder;

public class HttpClientUtil {


    public static String wrapParam(String param) {
        return "{" + param + "}";
    }

    public static String getUrl(String url, String... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        for (String param : params) {
            builder.queryParam(param, wrapParam(param));
        }
        return builder.encode().toUriString();
    }
}
