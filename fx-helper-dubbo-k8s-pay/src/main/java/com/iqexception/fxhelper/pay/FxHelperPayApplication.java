package com.iqexception.fxhelper.pay;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication(scanBasePackages = {"com.iqexception.fxhelper.pay", "com.iqexception.fxhelper.common.bean"})
@EnableDubbo
public class FxHelperPayApplication {
    public static void main(String[] args) throws UnknownHostException {
        //FIXME
        System.setProperty("nacos.logging.default.config.enabled", "false");
        System.setProperty("HOST_NAME", InetAddress.getLocalHost().getHostName());
        System.setProperty("HOST_IP", InetAddress.getLocalHost().getHostAddress());

        SpringApplication.run(FxHelperPayApplication.class, args);
    }
}
