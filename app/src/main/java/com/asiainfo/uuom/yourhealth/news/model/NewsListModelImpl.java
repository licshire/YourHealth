package com.asiainfo.uuom.yourhealth.news.model;

import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.util.NewsJsonUtil;
import com.asiainfo.uuom.yourhealth.util.LogUtils;
import com.asiainfo.uuom.yourhealth.util.OkHttpUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsListModelImpl implements NewsListModel {

    @Override
    public Observable<List<NewsBean>> getNews(final int pageNo, final int classId) {

        Observable<List<NewsBean>> observable = Observable.create(new Observable.OnSubscribe<List<NewsBean>>() {

            @Override
            public void call(final Subscriber<? super List<NewsBean>> subscriber) {

                StringBuilder stringBuilder = new StringBuilder(Constant.URL_NEWS_LIST);
                stringBuilder.append("?page=" + pageNo);
                stringBuilder.append("&limit=" + Constant.NEWS_LIMIT);
                stringBuilder.append("&type=" + Constant.NEWS_TYPE_ID);
                stringBuilder.append("&id=" + classId);

                LogUtils.d("loadNews-url",stringBuilder.toString());

                Request request = new Request.Builder()
                        .get()
                        .url(stringBuilder.toString())
                        .addHeader("accept", "application/json")
                        .addHeader("content-type", "application/json")
                        .addHeader("apix-key",Constant.NEWS_APIX_KEY)
                        .build();
                try {
                    Response response = OkHttpUtils.getmInstance().getmOkHttpClient().newCall(request).execute();
                    List<NewsBean> list = NewsJsonUtil.parseJsonToBean(response.body().string());
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });

        return observable;
    }
}
