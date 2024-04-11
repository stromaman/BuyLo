package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ecom.buylo.Adapter.CategoryRentAdapter;
import com.ecom.buylo.Model.RentCategory;
import com.ecom.buylo.R;

import java.util.ArrayList;

public class CategoryRentActivity extends AppCompatActivity {
    LinearLayout homelayout,homeshowlayout,cateLayout,cateShowLayout;
    RecyclerView categoryRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_rent);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#d1ebed"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        homelayout=findViewById(R.id.homelayout);
        homeshowlayout=findViewById(R.id.homeshowlayout);
        cateLayout=findViewById(R.id.cateLayout);
        cateShowLayout=findViewById(R.id.cateShowLayout);
        categoryRecycler=findViewById(R.id.categoryRecycler);

        ArrayList<RentCategory> categoriess= new ArrayList<>();
        categoriess.add(new RentCategory(R.drawable.presser,"Cloth","200/Day"));
        categoriess.add(new RentCategory(R.drawable.juicer,"Juicer","100/Day"));
        categoriess.add(new RentCategory(R.drawable.laptop,"Laptop","120/hour"));
        categoriess.add(new RentCategory(R.drawable.sound,"Sound Box","1120/hour"));
        categoriess.add(new RentCategory(R.drawable.car,"Car","120/km"));
        categoriess.add(new RentCategory(R.drawable.guitar,"Guitar","900/Day"));
        categoryRecycler.setHasFixedSize(true);
        CategoryRentAdapter adapter1 =new CategoryRentAdapter(getApplicationContext(), null,categoriess);
        categoryRecycler.setAdapter(adapter1);


        homeshowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryRentActivity.this, RentActivity.class);
                if (cateShowLayout.getVisibility() == View.VISIBLE){
                    cateShowLayout.setVisibility(View.VISIBLE);
                    cateLayout.setVisibility(View.GONE);
                    homelayout.setVisibility(View.VISIBLE);
                    homeshowlayout.setVisibility(View.GONE);
                }
                else {

                }
                startActivity(intent);
            }
        });

    }
}