package com.aaront.java.jmx.MBean;

import com.aaront.java.jmx.Book;

public interface HelloMBean {
    int getAge();
    String getName();
    String getEmail();
    void setEmail(String email);
    String sayHello();
    Book getBook();
    void setBook(Book book);
}
