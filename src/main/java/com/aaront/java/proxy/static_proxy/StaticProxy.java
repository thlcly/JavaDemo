package com.aaront.java.proxy.static_proxy;

public class StaticProxy implements HelloWorld {
    private HelloWorld helloWorld;

    public StaticProxy(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public void print() {
        System.out.println("Before Hello World!");
        helloWorld.print();
        System.out.println("After Hello World!");
    }
}
