package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView Text1,Text2;
    private CheckBox ChkAgree;
    private RadioGroup Rgroup1;
    private RadioButton RdoDog,RdoCat,RdoRabbit;
    private Button BtnOk;
    private ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("애완동물 사진 보기");

        //  리소스 초기화 //
        Text1 = findViewById(R.id.Text1);
        Text2 = findViewById(R.id.Text2);
        ChkAgree = findViewById(R.id.ChkAgree);
        Rgroup1 = findViewById(R.id.Rgroup1);
        RdoDog = findViewById(R.id.RdoDog);
        RdoCat = findViewById(R.id.RdoCat);
        RdoRabbit = findViewById(R.id.RdoRabbit);
        BtnOk = findViewById(R.id.BtnOk);
        imgPet = findViewById(R.id.imgPet);

        //  체크박스 이벤트 처리 //
        ChkAgree.setOnCheckedChangeListener((compoundButton, b) -> {
           if (ChkAgree.isChecked() == true){
               Text2.setVisibility(View.VISIBLE);
               Rgroup1.setVisibility(View.VISIBLE);
               imgPet.setVisibility(View.VISIBLE);
           }else{
               Text2.setVisibility(View.INVISIBLE);
               Rgroup1.setVisibility(View.INVISIBLE);
               imgPet.setVisibility(View.INVISIBLE);
           }
        });
        //  버튼 이벤트 처리   //
        BtnOk.setOnClickListener(view -> {
            if(Rgroup1.getCheckedRadioButtonId() == R.id.RdoDog){
                imgPet.setImageResource(R.drawable.dog);
            } else if (Rgroup1.getCheckedRadioButtonId() == R.id.RdoCat) {
                imgPet.setImageResource(R.drawable.cat);
            } else if (Rgroup1.getCheckedRadioButtonId() == R.id.RdoRabbit) {
                imgPet.setImageResource(R.drawable.rabbit);
            }else{
                Toast.makeText(this, "원하시는 그림이 없습니다.", Toast.LENGTH_SHORT).show();
            }

        });

    }
}