package com.aaront.java.proxy.dynamic_proxy_v2;

import java.lang.reflect.Method;

public interface InvocationHandler {
    void invoke(Object proxy, Method method) throws Exception;
}
