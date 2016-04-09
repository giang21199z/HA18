package com.giangnd_svmc.ha18.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 4/9/2016.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResIdContentView(), container, false);
        this.onCreateContentView(rootView, savedInstanceState);
        return rootView;
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            getBaseActivity().onCloseActivity();
        }
    }

    public void replaceFragment(BaseFragment fragment, String fragmentTag, String transactionTag) {
        getBaseActivity().replaceFragment(fragment, fragmentTag, transactionTag);
    }

    public BaseActivity getBaseActivity() {
        BaseActivity activity = (BaseActivity) getActivity();
        return activity;
    }

    protected abstract int getLayoutResIdContentView();

    protected abstract void onCreateContentView(View rootView, Bundle savedInstanceState);

}
