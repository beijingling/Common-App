package com.littleox.common.app.base;

import android.content.Context;
import android.os.Bundle;

import com.littleox.common.app.utils.TUtils;
import com.littleox.common.commonlib.baserx.RxManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    private T mPresenter;
    private E mModel;

    private Unbinder bind;
    public Context mContext;
    public RxManager mRxManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        setContentView(getLayoutResID());
        bind = ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();
    }

    public abstract int getLayoutResID();

    public abstract void initPresenter();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        mRxManager.unSubscribe();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }
}
