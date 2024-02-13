package com.example.faty.view.home;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.faty.R;


public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageViewGif = findViewById(R.id.imageViewGif);
        ImageView img = findViewById(R.id.img);
        Glide.with(this).asGif().load(R.drawable.live_cooking_lab).into(img);
        Glide.with(this).asGif().load(R.drawable.fattyfoods).into(imageViewGif);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}