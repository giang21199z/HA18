package com.giangnd_svmc.ha18.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.glide.GlideCircleTransform;

/**
 * Created by admin on 4/9/2016.
 */
public class InfoStudentFragment extends BaseFragment {
    private ImageView imageStudent;

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_info;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        imageStudent = (ImageView) rootView.findViewById(R.id.imageStudent);
//        Glide.with(getBaseActivity().getBaseContext())
//                .load(studentArrayList.get(position).imageURL)
//                .centerCrop()
//                .transform(new GlideCircleTransform(activity.getBaseContext()))
//                .error(R.drawable.account)
//                .into(myViewHolder.imageStudent);
    }
}
