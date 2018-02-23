package com.shop.didi.didishop;

import android.app.Application;
import android.content.Context;

/**
 * Author: ydy
 * Created: 2017/7/13 15:46
 * Description:
 */

public class MyApplication extends Application {

    private static MyApplication instance = null;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (MyApplication) getApplicationContext();
        context = this;
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    public static Context getContext() {
        return context;
    }

}
