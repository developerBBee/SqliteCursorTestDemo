package jp.developer.bbee.sqlitecursortestdemo.database;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.developer.bbee.sqlitecursortestdemo.model.Sample;

@RunWith(AndroidJUnit4.class)
public class MyDbOpenHelperTest {
    private SQLiteDatabase db;
    private MyDbOpenHelper dbHelper;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = new MyDbOpenHelper(context, null);
        db = dbHelper.getWritableDatabase();
    }


    @Test
    public void test_InsertAndGetSample_Database_Success() {
        Sample expect1 = new Sample(1, "test1");
        Sample expect2 = new Sample(2, "test2");
        Sample expect3 = new Sample(3, "test3");
        List<Sample> expectList = new ArrayList<>();
        expectList.add(expect1);
        expectList.add(expect2);
        expectList.add(expect3);

        // "3"件投入
        dbHelper.insertSampleByString(db, expect1.getColumnString());
        dbHelper.insertSampleByString(db, expect2.getColumnString());
        dbHelper.insertSampleByString(db, expect3.getColumnString());

        List<Sample> actualList = dbHelper.getAllSamples(db);

        // 取得したactualListが"3"件であることを確認
        assertThat(actualList.size()).isEqualTo(3);

        // columnIntをキーとしてexpectのListをMapに変換
        Map<Integer, Sample> expect = new HashMap<>();
        expectList.forEach(sample -> expect.put(sample.getColumnInt(), sample));

        // actualのcolumnIntでexpectのキーを指定して、expectの要素を取得し、actualと比較
        actualList.forEach(actual -> {
            Sample expectSample = expect.get(actual.getColumnInt());
            assertThat(expectSample).isNotNull();
            String expectString = expectSample.getColumnString();
            assertThat(expectString).isEqualTo(actual.getColumnString());
        });
    }

    @Test
    public void test_InsertAndGetSample_Database_Failure() {
        // キーのauto-generated が"1"から始まっているか確認
        // "4"から初めて"3"件投入で、キーが不一致になるかのテスト
        Sample expect1 = new Sample(4, "test4");
        Sample expect2 = new Sample(5, "test5");
        Sample expect3 = new Sample(6, "test6");
        List<Sample> expectList = new ArrayList<>();
        expectList.add(expect1);
        expectList.add(expect2);
        expectList.add(expect3);

        // "3"件投入
        dbHelper.insertSampleByString(db, expect1.getColumnString());
        dbHelper.insertSampleByString(db, expect2.getColumnString());
        dbHelper.insertSampleByString(db, expect3.getColumnString());

        List<Sample> actualList = dbHelper.getAllSamples(db);

        // 取得したactualListが"3"件であることを確認
        assertThat(actualList.size()).isEqualTo(3);

        // columnIntをキーとしてexpectのListをMapに変換
        Map<Integer, Sample> expect = new HashMap<>();
        expectList.forEach(sample -> expect.put(sample.getColumnInt(), sample));

        // 一致するキーが存在しないはずなので、nullであることを確認する
        actualList.forEach(actual -> {
            Sample expectSample = expect.get(actual.getColumnInt());
            assertThat(expectSample).isNull();
        });
    }

    @After
    public void tearDown() {
        db.close();
    }
}