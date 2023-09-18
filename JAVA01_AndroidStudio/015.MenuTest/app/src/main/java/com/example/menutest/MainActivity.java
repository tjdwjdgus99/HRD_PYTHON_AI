package com.example.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private View menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        menu = findViewById(R.id.menu);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuexample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItem_1) {
            Toast.makeText(this, "빨강", Toast.LENGTH_SHORT).show();
            menu.setBackgroundColor(Color.rgb(255,0,0));
            return true;
        }else if (id == R.id.menuItem_2) {
            Toast.makeText(this, "초록", Toast.LENGTH_SHORT).show();
            menu.setBackgroundColor(Color.rgb(0,255,0));
            return true;
        }else if (id == R.id.menuItem_3) {
            Toast.makeText(this, "파랑", Toast.LENGTH_SHORT).show();
            menu.setBackgroundColor(Color.rgb(0,0,255));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}