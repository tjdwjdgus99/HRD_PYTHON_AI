package com.example.multidatatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private Button btnReturn;
    private TextView txtV1, txtV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second 액티비티");

        // 보낸 데이터 받기
        Intent intent = getIntent();
        // 더하기
        int resultPlus = (intent.getIntExtra("V1", 0) + intent.getIntExtra("V2", 0));

        txtV1 = findViewById(R.id.txtV1);
        txtV2 = findViewById(R.id.txtV2);
        btnReturn = findViewById(R.id.btnReturn);

        txtV1.setText(intent.getIntExtra("V1", 0) + "");
        txtV2.setText(intent.getIntExtra("V2", 0) + "");

        // 계산된 결과를 되돌려 보내기
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retintent = new Intent();
                retintent.putExtra("result", resultPlus);
                // intent를 되돌려주는 함수
                setResult(RESULT_OK, retintent);
                finish(); // 현재 액티비티를 종료하고 이전 액티비티로 돌아갑니다.
            }
        });
    }
}
