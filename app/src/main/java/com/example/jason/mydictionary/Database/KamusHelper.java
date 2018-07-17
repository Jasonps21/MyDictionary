package com.example.jason.mydictionary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.jason.mydictionary.Model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.jason.mydictionary.Database.DatabaseContract.KamusColumns.ARTI;
import static com.example.jason.mydictionary.Database.DatabaseContract.KamusColumns.KATA;
import static com.example.jason.mydictionary.Database.DatabaseContract.TABLE_ENG_IND;
import static com.example.jason.mydictionary.Database.DatabaseContract.TABLE_IND_ENG;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    /*TABEL UNTUK ENGLISH TO INDONESIA*/
    public ArrayList<KamusModel> getAllDataENG_IND() {
        Cursor cursor = database.query(TABLE_ENG_IND, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert_ENG_IND(KamusModel kamusModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KATA, kamusModel.getKata());
        contentValues.put(ARTI, kamusModel.getArti());
        return database.insert(TABLE_ENG_IND, null, contentValues);
    }

    public void insertTransactionENG_IND(KamusModel kamusModel) {
        String sql = "INSERT INTO " + TABLE_ENG_IND + " (" + KATA + ", " + ARTI + ") VALUES(?,?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getKata());
        stmt.bindString(2, kamusModel.getArti());
        stmt.execute();
        stmt.clearBindings();
    }

//    public int update(KamusModel kamusModel) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(KATA, kamusModel.getKata());
//        contentValues.put(ARTI, kamusModel.getArti());
//        return database.update(TABLE_ENG_IND, contentValues, _ID + "= '" + kamusModel.getId() + "'", null);
//    }
//
//    public int delete(int id) {
//        return database.delete(TABLE_ENG_IND, _ID + "= '" + id + "'", null);
//    }

    public ArrayList<KamusModel> getDataByKataENG(String kata) {
        String result = "";
        Cursor cursor = database.query(TABLE_ENG_IND, null, KATA + " like '%"+kata+"%'",null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    /*AKHIR TABEL UNTUK ENGLISH TO INDONESIA*/

    /*TABEL UNTUK INDONESIA TO ENGLISH*/
    public ArrayList<KamusModel> getAllDataIND_ENG() {
        Cursor cursor = database.query(TABLE_IND_ENG, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert_IND_ENG(KamusModel kamusModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KATA, kamusModel.getKata());
        contentValues.put(ARTI, kamusModel.getArti());
        return database.insert(TABLE_IND_ENG, null, contentValues);
    }

    public void insertTransactionIND_ENG(KamusModel kamusModel) {
        String sql = "INSERT INTO " + TABLE_IND_ENG + " (" + KATA + ", " + ARTI + ") VALUES(?,?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getKata());
        stmt.bindString(2, kamusModel.getArti());
        stmt.execute();
        stmt.clearBindings();
    }

//    public int update_IND_ENG(KamusModel kamusModel) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(KATA, kamusModel.getKata());
//        contentValues.put(ARTI, kamusModel.getArti());
//        return database.update(TABLE_IND_ENG, contentValues, _ID + "= '" + kamusModel.getId() + "'", null);
//    }
//
//    public int delete_IND_ENG(int id) {
//        return database.delete(TABLE_IND_ENG, _ID + "= '" + id + "'", null);
//    }

    public ArrayList<KamusModel> getDataByKataIND(String kata) {
        String result = "";
        Cursor cursor = database.query(TABLE_IND_ENG, null, KATA + " like '%"+kata+"%'",null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    /*AKHIR TABEL UNTUK INDONESIA TO ENGLISH*/

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }
}
