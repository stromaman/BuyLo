package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ecom.buylo.R;

public class SaveLocationActivity extends AppCompatActivity {

    LinearLayout locationCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        locationCat=findViewById(R.id.locationCat);

        locationCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SaveLocationActivity.this, DashBoardMainActivity.class);
                startActivity(intent);
            }
        });
    }
}