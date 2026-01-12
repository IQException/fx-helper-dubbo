package com.iqexception.fxhelper.pay.support;


import com.google.protobuf.util.JsonFormat;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.common.ResponseStatus;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.bean.BeanUtil;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.util.MessageUtil;
import com.iqexception.fxhelper.pay.stub.UserServiceStub;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.protocol.tri.rest.filter.RestExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.io.IOException;

@Activate(group = org.apache.dubbo.common.constants.CommonConstants.PROVIDER)
public class PayVerifyFilter implements Filter, RestExtension {
    public static final String HEADER_PAY_SECRET = "FX-PAY-SECRET";

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        JsonMapper jsonMapper = BeanUtil.getBean(JsonMapper.class);

        String secret = req.getHeader(HEADER_PAY_SECRET);

        if (StringUtils.isBlank(secret) || !verify(secret)) {
            BaseResponse baseResponse = BaseResponse.newBuilder()
                    .setStatus(status(ErrorCode.PASSWORD_ERROR))
                    .build();

            res.getWriter().write(jsonMapper.serialize(baseResponse));
            res.getWriter().flush();
            LOG.warn("pay secret : {} is incorrect !", secret);
            return;
        }
        chain.doFilter(request, response);

    }

    public String[] getPatterns() {
        return new String[]{"/withdraw"};
    }

    public int getPriority() {
        return MIN_PRIORITY;
    }

    private boolean verify(String secret) {
        UserServiceStub userServiceStub = BeanUtil.getBean(UserServiceStub.class);
        return userServiceStub.verifyPaySecretQuietly(TLVarManager.getUserId(), secret);
    }

    private ResponseStatus status(int errorCode) {
        MessageSource messageSource = BeanUtil.getBean(MessageSource.class);
        return ResponseStatus.newBuilder()
                .setErrorCode(String.valueOf(errorCode))
                .setErrorMessage(MessageUtil.message(errorCode, messageSource))
                .build();
    }
}
