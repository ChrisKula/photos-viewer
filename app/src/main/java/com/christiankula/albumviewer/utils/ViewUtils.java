package com.christiankula.albumviewer.utils;


import android.view.View;

public class ViewUtils {
    private ViewUtils() {

    }

    public static void setViewVisibility(View v, boolean visibility) {
        v.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }
}
