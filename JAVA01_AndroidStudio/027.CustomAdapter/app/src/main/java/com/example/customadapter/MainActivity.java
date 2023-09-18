package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> Items = new ArrayList<Person>();
    private ListView list;
    private CustomAdapter adapter;

    //  데이터 만들기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person p;
        p = new Person(R.drawable.ic_launcher_foreground, "홍길동");
        Items.add(p);
        p = new Person(R.drawable.ic_launcher_foreground,"이순신");
        Items.add(p);
        p = new Person(R.drawable.ic_launcher_foreground,"강감찬");
        Items.add(p);

        list = findViewById(R.id.list);
        adapter = new CustomAdapter(getApplicationContext(), R.layout.custom,Items);
        list.setAdapter(adapter);


    }
}