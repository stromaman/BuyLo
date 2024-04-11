package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecom.buylo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductShowActivity extends AppCompatActivity {
    TextView sort;
    LinearLayout product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        product=findViewById(R.id.product);
        sort=findViewById(R.id.sort);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProductShowActivity.this, ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDrawerDialog();
            }
        });
    }

    Dialog drawerDialog;
    LinearLayout transLayer, drawerProfileLayout;
    CircleImageView userImage;

    public void getDrawerDialog() {
        drawerDialog = new Dialog(ProductShowActivity.this);
        drawerDialog.setContentView(R.layout.sortby_layout);
        drawerDialog.setCancelable(true);
        transLayer = drawerDialog.findViewById(R.id.transLayer);
        drawerProfileLayout = drawerDialog.findViewById(R.id.drawerProfileLayout);
        transLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerDialog.dismiss();
            }
        });



        drawerDialog.show();
        drawerDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        drawerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        drawerDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationReport;
        drawerDialog.getWindow().setGravity(Gravity.TOP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawerDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            drawerDialog.getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.white));
        }
    }
}