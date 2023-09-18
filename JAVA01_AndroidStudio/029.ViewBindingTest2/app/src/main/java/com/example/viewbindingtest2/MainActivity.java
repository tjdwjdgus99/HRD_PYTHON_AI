package com.example.viewbindingtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewbindingtest2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //  ViewBinding
    //  1.바인딩을 위한 병수 선언
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  2.ViewBinding 객체 만들기
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //  3.ViewBinding객체로 화면 지정
        setContentView(binding.getRoot());

        binding.edtName.setText("홍길동");
        binding.txtHello.setText("안녕하세요");
        binding.ibFace.setImageResource(R.drawable.snowman);

        binding.ibFace.setOnClickListener(view1 -> {
            Toast.makeText(this, "이미지 버튼이 눌림", Toast.LENGTH_SHORT).show();
        });

        binding.btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
            }
        });

    }
}