package com.giangnd_svmc.ha18.entity;

/**
 * Created by admin on 4/9/2016.
 */
public class Student {
    public int id;
    public String name;
    public String code;
    public String birthDay;
    public String address;
    public String description;
    private String image;
    public String phone;

    public void setImageStudent(String imageStudent) {
        image = imageStudent;
    }

    public String getImageStudent() {
        return "http://192.168.11.1/TS/images/" + image;
    }
}
