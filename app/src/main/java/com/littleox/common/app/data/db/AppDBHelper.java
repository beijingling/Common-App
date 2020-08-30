package com.littleox.common.app.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Create by  lingbj in 2020/7/11
 */
public class AppDBHelper extends DaoMaster.OpenHelper{
    public AppDBHelper(Context context, String name) {
        super(context, name);
    }

    public AppDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
