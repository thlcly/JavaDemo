package com.aaront.java.bridge_method;

/**
 * @author tonyhui
 * @since 16/12/30
 */
public class Child implements Parent<String> {
    public String bridgeMethod(String param) {
        return param;
    }
}
