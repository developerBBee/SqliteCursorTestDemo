package jp.developer.bbee.sqlitecursortestdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDb {
    static SQLiteDatabase DB_INSTANCE = null;

    static final private String DB_NAME = "sample.db";
    static final public String SAMPLE_TBL = "Sample";
    static final private String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + SAMPLE_TBL
            + " (columnInt INTEGER PRIMARY KEY AUTOINCREMENT, columnString TEXT)";

    public static SQLiteDatabase getDatabase(Context context) {
        if (DB_INSTANCE == null) {
            DB_INSTANCE = initDatabase(context);
        }
        return DB_INSTANCE;
    }

    private static SQLiteDatabase initDatabase(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE_SQL);
        return db;
    }
}
