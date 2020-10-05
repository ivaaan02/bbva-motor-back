package com.bbva.beans;

import com.google.gson.annotations.Expose;

public class ExampleBean {

    @Expose
    private String text;

    @Expose
    private String name;

    @Expose
    private String surname;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
