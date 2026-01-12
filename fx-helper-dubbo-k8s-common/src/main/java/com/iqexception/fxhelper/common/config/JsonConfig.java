package com.iqexception.fxhelper.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import com.iqexception.fxhelper.common.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class JsonConfig {


    @Bean
    public JsonMapper jsonMapper(ObjectMapper objectMapper) {
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Message.class, new PbSerializer());
//        objectMapper.registerModule(simpleModule);
        return new JsonMapper(objectMapper);
    }


    public static class PbSerializer extends StdSerializer<Message> {

        private final Logger LOG = LoggerFactory.getLogger(PbSerializer.class);

        public PbSerializer() {
            this(null);
        }

        protected PbSerializer(Class<Message> t) {
            super(t);
        }

        @Override
        public void serialize(Message message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeRawValue(JsonFormat.printer().print(message));
        }
    }
}
