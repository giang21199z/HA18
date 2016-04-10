package com.giangnd_svmc.ha18.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseActivity;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.MyUtils;
import com.giangnd_svmc.ha18.entity.Teacher;
import com.giangnd_svmc.ha18.glide.GlideCircleTransform;
import com.giangnd_svmc.ha18.ui.fragment.ClassFragment;
import com.giangnd_svmc.ha18.ui.fragment.ListClassFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    public DrawerLayout drawer;
    private NavigationView navigationView;
    public Teacher teacher = new Teacher();
    public ImageView ivTeacher;
    public TextView tvTeacher;
    public TextView tvEmail;

    @Override
    protected void onCreateContentView() {
        teacher = (Teacher) getIntent().getSerializableExtra(MyUtils.TAG_TEACHER);

        setupToolBar();
        setupMenu();
        setLockMenu(true);
        setupProfileTeacher();


    }

    private void setupProfileTeacher() {
        View headerLayout =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        tvTeacher = (TextView) headerLayout.findViewById(R.id.tv_nameTeacher);
        tvEmail = (TextView) headerLayout.findViewById(R.id.textViewEmailTeacher);
        ivTeacher = (ImageView) headerLayout.findViewById(R.id.imageViewTeacher);
        tvTeacher.setText(teacher.name + "");
        tvEmail.setText(teacher.email + "");
        Log.e("TAG", teacher.getURLImage(), null);
        Glide.with(this)
                .load(teacher.getURLImage())
                .centerCrop()
                .transform(new GlideCircleTransform(this))
                .error(R.drawable.account)
                .into(ivTeacher);
    }

    public void closeMenu() {
        drawer.closeDrawers();
    }

    public void openMenu() {
        drawer.openDrawer(GravityCompat.START);
    }

    public void setSelectedMenu(int id) {
        navigationView.setCheckedItem(id);
    }

    public void setSelectedItemMenu(int id) {
        setSelectedMenu(id);

    }

    public void setLockMenu(boolean isLock) {
        if (isLock) drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        else drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void setupMenu() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setSelectedItemMenu(R.id.classes);
    }

    public void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(teacher.name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawers();
        else {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                BaseFragment currentFragment = getCurrentFragment();
                if (currentFragment != null) {
                    currentFragment.onBackPressed();
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }


    @Override
    protected BaseFragment getFragmentContent() {
        return new ListClassFragment(teacher);
    }

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (!item.isChecked()) {
            switch (id) {

            }

            setSelectedItemMenu(id);
        }
        closeMenu();
        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
