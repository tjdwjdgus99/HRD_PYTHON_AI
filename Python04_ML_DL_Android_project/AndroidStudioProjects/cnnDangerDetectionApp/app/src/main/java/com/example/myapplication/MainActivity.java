package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    Classifier cls;

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ImageView imageView = (ImageView) view;
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            // 여기서 텐서플로라이트 분류
            float probability = cls.classify(bitmap);

            if (probability > 0.5) {
                textView.setText("Knife (Probability: " + probability + ")");
            } else {
                textView.setText("Gun (Probability: " + (1 - probability) + ")");
            }

            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView1.setOnTouchListener(listener);
        imageView2.setOnTouchListener(listener);

        cls = new Classifier(this); // 객체 생성
        try {
            cls.init(); // 초기화 메서드 호출
        } catch (IOException ioe) {
            Log.d("DigitClassifier", "Failed to init Classifier", ioe);
        }
    }

    @Override
    protected void onDestroy() {
        cls.finish();
        super.onDestroy();
    }
}
