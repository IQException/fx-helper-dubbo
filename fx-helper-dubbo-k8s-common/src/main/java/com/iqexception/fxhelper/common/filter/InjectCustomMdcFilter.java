package com.iqexception.fxhelper.common.filter;

import com.google.common.collect.Lists;
import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.common.MobileRequestHead;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.List;

@Activate(group = CommonConstants.PROVIDER)
public class InjectCustomMdcFilter implements Filter {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private InjectMdcFields injectFields;

    public void setInjectMdcFields(InjectMdcFields injectFields) {
        this.injectFields = injectFields;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Class<?>[] paramTypes = invocation.getParameterTypes();
        Class<?> bodyClass = null;
        List<String> mdcKeys = Lists.newArrayList();
        if (paramTypes.length > 0 && paramTypes[0] != BaseRequest.class) {
            try {
                Object body = invocation.getArguments()[0];
                bodyClass = paramTypes[0];
                Field paramField = bodyClass.getField("param");
                paramField.setAccessible(true);
                MobileRequestHead param = (MobileRequestHead) paramField.get(body);
                if (param != null && injectFields != null && injectFields.getInjectFields() != null) {
                    Class<?> paramClass = param.getClass();
                    for (Pair<String, String> pair : injectFields.getInjectFields()) {
                        Field field = paramClass.getField(pair.getLeft());
                        field.setAccessible(true);
                        MDC.put(pair.getRight(), String.valueOf(field.get(param)));
                        mdcKeys.add(pair.getRight());
                    }
                }
            } catch (NoSuchFieldException ignored) {
            } catch (IllegalAccessException e) {
                LOG.error("reflect failed ! class:{}", bodyClass, e);
            }
        }

        Result result = null;
        try {
            result = invoker.invoke(invocation);
        } finally {
            for (String mdcKey : mdcKeys) {
                MDC.remove(mdcKey);
            }
        }

        return result;
    }
}
