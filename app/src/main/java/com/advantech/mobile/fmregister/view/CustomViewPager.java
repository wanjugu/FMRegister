package com.advantech.mobile.fmregister.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ADVANTECH CONSULTING on 4/23/2018.
 */

public class CustomViewPager extends ViewPager {

    public boolean disable = false;



    public CustomViewPager(Context context,AttributeSet attrs) {
        super(context,attrs);
    }



    public CustomViewPager(Context context) {
        super(context);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return disable ? false : super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return disable ? false : super.onInterceptTouchEvent(event);
    }

    public void disableScroll(boolean disable) {
        this.disable = disable;
    }


}
