package com.iqexception.fxhelper.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

public class JsonMapper {

    private final ObjectMapper objectMapper;

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        try {
//            if (Message.class.isAssignableFrom(clazz)) {
//                Method newBuilder = clazz.getMethod("newBuilder");
//                Message.Builder builder = (Message.Builder) newBuilder.invoke(null);
//                JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
//                return (T) builder.build();
//            }
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String serialize(Object obj) {
        try {
            if (obj instanceof MessageOrBuilder) {
                return JsonFormat.printer().print((MessageOrBuilder) obj);
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
