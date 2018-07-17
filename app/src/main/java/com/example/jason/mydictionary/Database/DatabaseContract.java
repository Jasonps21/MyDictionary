package com.example.jason.mydictionary.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_ENG_IND = "tabel_eng_ind";
    public static String TABLE_IND_ENG = "tabel_ind_eng";

    public static final class KamusColumns implements BaseColumns {
        public static String KATA = "kata";
        public static String ARTI = "arti";
    }
}
