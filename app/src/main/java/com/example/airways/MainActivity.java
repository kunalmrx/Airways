package com.example.airways;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2800;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       new Handler().postDelayed(new Runnable()
       {
           @Override

       public void run() {
        Intent homeintent=new Intent(MainActivity.this, StartActivity.class);
        startActivity(homeintent);
        finish();
        }
        },SPLASH_TIME_OUT);

                }
        }

