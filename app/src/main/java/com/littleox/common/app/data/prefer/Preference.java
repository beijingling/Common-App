package com.littleox.common.app.data.prefer;

/**
 * Create by  lingbj in 2020/7/11
 */
public interface Preference {

    String getToken();

    void setToken(String token);

    int getCurrentUserId();

    void setCurrentUserId(int userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);
}
