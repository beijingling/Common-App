package com.littleox.common.app.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public class RepoInfo {
    private String id;
    private String node_id;
    private String name;
    @SerializedName("private")
    private boolean isPrivate;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String git_url;
//    private String id;
//    private String id;
//    private String id;
//    private String id;
//    private String id;
//    private String id;
    
}
