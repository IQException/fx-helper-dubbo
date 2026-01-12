package com.iqexception.fxhelper.common.filter;

import build.buf.protovalidate.ValidationResult;
import build.buf.protovalidate.Validator;
import build.buf.protovalidate.Violation;
import build.buf.protovalidate.exceptions.ValidationException;
import com.google.protobuf.Message;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.common.ResponseStatus;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.util.MessageUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.lang.Prioritized;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Activate(group = org.apache.dubbo.common.constants.CommonConstants.PROVIDER, order = Prioritized.MIN_PRIORITY)
public class PbValidationFilter implements Filter {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final Validator validator = new Validator();

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (invocation.getArguments().length > 0) {
            Message message = (Message) invocation.getArguments()[0];
            if (message != null) {
                try {
                    ValidationResult result = validator.validate(message);
                    // Check if there are any validation violations
                    if (!result.getViolations().isEmpty()) {
                        LOG.warn("validation failed ! service:{},method:{},args:{}, result: {}",
                                invocation.getServiceName(), invocation.getMethodName(), message, result);
                        return AsyncRpcResult.newDefaultAsyncResult(buildResponse(result.getViolations()), invocation);
                    }
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return invoker.invoke(invocation);
    }

    private BaseResponse buildResponse(List<Violation> violations) {

        ResponseStatus status = ResponseStatus.newBuilder()
                .setErrorCode(String.valueOf(ErrorCode.PARAM_ERROR))
                .setErrorMessage(MessageUtil.message(ErrorCode.PARAM_ERROR, messageSource))
                .putAllErrors(buildErrorsMap(violations))
                .build();

        return BaseResponse.newBuilder()
                .setStatus(status)
                .build();
    }

    private Map<String, String> buildErrorsMap(List<Violation> violations) {
        Map<String, String> errors = new HashMap<>();
        violations.forEach(violation -> {
            errors.put(violation.toProto().getField().toString(),
                    String.format(
                            "%s [%s]", violation.toProto().getMessage(), violation.toProto().getConstraintId()));
        });
        return errors;
    }
}
