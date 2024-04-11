package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.response.OtpRequest;
import com.ecom.buylo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpSenderActivity extends AppCompatActivity {
    CardView getOTP;
    ImageView Back;
    TextInputEditText edtNumber;
    RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_sender);
        getSupportActionBar().hide();
       // getWindow().setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        getWindow().setStatusBarColor(Color.parseColor("#FFFFFFFF")); // Set the status bar color to white


        getOTP=findViewById(R.id.getOTP);
        Back=findViewById(R.id.Back);
        edtNumber=findViewById(R.id.edtNumber);
        parentLayout=findViewById(R.id.parentLayout);


        Back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtpSenderActivity.super.onBackPressed();
            }
        });
        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OtpSenderActivity.this, DashBoardMainActivity.class);
                startActivity(intent);
            }
        });

//        getOTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(edtNumber.length()==0){
//                    Snackbar.make(parentLayout, "Please Enter Number", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }else if(edtNumber.length()<10){
//                    Snackbar.make(parentLayout, "Please Enter Valid Number", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }else{
//                    Log.e("NUMBER",edtNumber.getText().toString());
//                    getLoginOtp(edtNumber.getText().toString());
//                }
//
//            }
//        });
    }

    private void getLoginOtp(String number) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<OtpRequest> call = api.getLoginOtp(number);
        call.enqueue(new Callback<OtpRequest>() {
            @Override
            public void onResponse(Call<OtpRequest> call, Response<OtpRequest> response) {

                if (response.body() != null) {
                    if (response.body().getResult()) {
                        Intent intent = new Intent(OtpSenderActivity.this, VerifyOtpActivity.class);
                        intent.putExtra("type", "number");
                        intent.putExtra("number", number);
                        intent.putExtra("otp", response.body().getOtp());
                        Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#12d06b")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                       startActivity(intent);
                    } else {
                        Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                } else {
                    Log.e("BODY", "Body is null");
                }

            }

            @Override
            public void onFailure(Call<OtpRequest> call, Throwable t) {
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }
}