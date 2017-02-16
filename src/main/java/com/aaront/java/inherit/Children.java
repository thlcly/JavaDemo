package com.aaront.java.inherit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tonyhui
 * @since 16/6/18
 */
public class Children extends Parent {

    private Map<String, String> map = new HashMap<>();

    {
        map.put("children", "children");
    }

    /**
     * super.bridgeMethod()调用的是父类中的方法, 在method中使用的成员变量也全部都是父类中的
     * bridgeMethod() 不能是private的
     *
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return map.get(key) == null ?
                super.get(key) : map.get(key);
    }

    public static void main(String[] args) {
        Children children = new Children();
        System.out.println(children.get("parent"));
        System.out.println(children.get("children"));
    }
}
