package com.iqexception.fxhelper.common.filter;

import com.google.common.base.MoreObjects;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.common.ResponseStatus;
import com.iqexception.fxhelper.common.BizException;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.util.MessageUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.lang.Prioritized;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

@Activate(group = CommonConstants.PROVIDER, order = Prioritized.MIN_PRIORITY - 1)
public class BizExceptionFilter implements Filter, Filter.Listener {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);

    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        Throwable throwable = appResponse.getException();
        if (throwable == null) return;
        int errorCode;
        String errorMessage;
        if (throwable instanceof BizException) {
            errorCode = ErrorCode.SUCCESS == ((BizException) throwable).getErrorCode() ? ErrorCode.SYSTEM_ERROR
                    : ((BizException) throwable).getErrorCode();
            errorMessage = MoreObjects.firstNonNull(throwable.getMessage(),
                    MessageUtil.message(errorCode, messageSource));
            LOG.warn("invoke failed! service:{},method:{},args:{},errorCode:{},errorMessage:{}",
                    invocation.getServiceName(), invocation.getMethodName(),
                    invocation.getArguments()[0],
                    errorCode, errorMessage);
        } else {
            errorCode = ErrorCode.SYSTEM_ERROR;
            errorMessage = MessageUtil.message(errorCode, messageSource);
            LOG.error("invoke failed! service:{},method:{},args:{}",
                    invocation.getServiceName(), invocation.getMethodName(),
                    invocation.getArguments()[0],
                    throwable);
        }
        appResponse.setException(null);
        appResponse.setValue(buildResponse(errorCode, errorMessage));
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }

    private BaseResponse buildResponse(int errorCode, String errorMessage) {

        ResponseStatus status = ResponseStatus.newBuilder()
                .setErrorCode(String.valueOf(errorCode))
                .setErrorMessage(errorMessage)
                .build();

        return BaseResponse.newBuilder()
                .setStatus(status)
                .build();
    }

}
