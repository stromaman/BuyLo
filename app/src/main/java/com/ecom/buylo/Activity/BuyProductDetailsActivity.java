package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ecom.buylo.Adapter.ProductAdapter;
import com.ecom.buylo.Model.Product;
import com.ecom.buylo.R;

import java.util.ArrayList;

public class BuyProductDetailsActivity extends AppCompatActivity {
    RecyclerView relatedProductRecycler;
    LinearLayout viewShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product_details);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        viewShop=findViewById(R.id.viewShop);
        relatedProductRecycler=findViewById(R.id.relatedProductRecycler);

        viewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BuyProductDetailsActivity.this, ViewShopActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Product> seasons= new ArrayList<>();
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        relatedProductRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        relatedProductRecycler.setHasFixedSize(true);
        ProductAdapter adapter2 =new ProductAdapter(getApplicationContext(),seasons);
        relatedProductRecycler.setAdapter(adapter2);
    }
}