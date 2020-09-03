package com.littleox.common.app.network;

import com.littleox.common.app.bean.GithubUser;
import com.littleox.common.app.bean.RepoInfo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lingbeijing on 2020/9/1.
 */
public interface ApiService {

    @GET("/users/{username}")
    Flowable<GithubUser> getUserInfo(@Path("username") String userName);

    @GET("/users/{username}/repos")
    Flowable<List<RepoInfo>> getUserRepos(@Path("username") String userName);

}
