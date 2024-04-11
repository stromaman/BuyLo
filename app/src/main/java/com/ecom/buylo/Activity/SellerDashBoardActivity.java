package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecom.buylo.Fragment.HomeFragment;
import com.ecom.buylo.Fragment.InventoryFragment;
import com.ecom.buylo.Fragment.MenuFragment;
import com.ecom.buylo.R;
import com.google.android.material.button.MaterialButton;
import com.skydoves.elasticviews.ElasticCardView;

public class SellerDashBoardActivity extends AppCompatActivity {

    MaterialButton uploadCatalog;
    ElasticCardView backView;
    CardView shopCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dash_board);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        uploadCatalog=findViewById(R.id.uploadCatalog);
        backView=findViewById(R.id.backView);
        shopCard=findViewById(R.id.shopCard);

        shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerDashBoardActivity.this, MyShopActivity.class);
                startActivity(intent);
            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerDashBoardActivity.this, DashBoardMainActivity.class);
                startActivity(intent);
            }
        });

        uploadCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerDashBoardActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });
    }
}