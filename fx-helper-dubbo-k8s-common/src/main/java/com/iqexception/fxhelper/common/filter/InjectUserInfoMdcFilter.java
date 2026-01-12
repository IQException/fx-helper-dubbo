package com.iqexception.fxhelper.common.filter;

import com.iqexception.fxhelper.common.constant.CommonConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.protocol.tri.rest.filter.RestExtension;
import org.slf4j.MDC;

import java.io.IOException;
@Activate(group = org.apache.dubbo.common.constants.CommonConstants.PROVIDER)
public class InjectUserInfoMdcFilter implements Filter , RestExtension {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String userId = req.getHeader(CommonConstants.HEADER_USER_ID);
        if (StringUtils.isNotEmpty(userId)) {
            MDC.put(CommonConstants.MDC_USER_ID, userId);
        }

        String openId = req.getHeader(CommonConstants.HEADER_OPEN_ID);
        if (StringUtils.isNotEmpty(openId)) {
            MDC.put(CommonConstants.MDC_OPEN_ID, openId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CommonConstants.MDC_USER_ID);
            MDC.remove(CommonConstants.MDC_OPEN_ID);
        }

    }
    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }

}
