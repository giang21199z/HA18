package com.giangnd_svmc.ha18.entity;

import java.io.Serializable;

/**
 * Created by Manh on 4/9/16.
 */
public class Teacher implements Serializable {
    public int id;
    public String name;
    public String email;
    public String phone;
    public String address;
    private String images;

    public void setURL(String url) {
        images = url;
    }

    public String getURLImage() {
        return "http://192.168.11.1/TS/images/" + images;
    }
}
