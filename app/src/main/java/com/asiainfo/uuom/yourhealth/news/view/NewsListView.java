package com.asiainfo.uuom.yourhealth.news.view;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;

import java.util.List;

/**
 * Created by uuom on 16-1-13.
 */
public interface NewsListView {
    void showProgress();

    void showAlertMessage(String msg);

    void hideProgress();

    void addNewsData(List<NewsBean> newsBeans);
}
