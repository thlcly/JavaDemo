package com.aaront.java.nio;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tonyhui
 * @since 16/4/10
 */
public class JavaNioDemo {
    public static void main(String[] args){
        Map<Integer, Integer> numberAndCount = new HashMap<>();
        int[] numbers = {3, 5, 7,9, 11, 13, 17, 19, 2, 3, 5, 33, 12, 5};

        for(int i : numbers){
            int count = numberAndCount.get(i);
            numberAndCount.put(i, count++); // NullPointerException here
        }
    }


}
