package com.asiainfo.uuom.yourhealth.news.presenter;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.model.NewsListModel;
import com.asiainfo.uuom.yourhealth.news.model.NewsListModelImpl;
import com.asiainfo.uuom.yourhealth.news.view.NewsListView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsListPresenterImpl implements NewsListPresenter {

    private NewsListView mNewsListView;
    private NewsListModel mNewsListModel;

    public NewsListPresenterImpl(NewsListView mNewsListView) {
        this.mNewsListView = mNewsListView;
        mNewsListModel = new NewsListModelImpl();
    }

    @Override
    public void loadNews(int pageNo, int classId) {
        if (pageNo == 1){
            mNewsListView.showProgress();
        }
        mNewsListModel.getNews (pageNo, classId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<NewsBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mNewsListView.showAlertMessage(e.toString());
                }

                @Override
                public void onNext(List<NewsBean> newsBeans) {
                    mNewsListView.hideProgress();
                    mNewsListView.addNewsData(newsBeans);
                }
            });

    }
}
