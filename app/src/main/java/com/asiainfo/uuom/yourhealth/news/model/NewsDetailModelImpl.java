package com.asiainfo.uuom.yourhealth.news.model;

import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.util.NewsJsonUtil;
import com.asiainfo.uuom.yourhealth.util.OkHttpUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by uuom on 16-1-14.
 */
public class NewsDetailModelImpl implements NewsDetailModel {

    @Override
    public Observable<NewsBean> getNewsDetail(final int id) {
        Observable<NewsBean> observable = Observable.create(new Observable.OnSubscribe<NewsBean>() {
            @Override
            public void call(Subscriber<? super NewsBean> subscriber) {
                try {
                    StringBuilder stringBuilder = new StringBuilder(Constant.URL_NEWS_DETAIL);
                    stringBuilder.append("?id=" + id);

                    Request request = new Request.Builder()
                            .get()
                            .url(stringBuilder.toString())
                            .addHeader("accept", "application/json")
                            .addHeader("content-type", "application/json")
                            .addHeader("apix-key", Constant.NEWS_APIX_KEY)
                            .build();

                    String json = OkHttpUtils.getmInstance().getmOkHttpClient().newCall(request).execute().body().string();

                    NewsBean news = NewsJsonUtil.parseNewsDetailJsonToBean(json);
                    subscriber.onNext(news);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
        return observable;
    }
}
