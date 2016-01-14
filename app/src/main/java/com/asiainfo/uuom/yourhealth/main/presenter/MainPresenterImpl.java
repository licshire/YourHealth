package com.asiainfo.uuom.yourhealth.main.presenter;

import android.support.v4.app.Fragment;

import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.main.Model.MainModel;
import com.asiainfo.uuom.yourhealth.main.Model.MainModelImpl;
import com.asiainfo.uuom.yourhealth.main.view.MainView;
import com.asiainfo.uuom.yourhealth.news.widget.NewsFragment;

/**
 * Created by uuom on 16-1-12.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    @Override
    public void switchNavigation(int itemId) {
        Fragment fragment = null;
        switch (itemId){
            case R.id.navigation_item_home :
                fragment = NewsFragment.newInstance();
                break;
            case R.id.navigation_item_cook:
                break;
            case R.id.navigation_item_lore:
                break;
            case R.id.navigation_item_ask:
                break;
            case R.id.navigation_item_book:
                break;
            default:
                break;
        }
        mainView.switchNavigationView(fragment);
    }
}
