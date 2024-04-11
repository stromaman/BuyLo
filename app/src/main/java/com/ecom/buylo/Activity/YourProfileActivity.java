package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ecom.buylo.R;

public class YourProfileActivity extends AppCompatActivity {

    LinearLayout nextGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        nextGo=findViewById(R.id.nextGo);

        nextGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YourProfileActivity.this, SellerHubActivity.class);
                startActivity(intent);
            }
        });
    }
}