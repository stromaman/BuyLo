package com.ecom.buylo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ecom.buylo.Activity.CatalogActivity;
import com.ecom.buylo.Activity.ReviewActivity;
import com.google.android.material.button.MaterialButton;

public class CatlogBasicActivity extends AppCompatActivity {
    MaterialButton basicNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlog_basic);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        basicNext=findViewById(R.id.basicNext);



        basicNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CatlogBasicActivity.this, CatlogVarrientActivity.class);
                startActivity(intent);
            }
        });
    }
}