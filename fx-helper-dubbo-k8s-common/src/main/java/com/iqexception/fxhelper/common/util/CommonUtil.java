package com.iqexception.fxhelper.common.util;

import com.google.common.base.Joiner;

public class CommonUtil {

    public static String getObjectName(Long userId) {
        return Joiner.on("_").join(userId, System.currentTimeMillis());
    }

}
