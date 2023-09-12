package com.example.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class RobotView extends View implements Runnable{

    private Drawable image;
    private int viewWidth, viewHeight;
    private int imageWidth,imageHeight;
    private  int x,y;

    public RobotView(Context context, @Nullable AttributeSet attrs) {   //  Nullable null값들어 올수 있도록
        super(context, attrs);
        image = ResourcesCompat.getDrawable(getResources(),R.drawable.androidbot,null);

        Thread thread = new Thread((Runnable) this);
        thread.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {  //  이미지를 그려라
        super.onDraw(canvas);

        image.setBounds(x,y,x+imageWidth,y+imageHeight);
        image.draw(canvas);

    }// end of onDraw

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //  그림을 가운데 보내는 코드
        viewWidth = this.getWidth();    //  휴대폰 화면 가로 크기
        viewHeight = this.getHeight();  //  세로

        imageWidth = image.getIntrinsicWidth(); //  이미지의 가로 크기
        imageHeight = image.getIntrinsicHeight();    //  세로

        //  화면의 가운데로 오기위한 x,y좌표 구하기
        x = viewWidth/2 - imageWidth/2;
        y = 0;

    }// end of onSizeChanged

    int count = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if(count == 1){
                    count = 0;
                }
                if(count == 0){
                    count = 1;
                }
                this.invalidate();  // Refresh the view
                break;
        }
        return true;
    }

    @Override
    public void run(){
        for(;;){
            try {
                Thread.sleep(50);
                if (count ==0){
                    goright();
                    godown();
                    goleft();
                    goup();
                }else{
                    goleft();
                    godown();
                    goright();
                    goup();
                }


                this.postInvalidate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void goup(){
        for(;;){
            try {
                Thread.sleep(500);
                y = Math.max(0, y - 100); //위로 가는 코드 천장에 닿으면 멈춥니다.
                if(y == 0){
                    return;
                }
                this.postInvalidate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void godown(){
        for(;;){
            try {
                Thread.sleep(500);
                y = Math.min(viewHeight - imageHeight, y + 100);//밑으로 가는 코드 바닥에 닿으면 멈춥니다.
                if(y == viewHeight - imageHeight){
                    return;
                }
                this.postInvalidate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void goleft(){
        for(;;){
            try {
                Thread.sleep(500);
                x = Math.max(0, x - 100);
                if(x == 0){
                    return;
                }
                this.postInvalidate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void goright(){
        for(;;){
            try {
                Thread.sleep(500);
                x = Math.min(viewWidth - imageWidth, x + 100);
                if(x == viewWidth - imageWidth){
                    return;
                }
                this.postInvalidate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}// end of RobotView
