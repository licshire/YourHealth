package com.asiainfo.uuom.yourhealth.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.presenter.NewsListPresenter;
import com.asiainfo.uuom.yourhealth.news.presenter.NewsListPresenterImpl;
import com.asiainfo.uuom.yourhealth.news.view.NewsListView;
import com.asiainfo.uuom.yourhealth.news.widget.adapter.NewsItemAdapter;
import com.asiainfo.uuom.yourhealth.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsListFragment extends Fragment implements NewsListView, SwipeRefreshLayout.OnRefreshListener {

    private int classId;

    private NewsListPresenter mNewsListPresenter;

    @Bind(R.id.rv_newsList)
    RecyclerView mRecyclerView;
    @Bind(R.id.wrl_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager mLinearLayoutManager;
    private NewsItemAdapter mNewsItemAdapter;
    private static final String TAG = NewsListFragment.class.getSimpleName();
    private int currentPageNo = 1;
    private List<NewsBean> dataList;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) { //每次预加载时会调用一次，在真正显示的时候还会调用一次。
        LogUtils.e(TAG, "setUserVisibleHint---"+this);
        if (isVisibleToUser && isVisible()){
            onRefresh();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classId = getArguments().getInt(Constant.ARGUMENT_CLASS_ID,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,null,false);
        ButterKnife.bind(this,view);

        LogUtils.e(TAG, "onCreateView---" + this);

        initValue();
        initView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.e(TAG, "onActivityCreated---" + this);
        //在预加载第一个Fragment时，该Fragment并没有显示，setUserVisibleHint()中的 判断条件不成立
        //所以没有数据，需要在这里加载数据
        if (getUserVisibleHint()){
            onRefresh();
        }
        super.onActivityCreated(savedInstanceState);
    }

    private void initValue() {
        mNewsListPresenter = new NewsListPresenterImpl(this);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mNewsItemAdapter = new NewsItemAdapter(getContext());
    }

    private void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mNewsItemAdapter.setItemClickListener(new NewsItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                NewsBean news = dataList.get(position);
                Intent intent = new Intent(NewsListFragment.this.getActivity(),NewsDetailActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("news",news);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mNewsItemAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem == mNewsItemAdapter.getItemCount() - 1) {
                    //加载更多
                    LogUtils.d(TAG, "loading more data");
                    currentPageNo++;
                    mNewsListPresenter.loadNews(currentPageNo, classId);
                }
            }
        });

    }


    public static NewsListFragment newInstance(int classId){
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.ARGUMENT_CLASS_ID, classId);
        newsListFragment.setArguments(args);
        return newsListFragment;
    }

    @Override
    public void onRefresh() {
        if (dataList != null){
            dataList.clear();
        }
        mNewsListPresenter.loadNews(1,classId);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void showAlertMessage(String msg) {
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.dl_drawerLayout);
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void addNewsData(List<NewsBean> newsBeans) {
        if (dataList == null){
            dataList = new ArrayList<>();
        }
        dataList.addAll(newsBeans);
        mNewsItemAdapter.setDatas(dataList.toArray());
    }

}
