package com.iqexception.fxhelper.common.util;

import com.google.protobuf.Timestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class PbUtil {

    public static Timestamp toTimestamp(LocalDateTime time) {
        Timestamp.Builder builder = Timestamp.newBuilder();
        builder.setSeconds(time.toEpochSecond(OffsetDateTime.now().getOffset()));
        builder.setNanos(time.getNano());
        return builder.build();
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp.getSeconds(),
                timestamp.getNanos(), OffsetDateTime.now().getOffset());
    }
}
