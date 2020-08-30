package com.littleox.common.app.data;

import android.content.Context;

import com.littleox.common.app.data.db.DBHelper;
import com.littleox.common.app.data.db.DBHelperImpl;
import com.littleox.common.app.data.prefer.Preference;
import com.littleox.common.app.data.prefer.PreferenceImpl;

/**
 * Create by  lingbj in 2020/7/11
 */
public class DataManager {
    private static volatile DataManager instance ;

    private DBHelper dbHelper;
    private Preference preference;

    private DataManager(Context ctx){
        dbHelper = new DBHelperImpl();
        preference = new PreferenceImpl(ctx);
    }

    public static DataManager getInstance(Context ctx) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null)
                    instance = new DataManager(ctx);
            }
        }
        return instance;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public Preference getPreference() {
        return preference;
    }
}
