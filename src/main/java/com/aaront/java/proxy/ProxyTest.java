package com.aaront.java.proxy;

import com.aaront.java.proxy.dynamic_proxy_v0.ProxyVersion_0;
import com.aaront.java.proxy.dynamic_proxy_v1.ProxyVersion_1;
import com.aaront.java.proxy.dynamic_proxy_v2.HelloInvocationHandler;
import com.aaront.java.proxy.dynamic_proxy_v2.InvocationHandler;
import com.aaront.java.proxy.dynamic_proxy_v2.ProxyVersion_2;
import com.aaront.java.proxy.static_proxy.HelloWorld;
import com.aaront.java.proxy.static_proxy.HelloWorldImpl;
import com.aaront.java.proxy.static_proxy.StaticProxy;

/**
 * @author tonyhui
 * @since 17/2/13
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        ProxyTest proxyTest = new ProxyTest();
        // 静态代理
        // proxyTest.staticProxy();

        // 动态代理 V0: 生成指定接口的动态代理类
        // proxyTest.dynamicProxyV0();

        // 动态代理 V1: 生成任意接口的动态代理类, 但是代理是固定的
        // proxyTest.dynamicProxyV1();

        // 动态代理 V2: 生成任意接口的任意代理的动态代理类
        proxyTest.dynamicProxyV2();

    }

    public void staticProxy() {
        HelloWorld helloWorld = new StaticProxy(new HelloWorldImpl());
        helloWorld.print();
    }

    public void dynamicProxyV0() throws Exception {
        long start = System.currentTimeMillis();
        HelloWorld helloWorld = (HelloWorld) ProxyVersion_0.newProxyInstance();
        System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
        helloWorld.print();
        System.out.println();
    }

    public void dynamicProxyV1() throws Exception {
        long start = System.currentTimeMillis();
        HelloWorld helloWorld = (HelloWorld) ProxyVersion_1.newProxyInstance(HelloWorld.class);
        System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
        helloWorld.print();
        System.out.println();
    }

    public void dynamicProxyV2() throws Exception {
        long start = System.currentTimeMillis();
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        InvocationHandler ih = new HelloInvocationHandler(helloWorldImpl);
        HelloWorld helloWorld = (HelloWorld) ProxyVersion_2.newProxyInstance(HelloWorld.class, ih);
        System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
        helloWorld.print();
        System.out.println();
    }
}
