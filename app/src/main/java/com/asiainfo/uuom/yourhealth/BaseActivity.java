package com.asiainfo.uuom.yourhealth;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by uuom on 16-1-12.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private Context appContext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }



    public Context getAppContext() {
        return AppContext.getAppContext();
    }
}
