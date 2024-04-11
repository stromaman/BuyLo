package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecom.buylo.Adapter.RentCategoryAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.categoryRent;
import com.ecom.buylo.Model.response.Rent;
import com.ecom.buylo.Model.response.Subcat;
import com.ecom.buylo.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    public TextView txtState, txtDistrict,txtDistric,txtDistri;
    public String stateId, districtId,CategoryName,SubCategoryName,productId,districId, payment_code;
    RelativeLayout parentLayout;
    TextView txtAreaHeading;
    RecyclerView areaRecycler;
    FrameLayout framelayout;
    LinearLayout nextGo;
    List<Rent> stateList; // Assuming this list is populated somewhere
    List<Subcat> data;
    LinearLayout cardBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
//        txtState=findViewById(R.id.productCategory);
//        txtDistrict=findViewById(R.id.productSubCategory);
//        txtDistric=findViewById(R.id.productParentCategory);
//        txtDistri=findViewById(R.id.productFinalCategory);
//        parentLayout=findViewById(R.id.parentLayout);
//        txtAreaHeading=findViewById(R.id.txtAreaHeading);
        framelayout = findViewById(R.id.framelayout);
        areaRecycler = findViewById(R.id.areaRecycler);

      //  CallFragment(new CategoryUploadFragment(),"-1");
        getStateList();
        stateList = new ArrayList<>();
        data = new ArrayList<>();
        //DashboardView();
//        txtAreaHeading.setText(txtState.getText());
//        txtState.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (InternetValidation.validation(getApplicationContext())) {
//                    selectStatePopup();
//                } else {
//                    InternetValidation.getNoConnectionDialog(getApplicationContext());
//                }
//
//            }
//        });
        cardBack = findViewById(R.id.cardBack);
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryActivity.super.onBackPressed();
            }
        });

