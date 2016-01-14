package com.asiainfo.uuom.yourhealth;

import android.app.Application;
import android.content.Context;

/**
 * Created by uuom on 16-1-12.
 */
public class AppContext extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        
        appInit();
    }

    private void appInit() {

    }

    public static Context getAppContext() {
        return appContext;
    }
}
