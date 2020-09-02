package com.littleox.common.app.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.littleox.common.app.utils.TUtils;
import com.littleox.common.commonlib.baserx.RxManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

    private T mPresenter;
    private E mModel;
    protected View rootView;

    private Unbinder bind;
    public Context mContext;
    public RxManager mRxManager;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(getLayoutResID(), container,false);
        }
        bind = ButterKnife.bind(this,rootView);
        mRxManager = new RxManager();
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        mContext = getContext();
        if (mPresenter != null) {
            mPresenter.mContext = getContext();
        }
        this.initPresenter();
        this.initView();
        return rootView;
    }

    public abstract int getLayoutResID();


    public abstract void initPresenter();

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
        bind.unbind();
        mRxManager.unSubscribe();
    }

}
