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
//=--------
    public static final String TAG_SUBJECT = "subject";
    public static final String TAG_SUBJECT_ID = "idsubject";
    public static final String TAG_SUBJECT_NAME = "name";

    public static String urlListSubject(int idTeacher, int idClass) {
        return "http://192.168.11.1/TS/teacher/api.php?getdetailclass=true&idteacher=" + idTeacher + "&idclass=" + idClass;
    }

    //--------------------------------
    public static final String TAG_ATTENDENCES = "attendences";
    public static final String TAG_ATTENDENCES_ID = "idattendence";
    public static final String TAG_ATTENDENCES_STATUS = "isattendence";
    public static final String TAG_ATTENDENCES_STUDENT_ID = "idstudent";
    public static final String TAG_ATTENDENCES_STUDENT_NAME = "name";
    public static final String TAG_ATTENDENCES_STUDENT_CODE = "code";
    public static final String TAG_ATTENDENCES_STUDENT_IMAGE = "avatar";


    public static String urlListAttendences(int idTeacher, int idClass, int idSubject) {
        return "http://192.168.11.1/TS/teacher/api.php?createlistattendance=true&idclass=" + idClass + "&idteacher=" + idTeacher + "&idsubject=" + idSubject + "&idtiethoc=1";
    }

    //--------------------------------
    public static final String TAG_STUDIENS = "students";
    public static final String TAG_STUDIENT_ID = "idstudent";
    public static final String TAG_STUDIENT_NAME = "name";
    public static final String TAG_STUDIENT_CODE = "code";
    public static final String TAG_STUDIENT_BIRTHDAY = "birthday";
    public static final String TAG_STUDIENT_ADDRESS = "address";
    public static final String TAG_STUDIENT_PHONE = "phone";
    public static final String TAG_STUDIENT_DESCRIPTION = "description";
    public static final String TAG_STUDIENT_IMAGE = "avatar";



    public static String urlListAllStudentOfClass(int idClass) {
        return "http://192.168.11.1/TS/teacher/api.php?getallstudentinclass=true&idclass=" + idClass;
    }


}
