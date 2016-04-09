package com.giangnd_svmc.ha18.entity;

/**
 * Created by admin on 4/10/2016.
 */
public class Attendences {
    public int id;
    public int status;
    public int student_id;
    public String student_code;
    public String student_name;
    private String image;

    public void setImageStudent(String imageStudent) {
        this.image = imageStudent;
    }

    public String getImageStudent() {
        return "http://192.168.11.1/TS/images/" + image;
    }


}
