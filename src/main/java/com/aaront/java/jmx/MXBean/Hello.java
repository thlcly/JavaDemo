package com.aaront.java.jmx.MXBean;

import com.aaront.java.jmx.Book;

public class Hello implements HelloMXBean {

    private final String name;
    private final int age;
    private String email;
    private Book book;

    public Hello(String name, int age, String email, Book book) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.book = book;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String sayHello() {
        return "Hello, I'm " + name + ".";
    }

    @Override
    public Book getBook() {
      return this.book;
    }

    @Override
    public void setBook(Book book) {
      this.book = book;
    }
}
