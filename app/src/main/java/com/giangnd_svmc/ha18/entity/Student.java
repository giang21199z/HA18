package com.giangnd_svmc.ha18.entity;

/**
 * Created by admin on 4/9/2016.
 */
public class Student {
    public int id;
    public String name;
    public int attendance;
    public String imageURL="";

    public Student(String name) {
        this.name = name;
    }
}
