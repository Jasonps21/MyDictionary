package com.example.jason.mydictionary.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.jason.mydictionary.Database.DatabaseContract.KamusColumns.ARTI;
import static com.example.jason.mydictionary.Database.DatabaseContract.KamusColumns.KATA;
import static com.example.jason.mydictionary.Database.DatabaseContract.TABLE_ENG_IND;
import static com.example.jason.mydictionary.Database.DatabaseContract.TABLE_IND_ENG;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbkamus";
    private static int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENG_IND = "create table " + TABLE_ENG_IND +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI + " text not null);";

    public static String CREATE_TABLE_IND_ENG = "create table " + TABLE_IND_ENG +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ENG_IND);
        sqLiteDatabase.execSQL(CREATE_TABLE_IND_ENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_ENG_IND);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_IND_ENG);
        onCreate(sqLiteDatabase);
    }
}
