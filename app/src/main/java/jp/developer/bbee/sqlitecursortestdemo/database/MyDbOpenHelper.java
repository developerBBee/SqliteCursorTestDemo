package jp.developer.bbee.sqlitecursortestdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import jp.developer.bbee.sqlitecursortestdemo.model.Sample;

public class MyDbOpenHelper extends SQLiteOpenHelper {
    static final private String DB_NAME = "sample.db";
    static final private int DB_VERSION = 1;
    static final public String SAMPLE_TBL = "Sample";
    static final private String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + SAMPLE_TBL
            + " (columnInt INTEGER PRIMARY KEY AUTOINCREMENT, columnString TEXT)";

    public MyDbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @VisibleForTesting
    public MyDbOpenHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertSampleByString(SQLiteDatabase db, String string) {
        db.execSQL("INSERT INTO " + SAMPLE_TBL + " (columnString) VALUES ('" + string + "')");
    }

    public List<Sample> getAllSamples(SQLiteDatabase db) {
        List<Sample> list = new ArrayList<>();
        try (android.database.Cursor c = db.rawQuery("SELECT * FROM " + SAMPLE_TBL + ";", null)) {
            while (c.moveToNext()) {
                int indexInt = c.getColumnIndex("columnInt");
                int indexString = c.getColumnIndex("columnString");
                if (indexInt > -1 && indexString > -1) {
                    list.add(new Sample(c.getInt(indexInt), c.getString(indexString)));
                }
            }
        }

        return list;
    }
}
