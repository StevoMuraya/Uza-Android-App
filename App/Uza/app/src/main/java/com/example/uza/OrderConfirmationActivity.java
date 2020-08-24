package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class OrderConfirmationActivity extends AppCompatActivity {
    private Button order_confirm_great;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        try {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        order_confirm_great = findViewById(R.id.order_confirm_great);

        order_confirm_great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderConfirmationActivity.this,ActivityUzaHomePage.class));
        finish();
    }
}
