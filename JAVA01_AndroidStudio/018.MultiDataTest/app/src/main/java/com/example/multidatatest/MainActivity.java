package com.example.multidatatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnOpen;

    private EditText etV1,etV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etV1 = findViewById(R.id.etV1);
        etV2 = findViewById(R.id.etV2);
        btnOpen = findViewById(R.id.btnOpen);

        //  데이터 첨부
        btnOpen.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
            //  intent에 데이터 첨부
            intent.putExtra("V1", Integer.parseInt(etV1.getText().toString()));
            intent.putExtra("V2", Integer.parseInt(etV2.getText().toString()));
            startActivityForResult(intent,0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            int plus = data.getIntExtra("result",0);
            Toast.makeText(this, "합계 : " + plus, Toast.LENGTH_SHORT).show();
        }
    }
}