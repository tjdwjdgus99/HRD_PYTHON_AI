package com.example.listviewtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lsitView;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] mid = {"히어로즈","24시", "로스트","로스트 룸","스몰빌"};

        lsitView = findViewById(R.id.listView);
//        adapter = ArrayAdapter.createFromResource(this,R.array.fruits, android.R.layout.simple_list_item_multiple_choice);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice,mid);    //  리스트 디자인
        lsitView.setAdapter(adapter);
        lsitView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lsitView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Toast.makeText(MainActivity.this, mid[index] +"을 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}