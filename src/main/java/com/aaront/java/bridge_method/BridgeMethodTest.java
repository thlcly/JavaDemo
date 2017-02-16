package com.aaront.java.bridge_method;

import java.lang.reflect.Method;

public class BridgeMethodTest {
    public static void main(String[] args) throws Exception {
        Parent parent = new Child();
        System.out.println(parent.bridgeMethod("abc123"));// 调用的是实际的方法
        Class<? extends Parent> clz = parent.getClass();
        Method method = clz.getMethod("bridgeMethod", Object.class);
        System.out.println(method.isBridge());
        System.out.println(method.invoke(parent, "hello"));
        System.out.println(parent.bridgeMethod(new Object()));// 调用的是桥接方法
    }
}