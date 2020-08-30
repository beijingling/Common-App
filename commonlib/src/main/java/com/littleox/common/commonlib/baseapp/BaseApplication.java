package com.littleox.common.commonlib.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


/**
 * APPLICATION
 */
public class BaseApplication extends Application
{

    private static BaseApplication baseApplication;
    @Override
    public void onCreate()
    {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext()
    {
        return baseApplication;
    }

    public static Resources getAppResources()
    {
        return baseApplication.getResources();
    }

    public static synchronized BaseApplication getInstance()
    {
        return baseApplication;
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }

    /**
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
    }
}
