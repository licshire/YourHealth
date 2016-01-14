package com.asiainfo.uuom.yourhealth.news.view;

/**
 * Created by uuom on 16-1-14.
 */
public interface NewsDetailView {
    void showAlertMessage(String s);

    void setHtmlView(String message);

    void showProgress();

    void hideProgress();
}
