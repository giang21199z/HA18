package com.giangnd_svmc.ha18.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.giangnd_svmc.ha18.R;


public abstract class BaseActivity extends AppCompatActivity {

    private int fragmentContainerId;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResIdContentView());
        fragmentContainerId = getFragmentContainerId();
        onCreateContentView();
        addFragmentContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addFragmentContent() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            BaseFragment fragment = getFragmentContent();
            transaction.add(fragmentContainerId, fragment, fragment.getClass().getName());
            transaction.commit();
        } else {
            popToFirstFragment();
        }
    }

    @Override
    public void onBackPressed() {
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


    public BaseFragment getCurrentFragment() {
        BaseFragment currentFragment = (BaseFragment) getFragmentManager().findFragmentById(fragmentContainerId);
        return currentFragment;
    }

    public void popFragment() {
        onBackPressed();
    }

    public void popToFirstFragment() {
        getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void replaceFragment(BaseFragment fragment, String fragmentTag, String transactionTag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);
        transaction.replace(fragmentContainerId, fragment, fragmentTag).addToBackStack(transactionTag);
        transaction.commit();
    }

    public void onCloseActivity() {
        this.finish();
    }

    protected abstract int getLayoutResIdContentView();

    protected abstract void onCreateContentView();

    protected abstract int getFragmentContainerId();

    protected abstract BaseFragment getFragmentContent();

}
