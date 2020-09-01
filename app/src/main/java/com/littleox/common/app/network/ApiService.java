package com.littleox.common.app.network;

import com.littleox.common.app.bean.GithubUser;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by lingbeijing on 2020/9/1.
 */
public interface ApiService {

    @GET("/user/octokit")
    Flowable<GithubUser> getUserOctokit();

}
