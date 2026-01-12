package com.iqexception.fxhelper.misc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDubbo
public class FxHelperMiscApplication {

    public static void main(String[] args) throws UnknownHostException {

        System.setProperty("nacos.logging.default.config.enabled", "false");
        System.setProperty("HOST_NAME", InetAddress.getLocalHost().getHostName());
        System.setProperty("HOST_IP", InetAddress.getLocalHost().getHostAddress());

        SpringApplication.run(FxHelperMiscApplication.class, args);
    }
}
