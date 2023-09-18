package com.example.viewbindingtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewbindingtest1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //  ViewBinding
    //  1.바인딩을 위한 병수 선언
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  2.setContentView는 이제 R.layout을 가르키지 않는다.
        //setContentView(R.layout.activity_main);

        //  3.ViewBinding 객체 만들기
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //  4.ViewBinding객체로 화면 지정
        setContentView(view);

        TextView txtGreet = binding.textView;
        binding.textView.setText("ㅎㅇㅎㅇ");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼을눌렀습니다", Toast.LENGTH_SHORT).show();
            }
        });


    }
}