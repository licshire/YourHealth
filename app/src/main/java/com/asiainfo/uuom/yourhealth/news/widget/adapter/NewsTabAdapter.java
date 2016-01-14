package com.asiainfo.uuom.yourhealth.news.widget.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.ClassBean;

/**
 * Created by uuom on 16-1-12.
 */
public class NewsTabAdapter extends PagerAdapter{

    private ClassBean[] classes = Constant.getNewsClass();

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classes[position].getName();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