//        txtDistrict.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (stateId != null) {
//
//                    if (InternetValidation.validation(CategoryActivity.this)) {
//                        selectDistrictPopup(stateId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CategoryActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
//
//        txtDistric.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (districtId != null) {
//
//                    if (InternetValidation.validation(CategoryActivity.this)) {
//                        selectProductPopup(districtId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CategoryActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
//
//        txtDistri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (productId != null) {
//
//                    if (InternetValidation.validation(CategoryActivity.this)) {
//                        selectFinalProductPopup(productId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CategoryActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
       // getStateList();



    }
    public void getStateList() {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<categoryRent> call = api.getRentCategory();
        call.enqueue(new Callback<categoryRent>() {
            @Override
            public void onResponse(Call<categoryRent> call, Response<categoryRent> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        RentCategoryAdapter stateListAdapter = new RentCategoryAdapter(getApplicationContext()
                                , null
                                ,CategoryActivity.this
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






//    public void getStateList() {
//        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
//        Call<categoryRent> call = api.getRentCategory();
//        call.enqueue(new Callback<categoryRent>() {
//            @Override
//            public void onResponse(Call<categoryRent> call, Response<categoryRent> response) {
//                if (response.body() != null) {
//                    if (response.isSuccessful()) {
//                        stateList=response.body().getData();
//                        RentCategoryAdapter stateListAdapter = new RentCategoryAdapter(CategoryActivity.this
//                                , null
//                                ,CategoryActivity.this
//                                , response.body().getData());
//                        areaRecycler.setAdapter(stateListAdapter);
//                    } else {
//
//                    }
//                } else {
//                    Log.e("BODY", "Body is null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<categoryRent> call, Throwable t) {
//
//                Log.e("EXCEPTION", t.getLocalizedMessage());
//            }
//        });
//    }

//    public void DashboardView()
//    {
//        CallFragment(new CategoryUploadFragment(),"-1");
//
//
//
//    }

//    public void CallFragment(Fragment fragment, String pos)
//    {
//        Bundle bundle = new Bundle();
//        bundle.putString("position",pos);
//        /*    Toast.makeText(this, "pos: "+pos, Toast.LENGTH_SHORT).show();*/
//        fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.categoryUpload, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }
//    public BottomSheetDialog selectStateDialog;
//    TextView stateHeading;
//    ProgressBar stateProgress;
//    RecyclerView stateRecycler;
//    RentCategoryAdapter stateListAdapter;
//    EditText searchState;
//  //  List<Rent> stateList = new ArrayList<>();
//
//    public void selectStatePopup() {
//        selectStateDialog = new BottomSheetDialog(CategoryActivity.this);
//        selectStateDialog.setContentView(R.layout.custom_select_area_layout);
//        selectStateDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
//        selectStateDialog.getWindow().setTransitionBackgroundFadeDuration(10000);
//
//        stateHeading = selectStateDialog.findViewById(R.id.txtAreaHeading);
//        stateProgress = selectStateDialog.findViewById(R.id.areaProgress);
//        stateRecycler = selectStateDialog.findViewById(R.id.areaRecycler);
//        searchState = selectStateDialog.findViewById(R.id.edtSearchArea);
//
//        stateRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        stateProgress.setVisibility(View.GONE);
//        stateHeading.setText("Select Your Product Category");
//        getStateList();
//
//        searchState.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                stateFilter(String.valueOf(charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        selectStateDialog.show();
//        selectStateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    }
//
//    public void stateFilter(String statename) {
//        List<Rent> newStateList = new ArrayList<>();
//        for (Rent state : stateList) {
//            if (state.getCname().toLowerCase().trim().contains(statename.toLowerCase().trim())) {
//                newStateList.add(state);
//            }
//        }
//        stateListAdapter.filterList(newStateList);
//    }
//
////    public void getStateList() {
////        stateProgress.setVisibility(View.VISIBLE);
////        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
////        Call<categoryRent> call = api.getRentCategory();
////        call.enqueue(new Callback<categoryRent>() {
////            @Override
////            public void onResponse(Call<categoryRent> call, Response<categoryRent> response) {
////                stateProgress.setVisibility(View.GONE);
////                if (response.body() != null) {
////                    if (response.isSuccessful()) {
////                        stateList = response.body().getData();
////                        stateListAdapter = new RentCategoryAdapter(CategoryActivity.this
////                                , null
////                                ,CategoryActivity.this
////                                , response.body().getData());
////                        stateRecycler.setAdapter(stateListAdapter);
////                    } else {
////
////                    }
////                } else {
////                    Log.e("BODY", "Body is null");
////                }
////            }
////
////            @Override
////            public void onFailure(Call<categoryRent> call, Throwable t) {
////                stateProgress.setVisibility(View.GONE);
////                Log.e("EXCEPTION", t.getLocalizedMessage());
////            }
////        });
////    }
//
//    public BottomSheetDialog selectDistrictDialog;
//    TextView districtHeading;
//    ProgressBar districtProgress;
//    RecyclerView districtRecycler;
//    EditText searchDistrict;
//    SubCategoryAdapter districtListAdapter;
//    List<Subcat> districtList = new ArrayList<>();
//
//    public void selectDistrictPopup(String stateId) {
//        selectDistrictDialog = new BottomSheetDialog(CategoryActivity.this);
//        selectDistrictDialog.setContentView(R.layout.custom_select_area_layout);
//        selectDistrictDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
//        selectDistrictDialog.getWindow().setTransitionBackgroundFadeDuration(10000);
//
//        districtHeading = selectDistrictDialog.findViewById(R.id.txtAreaHeading);
//        districtProgress = selectDistrictDialog.findViewById(R.id.areaProgress);
//        districtRecycler = selectDistrictDialog.findViewById(R.id.areaRecycler);
//        searchDistrict = selectDistrictDialog.findViewById(R.id.edtSearchArea);
//
//        districtRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        districtProgress.setVisibility(View.GONE);
//        districtHeading.setText(txtState.getText());
//        getDistrictList(stateId);
//
//        searchDistrict.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                districtFilter(String.valueOf(charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        selectDistrictDialog.show();
//        selectDistrictDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    }
//
//    public void districtFilter(String districtname) {
//        List<Subcat> newDistrictList = new ArrayList<>();
//        for (Subcat district : districtList) {
//            if (district.getSname().toLowerCase().trim().contains(districtname.toLowerCase().trim())) {
//                newDistrictList.add(district);
//            }
//        }
//        districtListAdapter.filterList(newDistrictList);
//    }
//
//    public void getDistrictList(String stateId) {
//        districtProgress.setVisibility(View.VISIBLE);
//        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
//        Call<SubcategoryRent> call = api.getSubcategory(stateId);
//        call.enqueue(new Callback<SubcategoryRent>() {
//            @Override
//            public void onResponse(Call<SubcategoryRent> call, Response<SubcategoryRent> response) {
//                districtProgress.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    if (response.isSuccessful()) {
//                        districtList = response.body().getData();
//                        districtListAdapter = new SubCategoryAdapter(getApplicationContext()
//                                , null
//                                , CategoryActivity.this
//                                , response.body().getData());
//                        districtRecycler.setAdapter(districtListAdapter);
//                    } else {
//
//                    }
//                } else {
//                    Log.e("BODY", "Body is null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SubcategoryRent> call, Throwable t) {
//                districtProgress.setVisibility(View.GONE);
//                Log.e("EXCEPTION", t.getLocalizedMessage());
//            }
//        });
//    }
//
//
//    public BottomSheetDialog selectDistricDialog;
//    TextView districHeading;
//    ProgressBar districProgress;
//    RecyclerView districRecycler;
//    EditText searchDistric;
//    LowCategoryAdapter districListAdapter;
//    List<LowCategory> districList = new ArrayList<>();
//
//
//
//    private void selectProductPopup(String districtId) {
//        selectDistricDialog = new BottomSheetDialog(CategoryActivity.this);
//        selectDistricDialog.setContentView(R.layout.custom_select_area_layout);
//        selectDistricDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
//        selectDistricDialog.getWindow().setTransitionBackgroundFadeDuration(10000);
//
//        districHeading = selectDistricDialog.findViewById(R.id.txtAreaHeading);
//        districProgress = selectDistricDialog.findViewById(R.id.areaProgress);
//        districRecycler = selectDistricDialog.findViewById(R.id.areaRecycler);
//        searchDistric = selectDistricDialog.findViewById(R.id.edtSearchArea);
//
//        districRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        districProgress.setVisibility(View.GONE);
//        districHeading.setText(txtDistrict.getText());
//        getDistricList(districtId);
//
//        searchDistric.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                districFilter(String.valueOf(charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        selectDistricDialog.show();
//        selectDistricDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    }
//
//    public void districFilter(String districtname) {
//        List<LowCategory> newDistrictList = new ArrayList<>();
//        for (LowCategory district : districList) {
//            if (district.getSScName().toLowerCase().trim().contains(districtname.toLowerCase().trim())) {
//                newDistrictList.add(district);
//            }
//        }
//        districListAdapter.filterList(newDistrictList);
//    }
//
//    public void getDistricList(String districtId) {
//        districtProgress.setVisibility(View.VISIBLE);
//        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
//        Call<LowCategoryRent> call = api.getLowcategory(districtId);
//        call.enqueue(new Callback<LowCategoryRent>() {
//            @Override
//            public void onResponse(Call<LowCategoryRent> call, Response<LowCategoryRent> response) {
//                districtProgress.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    if (response.isSuccessful()) {
//                        districList = response.body().getData();
//                        districListAdapter = new LowCategoryAdapter(getApplicationContext()
//                                , null
//                                , CategoryActivity.this
//                                , response.body().getData());
//                        districtRecycler.setAdapter(districListAdapter);
//                    } else {
//
//                    }
//                } else {
//                    Log.e("BODY", "Body is null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LowCategoryRent> call, Throwable t) {
//                districtProgress.setVisibility(View.GONE);
//                Log.e("EXCEPTION", t.getLocalizedMessage());
//            }
//        });
//    }
//
//
//
//    public BottomSheetDialog selectDistriDialog;
//    TextView distriHeading;
//    ProgressBar distriProgress;
//    RecyclerView distriRecycler;
//    EditText searchDistri;
//    FinalCategoryAdapter distriListAdapter;
//    List<FinalCategoryy> distriList = new ArrayList<>();
//
//    private void selectFinalProductPopup(String productId) {
//        selectDistriDialog = new BottomSheetDialog(CategoryActivity.this);
//        selectDistriDialog.setContentView(R.layout.custom_select_area_layout);
//        selectDistriDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
//        selectDistriDialog.getWindow().setTransitionBackgroundFadeDuration(10000);
//
//        distriHeading = selectDistriDialog.findViewById(R.id.txtAreaHeading);
//        distriProgress = selectDistriDialog.findViewById(R.id.areaProgress);
//        distriRecycler = selectDistriDialog.findViewById(R.id.areaRecycler);
//        searchDistri = selectDistriDialog.findViewById(R.id.edtSearchArea);
//
//        distriRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        distriProgress.setVisibility(View.GONE);
//        distriHeading.setText(txtDistrict.getText());
//        getDistriList(productId);
//
//        searchDistri.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                distriFilter(String.valueOf(charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        selectDistriDialog.show();
//        selectDistriDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    }
//
//
//
//    public void distriFilter(String distritname) {
//        List<FinalCategoryy> newDistrictList = new ArrayList<>();
//        for (FinalCategoryy distric : distriList) {
//            if (distric.getConcatenatedName().toLowerCase().trim().contains(distritname.toLowerCase().trim())) {
//                newDistrictList.add(distric);
//            }
//        }
//        distriListAdapter.filterList(newDistrictList);
//    }
//
//    public void getDistriList(String productId) {
//        districtProgress.setVisibility(View.VISIBLE);
//        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
//        Call<FinalCategory> call = api.getFinalCategory(productId);
//        call.enqueue(new Callback<FinalCategory>() {
//            @Override
//            public void onResponse(Call<FinalCategory> call, Response<FinalCategory> response) {
//                districtProgress.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    if (response.isSuccessful()) {
//                        distriList = response.body().getData();
//                        distriListAdapter = new FinalCategoryAdapter(getApplicationContext()
//                                , null
//                                , CategoryActivity.this
//                                , response.body().getData());
//                        distriRecycler.setAdapter(distriListAdapter);
//                    } else {
//
//                    }
//                } else {
//                    Log.e("BODY", "Body is null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FinalCategory> call, Throwable t) {
//                districtProgress.setVisibility(View.GONE);
//                Log.e("EXCEPTION", t.getLocalizedMessage());
//            }
//        });
//    }
//

}