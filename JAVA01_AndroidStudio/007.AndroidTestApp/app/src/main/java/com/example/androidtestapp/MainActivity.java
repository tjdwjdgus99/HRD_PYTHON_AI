package com.example.androidtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private Button btnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  안드로이드 App 생성 초기화
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //  안드로이드 위젯(컨트롤)
        initWidget();
        //  이벤트 등록
        setButtonListner();

    }
    //  모듈화
    private void initWidget(){
        btnButton = findViewById(R.id.btnButton);
    }

    private void setButtonListner(){
        btnButton.setOnClickListener(view -> {
            Toast.makeText(this, "버튼이 눌러졌습니다.", Toast.LENGTH_SHORT).show();
        });
    }
}