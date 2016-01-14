package com.asiainfo.uuom.yourhealth.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.news.widget.adapter.NewsFragmentAdapter;
import com.asiainfo.uuom.yourhealth.news.widget.adapter.NewsTabAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by uuom on 16-1-12.
 */
public class NewsFragment extends Fragment {

    @Bind(R.id.tl_tabLayout) TabLayout mTabLayout;
    @Bind(R.id.vp_viewPager) ViewPager mViewPager;

    private NewsTabAdapter mNewsTabAdapter;
    private NewsFragmentAdapter mNewsFragmentAdapter;

    public static NewsFragment newInstance(){
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null,false);
        ButterKnife.bind(this, view);

        initValue();
        initView();
        
        return view;
    }

    private void initValue() {
        mNewsTabAdapter = new NewsTabAdapter();
        mNewsFragmentAdapter = new NewsFragmentAdapter(getFragmentManager());
    }

    private void initView() {
        mViewPager.setAdapter(mNewsFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mNewsTabAdapter);
    }
}
