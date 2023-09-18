package com.example.intentapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnResult);
        //  그림의 최대 갯수
        final int voteCount[] = new int[9];
        // 초기화
        for (int i = 0; i < 9; i++){
            voteCount[i] = 0;
        }
        //  이미지 객체 배열
        ImageView image[] = new ImageView[9];
        //  이미지 ID 추가 배열
        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
        final String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for (int i = 0; i < imageId.length; i++){
            final int index;    //  외부에서 지역변수를 건들지 못하도록 함
            index = i;
            image[index]= findViewById(imageId[index]);
            image[index].setOnClickListener(view -> {   //  이미지뷰를 버튼처럼
                //  투표수 증가
                voteCount[index]++;
                Toast.makeText(this, imgName[index] + ": 총 " + voteCount[index] + " 표", Toast.LENGTH_SHORT).show();
            });
        }
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                //  데이터 라벨 정해주고 데이터 담기
                intent.putExtra("Votecount" , voteCount);
                intent.putExtra("imgName",imgName);
                //  intent에 데이터 첨부
                startActivity(intent);
            }
        });

    }
}
