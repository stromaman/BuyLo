package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;

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

public class LocationActivity extends AppCompatActivity {

    LinearLayout locationShow,locationCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        locationShow=findViewById(R.id.locationShow);
        locationCat=findViewById(R.id.locationCat);

        locationShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LocationActivity.this, SaveLocationActivity.class);
                startActivity(intent);
            }
        });

//        locationShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getDrawerDialog();
//            }
//        });

    }

    Dialog drawerDialog;
    TextView company,individual;
    LinearLayout location;

    private void getDrawerDialog() {
        drawerDialog = new Dialog(LocationActivity.this);
        drawerDialog.setContentView(R.layout.locationpage);
        drawerDialog.setCancelable(true);
        location = drawerDialog.findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerDialog.dismiss();
                Intent intent = new Intent(LocationActivity.this, SaveLocationActivity.class);
                startActivity(intent);
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