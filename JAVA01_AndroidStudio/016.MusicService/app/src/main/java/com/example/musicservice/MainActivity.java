package com.example.musicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            MainActivity.this.startService(intent);
        });

        btnStop.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            MainActivity.this.stopService(intent);
        });

    }
}