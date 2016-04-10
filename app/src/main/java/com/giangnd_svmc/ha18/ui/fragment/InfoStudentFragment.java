package com.giangnd_svmc.ha18.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.Student;
import com.giangnd_svmc.ha18.glide.GlideCircleTransform;
import com.giangnd_svmc.ha18.ui.activity.MainActivity;

/**
 * Created by admin on 4/9/2016.
 */
public class InfoStudentFragment extends BaseFragment {
    private ImageView imageStudent;
    private TextView tvName, tvCode, tvClass, tvBirth, tvPhone, tvDescription, tvAddress;
    private Student student;

    public InfoStudentFragment() {

    }

    public InfoStudentFragment(Student student) {
        this.student = student;

    }

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_info;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("Info");
        imageStudent = (ImageView) rootView.findViewById(R.id.imageStudent);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvCode = (TextView) rootView.findViewById(R.id.tv_MSV);
        tvBirth = (TextView) rootView.findViewById(R.id.tv_Birth);
        tvPhone = (TextView) rootView.findViewById(R.id.tv_Phone);
        tvDescription = (TextView) rootView.findViewById(R.id.tv_Description);
        tvAddress = (TextView) rootView.findViewById(R.id.tv_Address);
        tvName.setText(student.name);
        tvCode.setText("Code: " + student.code);
        tvBirth.setText(student.birthDay);
        tvAddress.setText(student.address);
        tvDescription.setText(student.description);
        tvPhone.setText(student.phone);


        Glide.with(getActivity())
                .load(student.getImageStudent())
                .centerCrop()
                .transform(new GlideCircleTransform(getActivity()))
                .error(R.drawable.account)
                .into(imageStudent);
    }
}
