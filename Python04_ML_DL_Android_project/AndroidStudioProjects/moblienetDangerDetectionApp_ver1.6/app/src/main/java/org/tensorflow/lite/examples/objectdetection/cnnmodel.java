package org.tensorflow.lite.examples.objectdetection;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class cnnmodel extends AppCompatActivity {
    private TextView textView;
    private Classifier classifier;
    private static final int PICK_IMAGE = 100;
    private ImageView imageView;

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ImageView imageView = (ImageView) view;
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            // 이미지 분류 메서드 호출
            String result = classifier.classifyImage(bitmap);

            // 결과를 TextView에 표시
            textView.setText(result);

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cnnmodel);

        textView = findViewById(R.id.cnntextView);
        imageView = findViewById(R.id.cnnimageView);
        imageView.setOnTouchListener(listener);

        try {
            // TensorFlow Lite 모델 파일 로드
            Interpreter interpreter = new Interpreter(loadModelFile("cnnKnife.tflite"));
            classifier = new Classifier(interpreter);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (classifier != null && classifier.getInterpreter() != null) {
            classifier.getInterpreter().close();
        }
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
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 모델 파일 로드
    private MappedByteBuffer loadModelFile(String modelFileName) throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd(modelFileName);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

}
