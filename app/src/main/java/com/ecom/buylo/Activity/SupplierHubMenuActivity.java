package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ecom.buylo.R;

public class SupplierHubMenuActivity extends AppCompatActivity {

    CardView planCard;
    ImageView right,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_hub_menu);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        planCard=findViewById(R.id.planCard);
        right=findViewById(R.id.right);
        down=findViewById(R.id.down);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (planCard.getVisibility() == View.VISIBLE) {
                    planCard.setVisibility(View.GONE);
                    right.setImageResource(R.drawable.next);

                } else {
                    planCard.setVisibility(View.VISIBLE);
                    right.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }
            }
        });

    }
}