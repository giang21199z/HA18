package com.giangnd_svmc.ha18.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasePage {
    protected BaseActivity activity;
    protected View content;

    public BasePage(BaseActivity activity) {
        this(activity, null);
    }


    public BasePage(BaseActivity activity, ViewGroup parent) {
        this.activity = activity;
        if (parent != null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            content = inflater.inflate(getContentId(), parent, false);
        } else {
            content = View.inflate(activity, getContentId(), null);
        }

    }

    protected abstract int getContentId();

    public View getContent() {
        return content;
    }

    public void destroy() {
    }

}
