package com.aaront.java.clone;

/**
 * @author tonyhui
 * @since 16/7/18
 */
public class Email implements Cloneable {
    public String content;
    public String title;

    public Email(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected Email clone() {
        try {
            return (Email) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return new Email("", "");
        }
    }
}
