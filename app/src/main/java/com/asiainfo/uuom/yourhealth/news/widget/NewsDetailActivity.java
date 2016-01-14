package com.asiainfo.uuom.yourhealth.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.api.Constant;
import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.asiainfo.uuom.yourhealth.news.presenter.NewsDetailPresenter;
import com.asiainfo.uuom.yourhealth.news.presenter.NewsDetailPresenterImpl;
import com.asiainfo.uuom.yourhealth.news.view.NewsDetailView;
import com.asiainfo.uuom.yourhealth.util.ImageLoaderUtils;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView {

    @Bind(R.id.ivImage)
    ImageView ivImage;

    @Bind(R.id.progress)
    ProgressBar progress;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    HtmlTextView htNewsContent;
    private NewsBean news;
    private NewsDetailPresenter mNewsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        news = (NewsBean) getIntent().getExtras().getSerializable("news");
        ButterKnife.bind(this);
        htNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        collapsing_toolbar.setTitle(news.getTitle());

        mNewsDetailPresenter = new NewsDetailPresenterImpl(this);
        mNewsDetailPresenter.loadNewsDetail(news);

        ImageLoaderUtils.display(this,ivImage, Constant.NEWS_IMAGE_PREFIX+news.getImg());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAlertMessage(String msg) {
        Snackbar.make(findViewById(R.id.coorDinator), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHtmlView(String message) {
        htNewsContent.setHtmlFromString(message, new HtmlTextView.RemoteImageGetter());
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
