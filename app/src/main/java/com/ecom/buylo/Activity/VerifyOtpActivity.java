package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.verifyOtp;
import com.ecom.buylo.Model.response.OtpRequest;
import com.ecom.buylo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends AppCompatActivity {
    String name, number, email, password, otp, type, address, state_id, district_id, pincode;
    TextView txtOtp, txtNumber, txtResend;
    PinView otpView;
    CardView btnVerify;
    ImageView Back;
    LinearLayout resendLayout;
    RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        getWindow().setStatusBarColor(Color.parseColor("#FFFFFFFF")); // Set the status bar color to white
        txtNumber=findViewById(R.id.txtNumber);
        resendLayout = findViewById(R.id.resendLayout);
        txtOtp = findViewById(R.id.txtOtp);
        btnVerify=findViewById(R.id.btnVerify);
        Back=findViewById(R.id.Back);
        parentLayout=findViewById(R.id.parentLayout);
        resendLayout.setVisibility(View.GONE);
        otpView=findViewById(R.id.otpView);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            type = extra.getString("type");
            if (type.equals("signup")) {
                resendLayout.setVisibility(View.VISIBLE);

                number = extra.getString("number");
                otp = extra.getString("otp");

                Log.e("NUMBER", number);
                Log.e("OTP", otp);

            } else {
                number = extra.getString("number");
                otp = extra.getString("otp");
                Log.e("NUMBER", number);
                Log.e("OTP", otp);
            }
            txtOtp.setText(otp);
           txtNumber.setText(number);


        }

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpView.length() == 0) {
                    Snackbar.make(parentLayout, "Please Enter OTP", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                            .setTextColor(Color.WHITE)
                            .show();
                } else if (otpView.length() < 6) {
                    Snackbar.make(parentLayout, "Please Enter Valid OTP", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                            .setTextColor(Color.WHITE)
                            .show();
                } else {
                    verifyOtp(number, otp);
                }
            }
        });

        Back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyOtpActivity.super.onBackPressed();
            }
        });



    }



    private void fillOTP(String otp) {
        otpView.setText(otp);
    }

    private void verifyOtp(String number, String otp) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<verifyOtp> call = api.getUser(number,otp);
        call.enqueue(new Callback<verifyOtp>() {
            @Override
            public void onResponse(Call<verifyOtp> call, Response<verifyOtp> response) {

                if (response.isSuccessful()) {
                    if (response.body().getResult()) {
                        Intent intent = new Intent(VerifyOtpActivity.this, LocationActivity.class);
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
            public void onFailure(Call<verifyOtp> call, Throwable t) {
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

}