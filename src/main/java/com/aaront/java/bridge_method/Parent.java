package com.aaront.java.bridge_method;

/**
 * @author tonyhui
 * @since 16/12/30
 */
public interface Parent<T> {
    T bridgeMethod(T param);
}
