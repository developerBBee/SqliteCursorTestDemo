package jp.developer.bbee.sqlitecursortestdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.developer.bbee.sqlitecursortestdemo.database.MyDao;
import jp.developer.bbee.sqlitecursortestdemo.database.MyDb;
import jp.developer.bbee.sqlitecursortestdemo.database.MyDbOpenHelper;
import jp.developer.bbee.sqlitecursortestdemo.databinding.ActivityMainBinding;
import jp.developer.bbee.sqlitecursortestdemo.model.Sample;

public class MainActivity extends AppCompatActivity {
    private MyDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SQLiteDatabase db = MyDb.getDatabase(this);
        dao = new MyDao(db);

        binding.button.setOnClickListener(v -> {
            String nameText = binding.name.getText().toString();

            if (!nameText.isEmpty()) {
                dao.insertSampleByString(nameText);
            }

            List<Map<String, String>> names = getNames(dao.getAllSamples());
            SimpleAdapter adapter = new SimpleAdapter(this, names, android.R.layout.simple_list_item_1,
                    new String[]{"name"}, new int[]{android.R.id.text1});
            binding.listView.setAdapter(adapter);
        });
    }

    private List<Map<String, String>> getNames(List<Sample> samples) {
        List<Map<String, String>> names = new ArrayList<>();

        for (Sample sample : samples) {
            Map<String, String> name = new HashMap<>();
            name.put("name", sample.getColumnString());
            names.add(name);
        }

        return names;
    }
}