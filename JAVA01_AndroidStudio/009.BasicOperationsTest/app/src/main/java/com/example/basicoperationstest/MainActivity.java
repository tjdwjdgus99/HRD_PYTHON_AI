package com.example.basicoperationstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etWidth;
    private EditText etHeight;
    private Button btnCalculate;
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializedViews();

        clickMultiple();

    }
    private void InitializedViews(){
        etWidth = findViewById(R.id.etWidth);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
    }

    private void clickMultiple(){
        btnCalculate.setOnClickListener(view -> {
            txtResult.setText("결과 : " + Integer.parseInt(etWidth.getText().toString()) * Integer.parseInt(etHeight.getText().toString()));
        });
    }
}