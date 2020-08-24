package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logo = findViewById(R.id.splash_logo);

        Animation push_down_in  = AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_down_in);
        logo.setVisibility(View.VISIBLE);
        logo.setAnimation(push_down_in);

        new CountDownTimer(4000, 4000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {

                logo.animate().alpha(0f).setDuration(1000);
                new CountDownTimer(1500, 4000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this,ActivityUzaHomePage.class));
                        finish();
                    }

                }.start();
            }

        }.start();
    }
}
