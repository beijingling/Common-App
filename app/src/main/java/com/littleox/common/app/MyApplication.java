package com.littleox.common.app;

import com.littleox.common.commonlib.baseapp.BaseApplication;
import com.littleox.common.commonlib.utils.LogUtils;

/**
 * Created by lingbeijing on 2020/9/1.
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(BuildConfig.DEBUG);
    }
}
