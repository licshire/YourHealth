package com.asiainfo.uuom.yourhealth.news.model;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;

import java.util.List;

import rx.Observable;

/**
 * Created by uuom on 16-1-13.
 */
public interface NewsListModel {
    Observable<List<NewsBean>> getNews(int pageNo, int classId);
}
