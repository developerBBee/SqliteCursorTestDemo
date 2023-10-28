package jp.developer.bbee.sqlitecursortestdemo.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jp.developer.bbee.sqlitecursortestdemo.model.Sample;

public class MyDao {
    private final SQLiteDatabase db;

    public MyDao(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertSampleByString(String string) {
        db.execSQL("INSERT INTO " + MyDb.SAMPLE_TBL + " (columnString) VALUES ('" + string + "')");
    }

    public List<Sample> getAllSamples() {
        List<Sample> list = new ArrayList<>();
        try (Cursor c = db.rawQuery("SELECT * FROM " + MyDb.SAMPLE_TBL + ";", null)) {
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
