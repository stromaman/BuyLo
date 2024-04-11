package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ecom.buylo.R;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    MaterialButton Seller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        Seller=findViewById(R.id.Seller);

        Seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, SellerHubActivity.class);
                startActivity(intent);
            }
        });
    }
}