package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ecom.buylo.Adapter.VendorProductAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.AddProduct;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.InternetValidation;
import com.ecom.buylo.Utils.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyShopActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    LinearLayout viewCartLayout;
    ProgressBar dashProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        categoryRecycler=findViewById(R.id.categoryRecycler);
        viewCartLayout=findViewById(R.id.viewCartLayout);
        dashProgress = findViewById(R.id.dashProgress);

        dashProgress.setVisibility(View.GONE);
        viewCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyShopActivity.this, SupplierHubMenuActivity.class);
                startActivity(intent);
            }
        });
        MyPreferences preferences = MyPreferences.getInstance(getApplicationContext());
        getBestSellingProduct(preferences.getUserId());

//        ArrayList<CategoryModel> categories= new ArrayList<>();
//        categories.add(new CategoryModel(R.drawable.menu,"All Categories"));
//        categories.add(new CategoryModel(R.drawable.snack,"Snack"));
//        categories.add(new CategoryModel(R.drawable.fulltshirt,"T-Shirt"));
//        categoryRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));
//        categoryRecycler.setHasFixedSize(true);
//        CategoryListAdapter adapter =new CategoryListAdapter(getApplicationContext(), null,categories);
//        categoryRecycler.setAdapter(adapter);


    }



    public void getBestSellingProduct(int userId) {
        dashProgress.setVisibility(View.VISIBLE);
       // categoryRecycler.setLayoutManager(new LinearLayoutManager(MyShopActivity.this, GridLayoutManager.HORIZONTAL, false));
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<AddProduct> call = api.getProductList(String.valueOf(userId));
        call.enqueue(new Callback<AddProduct>() {
            @Override
            public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {
                dashProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        categoryRecycler.setVisibility(View.VISIBLE);
                        VendorProductAdapter bestSellingProductAdapter = new VendorProductAdapter(getApplicationContext()
                                , MyShopActivity.this
                                , response.body().getData());
                        categoryRecycler.setAdapter(bestSellingProductAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
                if (InternetValidation.validation(MyShopActivity.this)) {

                } else {
                    InternetValidation.getNoConnectionDialog(MyShopActivity.this);
                }
            }

            @Override
            public void onFailure(Call<AddProduct> call, Throwable t) {
                if (InternetValidation.validation(MyShopActivity.this)) {

                } else {
                    InternetValidation.getNoConnectionDialog(MyShopActivity.this);
                }
                dashProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }
}