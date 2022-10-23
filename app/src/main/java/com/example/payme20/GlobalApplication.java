package com.example.payme20;

import android.app.Application;
import android.content.Context;

/**
 * This class is used to get the ApplicationContext for the program
 */
public class GlobalApplication extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
    public static Context getAppContext() {
        return appContext;
    }
}
