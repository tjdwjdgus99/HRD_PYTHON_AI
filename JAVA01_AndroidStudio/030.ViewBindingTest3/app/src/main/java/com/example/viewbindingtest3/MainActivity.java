package com.example.viewbindingtest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.viewbindingtest3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //1. 바인딩을 위한 변수
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2. 뷰 바인딩을 사용하면 레이아웃 파일을 객체화 할 수 있다.
        //setContentView(R.layout.activity_main);

        //3. 뷰 바인딩으로 화면 객체를 만들자.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //4. view객체로 다시 받아 보았다.
        View view = binding.getRoot();
        //5. 만든 뷰 객체를 화면에 뿌려본다.
        setContentView(view);

        TextView txtGreet = binding.txtGreet;
        txtGreet.setText("안녕하세요. 뷰바인딩!!");

        //프레그먼트 교체
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainfragment, new FragmentSample());
        ft.commit();
    }
}