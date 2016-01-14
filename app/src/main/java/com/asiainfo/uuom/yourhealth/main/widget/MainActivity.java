package com.asiainfo.uuom.yourhealth.main.widget;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.asiainfo.uuom.yourhealth.BaseActivity;
import com.asiainfo.uuom.yourhealth.R;
import com.asiainfo.uuom.yourhealth.main.presenter.MainPresenter;
import com.asiainfo.uuom.yourhealth.main.presenter.MainPresenterImpl;
import com.asiainfo.uuom.yourhealth.main.view.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener ,MainView {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.dl_drawerLayout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nv_navigationView) NavigationView mNavigationView;

    private MainPresenter mainPresenter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initValue();
        initView();

        mainPresenter.switchNavigation(R.id.navigation_item_home);
    }

    protected void initValue() {
        mainPresenter = new MainPresenterImpl(this);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
    }

    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mainPresenter.switchNavigation(item.getItemId());
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void switchNavigationView(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameLayout, fragment).commit();
    }
}
