package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public class Classifier {
    Context context;
    private static final String MODEL_NAME = "cnnKnife.tflite";
    Interpreter interpreter = null;

    int modelInputWidth = 0;
    int modelInputHeight = 0;
    int modelInputChannel = 0;

    public Classifier(Context context) {
        this.context = context;
    }

    // tflite 파일 로드 함수
    private ByteBuffer loadModelFile(String modelName) throws IOException {
        AssetManager am = context.getAssets();
        AssetFileDescriptor afd = am.openFd(modelName);
        FileInputStream fis = new FileInputStream(afd.getFileDescriptor());
        FileChannel fc = fis.getChannel();
        long startOffset = afd.getStartOffset();
        long declaredLength = afd.getDeclaredLength();

        return fc.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public void init() throws IOException {
        ByteBuffer model = loadModelFile(MODEL_NAME);
        model.order(ByteOrder.nativeOrder()); // 바이트 순서를 따름
        Interpreter.Options tfliteOptions = new Interpreter.Options(); // Options 추가
        interpreter = new Interpreter(model, tfliteOptions); // Options 사용하여 Interpreter 초기화

        initModelShape();
    }

    // 모델 크기 계산 함수 정의
    private void initModelShape() {
        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] inputShape = inputTensor.shape();
        modelInputChannel = inputShape[3]; // 수정: RGB 채널 수로 설정
        modelInputWidth = inputShape[1]; // 수정: 이미지 너비
        modelInputHeight = inputShape[2]; // 수정: 이미지 높이
    }

    // 입력 이미지 크기 변환
    private Bitmap resizeBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, modelInputWidth, modelInputHeight, false);
    }

    // ARGB를 Gray로 변환하면서 비트맵을 바이트버퍼 포맷으로 변환
    private ByteBuffer convertBitmapToGrayByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(modelInputWidth * modelInputHeight * modelInputChannel * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[modelInputWidth * modelInputHeight];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int pixel : pixels) {
            int r = (pixel >> 16) & 0xFF;
            int g = (pixel >> 8) & 0xFF;
            int b = pixel & 0xFF;
            float avgPixelValue = (r + g + b) / 3.0f;
            float normalizedPixelValue = avgPixelValue / 255.0f;
            byteBuffer.putFloat(normalizedPixelValue);
        }
        return byteBuffer;
    }

    public float classify(Bitmap image) {
        ByteBuffer buffer = convertBitmapToGrayByteBuffer(resizeBitmap(image));
        float[][] result = new float[1][1]; // 모델의 출력 형태를 [1, 1]로 수정

        interpreter.run(buffer, result);

        // 모델의 출력 값 그대로 반환
        return result[0][0];
    }



    public void finish() {
        if (interpreter != null)
            interpreter.close();
    }
}
