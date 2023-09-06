package org.tensorflow.lite.examples.objectdetection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class emergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        Button btn1 = (Button)findViewById(R.id.emergencybutton1);
        Button btn2 = (Button)findViewById(R.id.emergencybutton2);
        Button btn3 = (Button)findViewById(R.id.emergencybutton3);
        
        //  112 전화
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 112 번호로 전화걸기
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:000"));
                startActivity(intent);
            }
        });
        
        //  119 전화
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 119 번호로 전화걸기
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:000"));
                startActivity(intent);
            }
        });



        //  전화 앱 열기
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"));
                //Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:12345"));
                startActivity(intent);
            }
        });
    }
}