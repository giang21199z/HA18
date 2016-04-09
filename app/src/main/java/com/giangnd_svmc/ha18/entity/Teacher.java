package com.giangnd_svmc.ha18.entity;

/**
 * Created by Manh on 4/9/16.
 */
public class Teacher {
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Teacher(String name) {
        this.name = name;
    }
}
