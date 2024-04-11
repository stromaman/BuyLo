package com.ecom.buylo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecom.buylo.Adapter.SubCategoryAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.SubcategoryRent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubCategoryUploadFragment extends Fragment {
    RecyclerView areaRecycler;
    int productId;
    public String stateId, districtId,CategoryName,SubCategoryName,districId, payment_code;
    public TextView txtState, txtDistrict,txtDistric,txtDistri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sub_category_upload, container, false);

        areaRecycler=view.findViewById(R.id.areaRecycler);
        Bundle bundle = getArguments();
        if (bundle != null) {
            productId = bundle.getInt("STATE ID", -1); // -1 is the default value if key is not found
            // Use the productId as needed
        }
        getDistrictList(String.valueOf(stateId));
        return  view;
    }

    public void getDistrictList(String stateId) {

        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<SubcategoryRent> call = api.getSubcategory(stateId);
        call.enqueue(new Callback<SubcategoryRent>() {
            @Override
            public void onResponse(Call<SubcategoryRent> call, Response<SubcategoryRent> response) {

                if (response.body() != null) {
                    if (response.isSuccessful()) {

                        SubCategoryAdapter districtListAdapter = new SubCategoryAdapter(getContext()
                                , null
                                , null
                                ,SubCategoryUploadFragment.this
                                , response.body().getData());
                        areaRecycler.setAdapter(districtListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<SubcategoryRent> call, Throwable t) {

                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }



}