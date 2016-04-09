package com.giangnd_svmc.ha18.entity;

/**
 * Created by admin on 4/9/2016.
 */
public class MyUtils {
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_TEACHER = "teacher";
    public static final String TAG_TEACHER_ID = "idteacher";
    public static final String TAG_TEACHER_NAME = "name";




    public static String urlLoginTeacher(String username, String password) {
        return "http://192.168.11.1/TS/teacher/api.php?loginteacher=true&user=" + username + "&" + "pass=" + password;
    }
}
