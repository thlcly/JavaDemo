package com.aaront.java.jmx.MXBean;

import com.aaront.java.jmx.Book;

public interface HelloMXBean {
    int getAge();
    String getName();
    String getEmail();
    void setEmail(String email);
    String sayHello();
    Book getBook();
    void setBook(Book book);
}  