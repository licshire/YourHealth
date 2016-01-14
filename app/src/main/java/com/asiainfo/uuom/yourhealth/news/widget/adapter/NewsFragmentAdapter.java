package com.asiainfo.uuom.yourhealth.news.widget.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.ClassBean;
import com.asiainfo.uuom.yourhealth.news.widget.NewsListFragment;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {

    private ClassBean[] classes = Constant.getNewsClass();

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public Fragment getItem(int position) {
        ClassBean clazz = classes[position];
        return NewsListFragment.newInstance(clazz.getId());
    }
}
