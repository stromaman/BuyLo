package com.ecom.buylo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecom.buylo.Adapter.CityListAdapter;
import com.ecom.buylo.Adapter.DistrictListAdapter;
import com.ecom.buylo.Adapter.StateListAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.CityRequest;
import com.ecom.buylo.Model.request.DistrictRequest;
import com.ecom.buylo.Model.request.StateRequest;
import com.ecom.buylo.Model.request.Vendor;
import com.ecom.buylo.Model.response.City;
import com.ecom.buylo.Model.response.District;
import com.ecom.buylo.Model.response.State;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.InternetValidation;
import com.ecom.buylo.Utils.MyPreferences;
import com.ecom.buylo.Utils.utils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerHubActivity extends AppCompatActivity {
    CircleImageView imgProfile;
    CardView openCamera;
    MaterialButton started;
    Bitmap bitmapImage;
    File file;
    Uri returnUri;
    EditText edtName,edtMobileno,edtWhatsupno,edtemail,edtlocation;
    CheckBox checkBox;
    boolean isAllFieldsChecked = false;
    RelativeLayout parentLayout;
    ProgressBar progress;
    TextView txtlocation;
    public TextView txtState, txtDistrict,txtCity;
    public String stateId, districtId,cityId, payment_code;
    private static final int REQUEST_MAP_LOCATION = 1;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_hub);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        started=findViewById(R.id.started);
        openCamera=findViewById(R.id.openCamera);
        imgProfile=findViewById(R.id.imgProfile);
        edtName=findViewById(R.id.edtName);
        edtMobileno=findViewById(R.id.edtNumber);
        edtWhatsupno=findViewById(R.id.edtwhtNumber);
        edtemail=findViewById(R.id.edtEmail);
        edtlocation=findViewById(R.id.edtlocation);
        txtState=findViewById(R.id.txtState);
        txtDistrict=findViewById(R.id.txtDistrict);
        txtCity=findViewById(R.id.txtCity);
        txtlocation=findViewById(R.id.txtlocation);
       // checkBox = findViewById(R.id.checkBox);
        parentLayout = findViewById(R.id.parentLayout);
        progress = findViewById(R.id.progress);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Button buttonGetLocation = findViewById(R.id.buttonGetLocation);
        progress.setVisibility(View.GONE);
        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = checkAllFields();
                if (isAllFieldsChecked) {
                    MyPreferences preferences = MyPreferences.getInstance(getApplicationContext());
                    Log.e("NAME", edtName.getText().toString().trim());
                    Log.e("NUMBER", edtMobileno.getText().toString().trim());
                    Log.e("Whatsapp", edtWhatsupno.getText().toString().trim());
                    Log.e("Email", edtemail.getText().toString().trim());
                    Log.e("Email",  stateId);
                    Log.e("Email",  districtId);
                    Log.e("Email",  cityId);
                    Log.e("Email",  txtlocation.getText().toString());
                    Log.e("Location", edtlocation.getText().toString());

                    doUserRegistration(edtName.getText().toString().trim()
                            , edtMobileno.getText().toString().trim()
                            ,edtWhatsupno.getText().toString().trim()
                            ,edtlocation.getText().toString()
                            , edtemail.getText().toString().trim()
                            ,txtlocation.getText().toString()
                            ,stateId
                            ,districtId
                            ,cityId
                            ,returnUri);
                }
            }
        });

        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SellerHubActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(SellerHubActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SellerHubActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                    return;
                }
                requestLocationUpdates();
            }
        });



        txtState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectStatePopup();
            }
        });
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isChecked = checkBox.isChecked();
//                // Use the 'isChecked' boolean as needed (true if checked, false if unchecked)
//                if (isChecked) {
//                    // Do something when CheckBox is checked (true)
//                } else {
//                    // Do something when CheckBox is unchecked (false)
//                }
//            }
//        });
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetValidation.validation(SellerHubActivity.this)) {
                    imageChooser();
                } else {
                    InternetValidation.getNoConnectionDialog(SellerHubActivity.this);
                }
            }
        });


        txtDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stateId != null) {

                    if (InternetValidation.validation(SellerHubActivity.this)) {
                        selectDistrictPopup(stateId);
                    } else {
                        InternetValidation.getNoConnectionDialog(SellerHubActivity.this);
                    }

                } else {
                    Snackbar.make(parentLayout, "Please Select State...!!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                            .setTextColor(Color.WHITE)
                            .show();
                }
            }
        });

        txtCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (districtId != null) {

                    if (InternetValidation.validation(SellerHubActivity.this)) {
                        selectCityPopup(districtId);
                        Log.e("district",districtId);
                    } else {
                        InternetValidation.getNoConnectionDialog(SellerHubActivity.this);
                    }

                } else {
                    Snackbar.make(parentLayout, "Please Select District...!!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                            .setTextColor(Color.WHITE)
                            .show();
                }
            }
        });
    }



    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Update location every 10 seconds

        if (locationCallback == null) {
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                            if (addresses != null && addresses.size() > 0) {
                                Address address = addresses.get(0);
                                StringBuilder fullAddress = new StringBuilder();
                                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                                    fullAddress.append(address.getAddressLine(i)).append(" ");
                                }
                                edtlocation.setText(fullAddress.toString());
                                txtlocation.setText(utils.getGPSLocation(SellerHubActivity.this));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        fusedLocationClient.removeLocationUpdates(locationCallback);
                    }
                }
            };
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }



    public BottomSheetDialog selectStateDialog;
    TextView stateHeading;
    ProgressBar stateProgress;
    RecyclerView stateRecycler;
    StateListAdapter stateListAdapter;
    EditText searchState;
    List<State> stateList = new ArrayList<>();

    public void selectStatePopup() {
        selectStateDialog = new BottomSheetDialog(SellerHubActivity.this);
        selectStateDialog.setContentView(R.layout.custom_select_area_layout);
        selectStateDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectStateDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        stateHeading = selectStateDialog.findViewById(R.id.txtAreaHeading);
        stateProgress = selectStateDialog.findViewById(R.id.areaProgress);
        stateRecycler = selectStateDialog.findViewById(R.id.areaRecycler);
        searchState = selectStateDialog.findViewById(R.id.edtSearchArea);

        stateRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        stateProgress.setVisibility(View.GONE);
        stateHeading.setText("Select Your State");
        getStateList();

        searchState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stateFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectStateDialog.show();
        selectStateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void stateFilter(String statename) {
        List<State> newStateList = new ArrayList<>();
        for (State state : stateList) {
            if (state.getName().toLowerCase().trim().contains(statename.toLowerCase().trim())) {
                newStateList.add(state);
            }
        }
        stateListAdapter.filterList(newStateList);
    }

    public void getStateList() {
        stateProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<StateRequest> call = api.getStateList();
        call.enqueue(new Callback<StateRequest>() {
            @Override
            public void onResponse(Call<StateRequest> call, Response<StateRequest> response) {
                stateProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        stateList = response.body().getData();
                        stateListAdapter = new StateListAdapter(getApplicationContext()
                                , SellerHubActivity.this
                                , response.body().getData());
                        stateRecycler.setAdapter(stateListAdapter);
                    } else {
                        Snackbar.make(parentLayout, "" + response.body().getData(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<StateRequest> call, Throwable t) {
                stateProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }


    public BottomSheetDialog selectDistrictDialog;
    TextView districtHeading;
    ProgressBar districtProgress;
    RecyclerView districtRecycler;
    EditText searchDistrict;
    DistrictListAdapter districtListAdapter;
    List<District> districtList = new ArrayList<>();

    public void selectDistrictPopup(String stateId) {
        selectDistrictDialog = new BottomSheetDialog(SellerHubActivity.this);
        selectDistrictDialog.setContentView(R.layout.custom_select_area_layout);
        selectDistrictDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectDistrictDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        districtHeading = selectDistrictDialog.findViewById(R.id.txtAreaHeading);
        districtProgress = selectDistrictDialog.findViewById(R.id.areaProgress);
        districtRecycler = selectDistrictDialog.findViewById(R.id.areaRecycler);
        searchDistrict = selectDistrictDialog.findViewById(R.id.edtSearchArea);

        districtRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        districtProgress.setVisibility(View.GONE);
        districtHeading.setText("Select Your District");
        getDistrictList(stateId);

        searchDistrict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                districtFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectDistrictDialog.show();
        selectDistrictDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void districtFilter(String districtname) {
        List<District> newDistrictList = new ArrayList<>();
        for (District district : districtList) {
            if (district.getName().toLowerCase().trim().contains(districtname.toLowerCase().trim())) {
                newDistrictList.add(district);
            }
        }
        districtListAdapter.filterList(newDistrictList);
    }

    public void getDistrictList(String stateId) {
        districtProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<DistrictRequest> call = api.getDistrictList(stateId);
        call.enqueue(new Callback<DistrictRequest>() {
            @Override
            public void onResponse(Call<DistrictRequest> call, Response<DistrictRequest> response) {
                districtProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        districtList = response.body().getData();
                        districtListAdapter = new DistrictListAdapter(getApplicationContext()
                                , SellerHubActivity.this
                                , response.body().getData());
                        districtRecycler.setAdapter(districtListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<DistrictRequest> call, Throwable t) {
                districtProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }


    public BottomSheetDialog selectDistricDialog;
    TextView districHeading;
    ProgressBar districProgress;
    RecyclerView districRecycler;
    EditText searchDistric;
    CityListAdapter districListAdapter;
    List<City> districList = new ArrayList<>();

    public void selectCityPopup(String districtId) {
        selectDistricDialog = new BottomSheetDialog(SellerHubActivity.this);
        selectDistricDialog.setContentView(R.layout.custom_select_area_layout);
        selectDistricDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectDistricDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        districHeading = selectDistricDialog.findViewById(R.id.txtAreaHeading);
        districProgress = selectDistricDialog.findViewById(R.id.areaProgress);
        districRecycler = selectDistricDialog.findViewById(R.id.areaRecycler);
        searchDistric = selectDistricDialog.findViewById(R.id.edtSearchArea);

        districRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        districProgress.setVisibility(View.GONE);
        districHeading.setText("Select Your City");
        getCityList(districtId);

        searchDistric.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                districFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectDistricDialog.show();
        selectDistricDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void districFilter(String cityname) {
        List<City> newCityList = new ArrayList<>();
        for (City distric : districList) {
            if (distric.getName().toLowerCase().trim().contains(cityname.toLowerCase().trim())) {
                newCityList.add(distric);
            }
        }
        districListAdapter.filterLis(newCityList);
    }

    public void getCityList(String districtId) {
        districtProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<CityRequest> call = api.getCityList(districtId);
        call.enqueue(new Callback<CityRequest>() {
            @Override
            public void onResponse(Call<CityRequest> call, Response<CityRequest> response) {
                districtProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        Log.e("start", String.valueOf(response.isSuccessful()));
                        districList = response.body().getData();
                        districListAdapter = new CityListAdapter(getApplicationContext()
                                , SellerHubActivity.this
                                , response.body().getData());
                        districRecycler.setAdapter(districListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<CityRequest> call, Throwable t) {
                districtProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    private void doUserRegistration(String name, String mobileno, String whatsapp,String location, String email,String LatandLang,String stateId,String districtId,String cityId,  Uri returnUri) {
        progress.setVisibility(View.VISIBLE);
        MultipartBody.Part propertyStartRideImage = null;
        if (returnUri != null) {
            //creating a file
            File filePARCEL = new File(getRealPathFromUri(returnUri));

            if (filePARCEL != null) {
                RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), filePARCEL.getAbsoluteFile());
                propertyStartRideImage = MultipartBody.Part.createFormData("ProfileImage",
                        filePARCEL.getName(), propertyImage);
            } else {
                RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");
                propertyStartRideImage = MultipartBody.Part.createFormData("ProfileImage", "", attachmentEmpty);
            }
        }

        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<Vendor> call = api.getRegister(RequestBody.create(MediaType.parse("multipart/form-data"),name ),
                        RequestBody.create(MediaType.parse("multipart/form-data"), mobileno),
                        RequestBody.create(MediaType.parse("multipart/form-data"), whatsapp),
                        RequestBody.create(MediaType.parse("multipart/form-data"), location),
                        RequestBody.create(MediaType.parse("multipart/form-data"), email),
                        RequestBody.create(MediaType.parse("multipart/form-data"), LatandLang),
                        RequestBody.create(MediaType.parse("multipart/form-data"), stateId),
                        RequestBody.create(MediaType.parse("multipart/form-data"), districtId),
                        RequestBody.create(MediaType.parse("multipart/form-data"), cityId),
                        propertyStartRideImage
                );

        call.enqueue(new Callback<Vendor>() {
            @Override
            public void onResponse(Call<Vendor> call, Response<Vendor> response) {
                progress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().getResult()) {
                            String username = response.body().getData().getUsername();
                            String password = response.body().getData().getPassword();
                        MyPreferences.getInstance(getApplicationContext())
                                .saveVendor(response.body().getData().getUserid()
                                         ,response.body().getData().getUsername()
                                        , response.body().getData().getPassword());
                            showDetailsPopup(username, password);


                    } else {
                        Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                } else {
                    Log.d("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<Vendor> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("FAILURE", t.getLocalizedMessage());
            }
        });
    }

    private void showDetailsPopup(String username, String password) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.custom_popup_show, null);

        // Find TextViews within the layout
        TextView textViewUsername = popupView.findViewById(R.id.userName);
        TextView textViewPassword = popupView.findViewById(R.id.password);

        // Set the username and password
        textViewUsername.setText(username);
        textViewPassword.setText(password);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView)
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(SellerHubActivity.this, SellerDashBoardActivity.class);

                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public boolean checkAllFields() {
        if (edtName.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter Name", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }

        if (edtMobileno.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter Number", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        } else if (edtMobileno.length() < 10) {
            Snackbar.make(parentLayout, "Please Enter Valid Number", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }
        if (edtWhatsupno.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter Your WhatsApp Number", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        } else if (edtWhatsupno.length() < 10) {
            Snackbar.make(parentLayout, "Please Enter Your WhatsApp Number", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }
        if (edtemail.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter Email Address", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        } else if (!edtemail.getText().toString().contains("@")) {
            Snackbar.make(parentLayout, "Please Enter Valid Email Address", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }

        if (edtlocation.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter your Shop Location", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }
        if (txtlocation.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter your Shop Location", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;
        }



        return true;
    }

    private void imageChooser() {
        ImagePicker.Companion.with(this)
                /*.saveDir(new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), ""+getResources().getString(R.string.app_name)))*/
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(300)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(400, 550)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_MAP_LOCATION && resultCode == RESULT_OK && data != null) {
            double latitude = data.getDoubleExtra("latitude", 0.0);
            double longitude = data.getDoubleExtra("longitude", 0.0);

            // Update the EditText with the selected location
            EditText edtLocation = findViewById(R.id.edtlocation);
            edtLocation.setText(latitude + ", " + longitude);
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            returnUri = data.getData();
            if (returnUri != null) {
                try {
                    file = new File(getRealPathFromUri(returnUri));
                    bitmapImage = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), returnUri);
                    imgProfile.setImageBitmap(bitmapImage);
                    Log.e("FILE PATH: ", file.toString());
                    Log.e("BITMAP PATH: ", bitmapImage.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getRealPathFromUri(final Uri uri) { // function for file path from uri,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(SellerHubActivity.this, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(SellerHubActivity.this, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(SellerHubActivity.this, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(SellerHubActivity.this, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Drive.
     */
    private static boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority()) ||
                "com.google.android.apps.docs.storage.legacy".equals(uri.getAuthority());
    }

    private static boolean isWhatsAppUri(Uri uri) {
        return "com.whatsapp.provider.media".equals(uri.getAuthority());
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                String value = cursor.getString(index);
                if (value.startsWith("content://") || !value.startsWith("/") && !value.startsWith("file://")) {
                    return null;
                }

                return value;
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;

    }
}