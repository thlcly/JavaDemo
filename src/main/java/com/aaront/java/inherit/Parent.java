package com.aaront.java.inherit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tonyhui
 * @since 16/6/18
 */
public class Parent {
    private Map<String , String> map = new HashMap<>();

    {
        map.put("parent", "parent");
    }

    protected String get(String key) {
        return map.get(key);
    }
}
