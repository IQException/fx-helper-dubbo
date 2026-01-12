package com.iqexception.fxhelper.common.filter;

import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.protocol.tri.rest.filter.RestExtension;

import java.io.IOException;
@Activate(group = org.apache.dubbo.common.constants.CommonConstants.PROVIDER)
public class SessionFilter implements Filter, RestExtension {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String userId = req.getHeader(CommonConstants.HEADER_USER_ID);
        String openId = req.getHeader(CommonConstants.HEADER_OPEN_ID);

        if (StringUtils.isNotBlank(userId)) {
            TLVarManager.setUserId(Long.valueOf(userId));
        }
        if (StringUtils.isNotBlank(openId)) {
            TLVarManager.setOpenId(openId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            TLVarManager.removeUserId();
            TLVarManager.removeOpenId();
        }

    }

}


