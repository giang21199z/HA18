package com.giangnd_svmc.ha18.entity;

/**
 * Created by admin on 4/9/2016.
 */
public class MyUtils {
    public static final String TAG_SUCCESS = "success";
    //---
    public static final String TAG_TEACHER = "teacher";
    public static final String TAG_TEACHER_ID = "idteacher";
    public static final String TAG_TEACHER_NAME = "name";
    public static final String TAG_TEACHER_PHONE = "phone";
    public static final String TAG_TEACHER_EMAIL = "email";
    public static final String TAG_TEACHER_ADDRESS = "address";
    public static final String TAG_TEACHER_IMAGES = "images";

    public static String urlLoginTeacher(String username, String password) {
        return "http://192.168.11.1/TS/teacher/api.php?loginteacher=true&user=" + username + "&" + "pass=" + password;
    }

    //---
    public static final String TAG_CLASS = "classes";
    public static final String TAG_CLASS_ID = "idclass";
    public static final String TAG_CLASS_NAME = "name";

    public static String urlListClass(int idTeacher) {
        return "http://192.168.11.1/TS/teacher/api.php?getallclass=true&idteacher=" + idTeacher;
    }
    //-----


}
