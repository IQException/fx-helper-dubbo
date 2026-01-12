package com.iqexception.fxhelper.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.protocol.tri.rest.filter.RestExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Activate(group = CommonConstants.PROVIDER)
public class ReqRespFilter implements Filter, RestExtension {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            chain.doFilter(request, response);
        } finally {
            LOG.info("Logging Request  {} : {} ,Response Status: {} ",
                    req.getMethod(), req.getRequestURI(), res.getStatus());
        }


    }

}
