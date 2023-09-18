package com.example.androidapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtHello : TextView = findViewById(R.id.txtHello)
        var btnButton : Button = findViewById(R.id.btnOk)
        btnButton.setOnClickListener(){
            txtHello.setText("Hello Android App!!")
        }
    }
}
