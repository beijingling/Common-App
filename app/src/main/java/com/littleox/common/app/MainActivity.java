package com.littleox.common.app;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.littleox.common.app.base.BaseActivity;
import com.littleox.common.app.bean.GithubUser;
import com.littleox.common.app.network.WebApi;
import com.littleox.common.commonlib.baserx.RxSubscriber;
import com.littleox.common.commonlib.baserx.RxUtil;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_avatar)
    ImageView user_avatar;
    @BindView(R.id.user_name)
    TextView user_name;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        mRxManager.addSubscribe(WebApi.getInstance().getService().getUserInfo("octokit").compose(RxUtil.rxSchedulerHelper()).subscribeWith(new RxSubscriber<GithubUser>(this) {
            @Override
            protected void _onNext(GithubUser githubUser) {
                if(TextUtils.isEmpty(githubUser.getMessage())){
                    user_name.setText(githubUser.getMessage());
                    return;
                }
                user_name.setText(githubUser.getLogin());
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

}
