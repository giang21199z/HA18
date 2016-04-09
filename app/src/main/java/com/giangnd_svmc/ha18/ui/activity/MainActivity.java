package com.giangnd_svmc.ha18.ui.activity;

import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseActivity;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.ui.fragment.MyClassesFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    public TextView mTitle;
    public DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreateContentView() {
        setupToolBar();
        setupMenu();
        setLockMenu(true);
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


    @Override
    protected BaseFragment getFragmentContent() {
        return new MyClassesFragment();
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
