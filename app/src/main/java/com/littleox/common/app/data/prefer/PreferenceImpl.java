package com.littleox.common.app.data.prefer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Create by  lingbj in 2020/7/11
 */
public class PreferenceImpl implements Preference {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_PREF_FILE_NAME = "PREF_KEY_CURRENT_USER";
    private Context mContext;
    private SharedPreferences mPrefs;
    public PreferenceImpl(Context ctx) {
        mContext = ctx;
        mPrefs = mContext.getSharedPreferences(PREF_KEY_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        assert (mPrefs != null);
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }

    @Override
    public int getCurrentUserId() {
        return mPrefs.getInt(PREF_KEY_CURRENT_USER_ID, 0);
    }

    @Override
    public void setCurrentUserId(int userId) {
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }


    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, "");
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

}
