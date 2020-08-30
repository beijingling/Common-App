package com.littleox.common.app.data.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by  lingbj in 2020/7/11
 */
@Entity
public class KeyHistory {
    @Id
    private Long _id;
    int time;
    String key;
    @Generated(hash = 730435737)
    public KeyHistory(Long _id, int time, String key) {
        this._id = _id;
        this.time = time;
        this.key = key;
    }
    @Generated(hash = 1673933113)
    public KeyHistory() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public int getTime() {
        return this.time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
