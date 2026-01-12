package com.iqexception.fxhelper.common.util;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.common.ResponseStatus;
import com.iqexception.fxhelper.common.constant.ErrorCode;


public class ResponseUtil {


    public static ResponseStatus statusOk() {
        return ResponseStatus.newBuilder()
                .setErrorCode(String.valueOf(ErrorCode.SUCCESS))
                .build();
    }

    public static boolean isSuccess(ResponseStatus status) {
        return status.getErrorCode().equals(String.valueOf(ErrorCode.SUCCESS));
    }

    public static boolean isSuccess(int errorCode) {
        return errorCode == ErrorCode.SUCCESS;
    }

    public static boolean isSuccess(String errorCode) {
        return String.valueOf(ErrorCode.SUCCESS).equals(errorCode);
    }

    public static boolean isFail(ResponseStatus status) {
        return !isSuccess(status);
    }

    public static boolean isFail(int errorCode) {
        return !isSuccess(errorCode);
    }

    public static boolean isFail(String errorCode) {
        return !isSuccess(errorCode);
    }

    public static BaseResponse responseOk() {
        return BaseResponse.newBuilder()
                .setStatus(statusOk())
                .build();
    }

    public static BaseResponse response(ResponseStatus status) {
        return BaseResponse.newBuilder()
                .setStatus(status)
                .build();
    }

}
