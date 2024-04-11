package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.ecom.buylo.R;

public class WishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
    }
}