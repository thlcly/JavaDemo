package com.aaront.java.clone;

/**
 * @author tonyhui
 * @since 16/7/18
 */
public class Person implements Cloneable {
    /**
     * 姓名
     **/
    private String name;

    /**
     * 电子邮件
     **/
    private Email email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Person(String name, Email email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    protected Person clone() {
        Person person = null;
        try {
            Email email = this.email.clone();
            person = (Person) super.clone();
            person.setEmail(email);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }
}
