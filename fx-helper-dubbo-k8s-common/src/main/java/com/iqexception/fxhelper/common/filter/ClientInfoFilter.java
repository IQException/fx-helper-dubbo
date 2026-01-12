package com.iqexception.fxhelper.common.filter;

import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.MobileRequestHead;
import com.iqexception.fxhelper.common.ClientInfo;
import com.iqexception.fxhelper.common.TLVarManager;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

@Activate(group = CommonConstants.PROVIDER)
public class ClientInfoFilter implements Filter {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        Class<?>[] paramTypes = invocation.getParameterTypes();
        Class<?> bodyClass = null;
        if (paramTypes.length > 0 && paramTypes[0] != BaseRequest.class) {
            try {
                Object body = invocation.getArguments()[0];
                bodyClass = paramTypes[0];
                Field headField = bodyClass.getField("head");
                headField.setAccessible(true);
                MobileRequestHead head = (MobileRequestHead) headField.get(body);
                // 将head放入ThreadLocal中
                TLVarManager.setClientInfo(buildClientInfo(head));

            } catch (NoSuchFieldException ignored) {
            } catch (IllegalAccessException e) {
                LOG.error("reflect failed ! class:{}", bodyClass, e);
            }
        }

        Result result = null;
        try {
            result = invoker.invoke(invocation);
        } finally {
            TLVarManager.removeClientInfo();
        }

        return result;
    }

    private ClientInfo buildClientInfo(MobileRequestHead head) {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setBrand(head.getBrand());
        clientInfo.setIp(head.getIpAddr());
        clientInfo.setMac(head.getMacAddr());
        clientInfo.setModel(head.getModel());
        clientInfo.setSystem(head.getSystem());
        clientInfo.setDeviceId(head.getDeviceId());
        clientInfo.setPlatform(head.getPlatform());
        clientInfo.setLongitude(head.getLongitude());
        clientInfo.setLatitude(head.getLatitude());
        return clientInfo;
    }
}
