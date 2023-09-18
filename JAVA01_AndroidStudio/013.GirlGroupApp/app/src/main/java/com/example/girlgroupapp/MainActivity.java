package com.example.girlgroupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView Text1,Text2;
    private CheckBox ChkAgree;
    private RadioGroup Rgroup1;
    private RadioButton Rdo1,Rdo2,Rdo3;
    private Button BtnOk;
    private ImageView imggirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("걸그룹 사진 보기");

        //  리소스 초기화 //
        resourceinitialization();

        //  체크박스 이벤트 처리 //
        checkboxevent();

        //  버튼 이벤트 처리   //
        btnevent();

    }
    private void resourceinitialization(){
        Text1 = findViewById(R.id.Text1);
        Text2 = findViewById(R.id.Text2);
        ChkAgree = findViewById(R.id.ChkAgree);
        Rgroup1 = findViewById(R.id.Rgroup1);
        Rdo1 = findViewById(R.id.Rdo1);
        Rdo2 = findViewById(R.id.Rdo2);
        Rdo3 = findViewById(R.id.Rdo3);
        BtnOk = findViewById(R.id.BtnOk);
        imggirl = findViewById(R.id.imggirl);
    }
    private void checkboxevent(){
        ChkAgree.setOnCheckedChangeListener((compoundButton, b) -> {
            if (ChkAgree.isChecked() == true){
                Text2.setVisibility(View.VISIBLE);
                Rgroup1.setVisibility(View.VISIBLE);
                imggirl.setVisibility(View.VISIBLE);
            }else {
                Text2.setVisibility(View.INVISIBLE);
                Rgroup1.setVisibility(View.INVISIBLE);
                imggirl.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void btnevent(){
        BtnOk.setOnClickListener(view -> {
            if(Rgroup1.getCheckedRadioButtonId() == R.id.Rdo1){
                imggirl.setImageResource(R.drawable.newjin);
            } else if (Rgroup1.getCheckedRadioButtonId() == R.id.Rdo2) {
                imggirl.setImageResource(R.drawable.ruseira);
            } else if (Rgroup1.getCheckedRadioButtonId() == R.id.Rdo3) {
                imggirl.setImageResource(R.drawable.blackpink);
            }else{
                Toast.makeText(this, "원하시는 그림이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}