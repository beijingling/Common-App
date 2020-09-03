package com.littleox.common.app;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.littleox.common.app.base.BaseActivity;
import com.littleox.common.app.bean.GithubUser;
import com.littleox.common.app.bean.RepoInfo;
import com.littleox.common.app.network.WebApi;
import com.littleox.common.commonlib.baserx.RxSubscriber;
import com.littleox.common.commonlib.baserx.RxUtil;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_avatar)
    ImageView user_avatar;
    @BindView(R.id.user_name)
    TextView user_name;

    //https://github.com/fluidicon.png github 图片
    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        mRxManager.addSubscribe(WebApi.getInstance().getService().getUserInfo("beijingling").compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<GithubUser>(this) {
            @Override
            protected void _onNext(GithubUser githubUser) {
                if(!TextUtils.isEmpty(githubUser.getMessage())){
                    user_name.setText(githubUser.getMessage());
                    return;
                }
                user_name.setText(githubUser.getLogin());
                Glide.with(mContext).asBitmap().load(githubUser.getAvatar_url()).diskCacheStrategy(DiskCacheStrategy.ALL).into(user_avatar);
                toolbar.setTitle(githubUser.getName());
            }

            @Override
            protected void _onError(String message) {

            }
        }));
        getRepos();
    }

    public void getRepos(){
        mRxManager.addSubscribe(WebApi.getInstance().getService().getUserRepos("beijingling").compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<List<RepoInfo>>(mContext) {
                    @Override
                    protected void _onNext(List<RepoInfo> repoInfos) {
                        
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }
}
