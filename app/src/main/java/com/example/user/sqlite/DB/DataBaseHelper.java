package com.example.user.sqlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.sqlite.Config;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String dbName = Config.DB_NAME;
    public static int dbVersion = 2;

    public DataBaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Config.COMMAND_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Config.COMMAND_DELETE);
        this.onCreate(db);
    }
}
