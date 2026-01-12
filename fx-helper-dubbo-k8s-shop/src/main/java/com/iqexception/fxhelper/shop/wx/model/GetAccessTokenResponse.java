package com.iqexception.fxhelper.shop.wx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iqexception.fxhelper.common.dto.wx.CommonResponse;

public class GetAccessTokenResponse extends CommonResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }




}
