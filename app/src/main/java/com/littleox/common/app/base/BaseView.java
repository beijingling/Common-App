package com.littleox.common.app.base;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public interface BaseView {

    void showLoading(String msg);
    void stopLoading();
    void showErrorTip(String msg);
}
