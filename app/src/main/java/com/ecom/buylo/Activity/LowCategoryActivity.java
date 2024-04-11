package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecom.buylo.Adapter.LowCategoryAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.LowCategoryRent;
import com.ecom.buylo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LowCategoryActivity extends AppCompatActivity {
    RecyclerView areaRecycler;
    String category_id;
    TextView txtSelect;
    LinearLayout cardBack;
    public String stateId, districtId,CategoryName,SubCategoryName,productId,districId, payment_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_category);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        areaRecycler=findViewById(R.id.areaRecycler);
        txtSelect=findViewById(R.id.txtSelect);
        Intent intent = getIntent();
        if (intent != null) {
            int categoryId = intent.getIntExtra("category_id", -1); // -1 is the default value if "category_id" is not found
            Log.e("SubCategoryId", "Received category_id: " + categoryId);
            String categoryName = intent.getStringExtra("category_name");
            // Now you can use the categoryId as needed in your activity
            txtSelect.setText(categoryName);
            getDistrictList(categoryId);
        } else {
            Log.e("SubCategoryId", "Intent is null");
        }

        cardBack = findViewById(R.id.cardBack);
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LowCategoryActivity.super.onBackPressed();
            }
        });
    }

    private void getDistrictList(int categoryId) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<LowCategoryRent> call = api.getLowcategory(String.valueOf(categoryId));
        call.enqueue(new Callback<LowCategoryRent>() {
            @Override
            public void onResponse(Call<LowCategoryRent> call, Response<LowCategoryRent> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        LowCategoryAdapter districListAdapter = new LowCategoryAdapter(getApplicationContext()
                                , null
                                , LowCategoryActivity.this
                                , response.body().getData());
                        areaRecycler.setAdapter(districListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<LowCategoryRent> call, Throwable t) {
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }
}