
package app.ij.mlwithtensorflowlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import app.ij.mlwithtensorflowlite.ml.Model;

public class MainActivity extends AppCompatActivity {

    TextView result, confidence;
    ImageView imageView;
    Button picture;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);
        Button galleryButton = findViewById(R.id.button2);

        //  과일 데이터 베이스로 이동
        Button btndatabase = (Button) findViewById(R.id.btndatabase);
        btndatabase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), fruitselection.class);
                intent.putExtra("TEXT", "" + result.getText().toString());
                startActivity(intent);
            }
        });

        //  캘린더로 이동
        Button btncalendar = (Button) findViewById(R.id.btncalendar);
        btncalendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), calendarview.class);
                intent.putExtra("TEXT", "" + result.getText().toString());
                startActivity(intent);
            }
        });

        //  갤러리 사진 가져오기
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 갤러리에서 사진을 선택하기 위한 인텐트 생성
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2); // 2는 requestCode로 사용할 값입니다.
            }
        });


        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
    }




    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // 이미지를 원하는 크기로 조정
            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, imageSize, imageSize, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of pixels in the image
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, imageSize, 0, 0, imageSize, imageSize);

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            for (int pixelValue : intValues) {
                byteBuffer.putFloat(((pixelValue >> 16) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat(((pixelValue >> 8) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat((pixelValue & 0xFF) * (1.f / 255.f));
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for(int i = 0; i < confidences.length; i++){
                if(confidences[i] > maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Apple", "Banana", "Cherry", "Kiwi","Lemon","Orange","Tomato","Strawberry"};
            result.setText(classes[maxPos]);

            String s = "";
            for(int i = 0; i < classes.length; i++){
                s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100);
            }
            confidence.setText(s);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // 사진 찍기 결과
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);
        } else  if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            // 갤러리에서 사진을 선택한 경우

            // 선택한 이미지의 URI를 가져옵니다.
            Uri selectedImageUri = data.getData();

            try {
                // URI에서 Bitmap 이미지를 가져옵니다.
                Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                int dimension = Math.min(selectedImage.getWidth(), selectedImage.getHeight());
                selectedImage = ThumbnailUtils.extractThumbnail(selectedImage, dimension, dimension);

                // ImageView에 이미지 설정
                imageView.setImageBitmap(selectedImage);

                // 이미지를 모델에 전달하여 분류를 수행하는 함수 호출
                classifyImage(selectedImage);
            } catch (IOException e) {
                // 예외 처리
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}