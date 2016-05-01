package com.aaront.java.annotation;

/**
 * @author tonyhui
 * @since 16/4/27
 */
public @interface Entity {
    //String tableName();
    //String primaryKey();
    String value();
    String name();
    int age();
    String[] newNames();
}
