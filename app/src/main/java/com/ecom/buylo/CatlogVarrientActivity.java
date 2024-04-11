package com.ecom.buylo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ecom.buylo.Activity.CatalogActivity;
import com.ecom.buylo.Activity.ReviewActivity;
import com.google.android.material.button.MaterialButton;

public class CatlogVarrientActivity extends AppCompatActivity {
    MaterialButton goShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlog_varrient);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        goShop=findViewById(R.id.goShop);



        goShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CatlogVarrientActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
    }
}