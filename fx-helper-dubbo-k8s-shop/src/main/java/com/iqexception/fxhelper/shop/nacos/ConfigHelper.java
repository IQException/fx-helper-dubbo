package com.iqexception.fxhelper.shop.nacos;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import com.iqexception.fxhelper.common.nacos.WxConfig;
import org.springframework.stereotype.Component;

@Component
public class ConfigHelper {

    @NacosConfig(dataId = "wx-config.json", group = "fx-helper-common")
    private WxConfig wxConfig;

    public WxConfig getWxConfig() {
        return wxConfig;
    }

    public void setWxConfig(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }
}
