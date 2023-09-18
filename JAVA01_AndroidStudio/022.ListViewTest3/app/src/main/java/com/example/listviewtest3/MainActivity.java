package com.example.listviewtest3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private EditText edtItem;
    private Button btnadd;
    private ArrayList<String> midList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  기본 과일
        fruits();
        //  연결
        list = findViewById(R.id.listView1);
        edtItem = findViewById(R.id.edtItem);
        btnadd = findViewById(R.id.btnAdd);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);
        //  추가
        addClickListener();
        //  길게 삭제
        delectLongClickListener();

    }
    private void fruits(){
        midList.add("사과");
        midList.add("배");
        midList.add("딸기");
        midList.add("포도");
        midList.add("수박");
    }
    private void addClickListener(){
        btnadd.setOnClickListener(view -> {
            midList.add(edtItem.getText().toString());
            adapter.notifyDataSetChanged();
        });
    }
    private void delectLongClickListener(){
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int positions, long id) {
                midList.remove(positions);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}