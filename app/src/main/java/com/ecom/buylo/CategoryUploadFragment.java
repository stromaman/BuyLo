package com.ecom.buylo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecom.buylo.Activity.CatalogActivity;
import com.ecom.buylo.Adapter.RentCategoryAdapter;
import com.ecom.buylo.Adapter.SubCategoryAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Model.request.SubcategoryRent;
import com.ecom.buylo.Model.request.categoryRent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryUploadFragment extends Fragment {
    RecyclerView areaRecycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category_upload, container, false);
        areaRecycler=view.findViewById(R.id.areaRecycler);

        getStateList();

        return view;
    }
    public void getStateList() {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<categoryRent> call = api.getRentCategory();
        call.enqueue(new Callback<categoryRent>() {
            @Override
            public void onResponse(Call<categoryRent> call, Response<categoryRent> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        RentCategoryAdapter stateListAdapter = new RentCategoryAdapter(getContext()
                                , null
                                ,null
                                ,null
                                , response.body().getData(),
                                null);
                        areaRecycler.setAdapter(stateListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<categoryRent> call, Throwable t) {

                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }
}