package com.example.basicoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiple;
    private Button btnDivide;
    private EditText etV1;
    private EditText etV2;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  1.위젯 변수와 R.id 연결
        InitializedViews();

        //  2.이벤트 등록 및 처리
        btnPlus.setOnClickListener(view -> {
            clickPlus();
        });
        btnMinus.setOnClickListener(view -> {
            clickMinus();
        });
        btnMultiple.setOnClickListener(view -> {
            clickMultiple();
        });
        btnDivide.setOnClickListener(view -> {
            clickDivide();
        });
    }
    private void InitializedViews(){
        etV1 = findViewById(R.id.etV1);
        etV2 = findViewById(R.id.etV2);
        txtResult = findViewById(R.id.txtResult);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);
    }

    private void clickPlus(){
        //  문자열에서 숫자를 읽어옴 Integer.parseInt(etV1.getText().toString()) 문자열을 가져와서 인티저로 형변환
        int v1 = Integer.parseInt(etV1.getText().toString());
        int v2 = Integer.parseInt(etV2.getText().toString());
        int result = v1 + v2;
        txtResult.setText("계산결과 : " + Integer.toString(result));
    }
    private void clickMinus(){
        //  문자열에서 숫자를 읽어옴 Integer.parseInt(etV1.getText().toString()) 문자열을 가져와서 인티저로 형변환
        int v1 = Integer.parseInt(etV1.getText().toString());
        int v2 = Integer.parseInt(etV2.getText().toString());
        int result = v1 - v2;
        txtResult.setText("계산결과 : " + Integer.toString(result));
    }
    private void clickMultiple(){
        //  문자열에서 숫자를 읽어옴 Integer.parseInt(etV1.getText().toString()) 문자열을 가져와서 인티저로 형변환
        int v1 = Integer.parseInt(etV1.getText().toString());
        int v2 = Integer.parseInt(etV2.getText().toString());
        int result = v1 * v2;
        txtResult.setText("계산결과 : " + Integer.toString(result));
    }
    private void clickDivide(){
        //  문자열에서 숫자를 읽어옴 Integer.parseInt(etV1.getText().toString()) 문자열을 가져와서 더블로 형변환
        double v1 = Double.parseDouble(etV1.getText().toString());
        double v2 = Double.parseDouble(etV2.getText().toString());
        double result = v1 / v2;
        txtResult.setText("계산결과 : " + Double.toString(result));
    }


}