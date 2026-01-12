package com.iqexception.fxhelper.common;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmqHelper {

    private static final Logger LOG = LoggerFactory.getLogger(RmqHelper.class);

    private final RocketMQTemplate rmqTemplate;

    private final JsonMapper jsonMapper;

    public RmqHelper(RocketMQTemplate rmqTemplate, JsonMapper jsonMapper) {
        this.rmqTemplate = rmqTemplate;
        this.jsonMapper = jsonMapper;
    }

    public void asyncSend(String topic, Object msg) {
        rmqTemplate.asyncSend(topic, msg,
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult var1) {
                        LOG.info("Success on async send topic :{} , SendResult={} %n", topic, var1);
                    }

                    @Override
                    public void onException(Throwable var1) {
                        LOG.error("Exception on async send topic: {} , message: {} ", topic, jsonMapper.serialize(msg), var1);
                    }

                });
    }
}
