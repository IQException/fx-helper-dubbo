package com.iqexception.fxhelper.misc.support;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import com.iqexception.fxhelper.common.nacos.AliConfig;
import com.iqexception.fxhelper.common.nacos.WxConfig;
import org.springframework.stereotype.Component;

@Component
public class ConfigHelper {

    @NacosConfig(dataId = "wx-config.json", group = "fx-helper-common")
    private WxConfig wxConfig;

    @NacosConfig(dataId = "ali-config.json", group = "fx-helper-common")
    private AliConfig aliConfig;

    public AliConfig getAliConfig() {
        return aliConfig;
    }

    public void setAliConfig(AliConfig aliConfig) {
        this.aliConfig = aliConfig;
    }

    public WxConfig getWxConfig() {
        return wxConfig;
    }

    public void setWxConfig(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }
}
