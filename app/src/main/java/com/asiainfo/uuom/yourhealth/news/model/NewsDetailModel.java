package com.asiainfo.uuom.yourhealth.news.model;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;

import rx.Observable;

/**
 * Created by uuom on 16-1-14.
 */
public interface NewsDetailModel {
    Observable<NewsBean> getNewsDetail(int id);
}
