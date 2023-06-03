package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splahscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splahscreen);
        getSupportActionBar().hide();
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    startActivity(new Intent(Splahscreen.this,MainActivity.class));
                    finish();
                }
            }
        };thread.start();
    }
}