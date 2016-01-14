package com.asiainfo.uuom.yourhealth.news.presenter;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.model.NewsDetailModel;
import com.asiainfo.uuom.yourhealth.news.model.NewsDetailModelImpl;
import com.asiainfo.uuom.yourhealth.news.view.NewsDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by uuom on 16-1-14.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private NewsDetailView mNewsDetailView;
    private NewsDetailModel mNewsDetailModel;

    public NewsDetailPresenterImpl(NewsDetailView mNewsDetailView) {
        this.mNewsDetailView = mNewsDetailView;
        mNewsDetailModel = new NewsDetailModelImpl();
    }

    @Override
    public void loadNewsDetail(NewsBean news) {
        mNewsDetailView.showProgress();
        mNewsDetailModel.getNewsDetail(news.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mNewsDetailView.showAlertMessage(e.toString());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        mNewsDetailView.setHtmlView(newsBean.getMessage());
                    }
                });
        mNewsDetailView.hideProgress();
    }
}
