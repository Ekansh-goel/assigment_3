package com.example.assignment_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelperDemo extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "logindata";
    public static final int DATABASE_VIRSION = 1;

    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String COURSE = "course";
    public static final String BLOOD = "blood";
    public static final String MAIL = "mail";
    public static final String MOBILE = "mobile";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + COURSE + " TEXT," + BLOOD + " TEXT," + MAIL + " TEXT," + MOBILE + " TEXT," + PASSWORD + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDataBaseHelperDemo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VIRSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
}
