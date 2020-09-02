package com.littleox.common.app.base;

import android.content.Context;

import com.littleox.common.commonlib.baserx.RxManager;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public abstract class BasePresenter<V, M> {
    public Context mContext;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        mModel = m;
        mView = v;
    }

    public void onStart() {
    }

    public void onDestory() {
        mRxManager.unSubscribe();
    }
}
