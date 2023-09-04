package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    Classifier cls;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    ImageView imageView;

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
                textView.setText("no Knife (Probability: " + (1 - probability) + ")");
            }

            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView1);
        imageView.setOnTouchListener(listener);

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

    public void onSelectImageClick(View view) {
        // 갤러리에서 이미지를 선택하는 인텐트를 생성
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
