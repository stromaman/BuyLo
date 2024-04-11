package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecom.buylo.Adapter.RecentlyViewAdapter;
import com.ecom.buylo.Adapter.SeasonListAdapter;
import com.ecom.buylo.Adapter.SliderAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Model.Season;
import com.ecom.buylo.Model.request.BannerModel;
import com.ecom.buylo.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView categoryRecycler,recentlyRecycler,seasonRecycler,recentlyRecycler1;
    SliderView slider;
    RelativeLayout myWishLayout,myprofileLayout;
    TextView txtSearch;
    LinearLayout menuLayout,categoryLayout;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer speechRecognizer;
    ProgressBar dashProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        categoryRecycler=findViewById(R.id.categoryRecycler);
        recentlyRecycler=findViewById(R.id.recentlyRecycler);
        menuLayout = findViewById(R.id.menuLayout);
        seasonRecycler=findViewById(R.id.seasonRecycler);
      //  myWishLayout=findViewById(R.id.myWishLayout);
        myprofileLayout=findViewById(R.id.myprofileLayout);
        categoryLayout=findViewById(R.id.categoryLayout);
        slider=findViewById(R.id.slider);
        txtSearch=findViewById(R.id.txtSearch);
        dashProgress = findViewById(R.id.dashProgress);

        dashProgress.setVisibility(View.GONE);
        categoryLayout.setVisibility(View.GONE);
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                // Do something when ready for speech
            }

            @Override
            public void onBeginningOfSpeech() {
                // Do something when the user starts speaking
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // Do something when RMS changes
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // Do something with the audio buffer
            }

            @Override
            public void onEndOfSpeech() {
                // Do something when the user stops speaking
            }

            @Override
            public void onError(int error) {
                // Handle recognition errors
            }

            @Override
            public void onResults(Bundle results) {
                // Get the recognized speech
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null && !matches.isEmpty()) {
                    String query = matches.get(0);
                    txtSearch.setText(query);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // Handle partial recognition results
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // Handle events
            }
        });

        // Set onClickListener for the mic button
        ImageView micButton = findViewById(R.id.micButton);
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
//        menuLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getDrawerDialog();
//            }
//        });

        myWishLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, WishListActivity.class);
                startActivity(intent);
            }
        });

        myprofileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        //getCategoryList();
       // BannerApi();



//        ArrayList<RecentlyView> categoriess= new ArrayList<>();
//        categoriess.add(new RecentlyView(R.drawable.presser));
//        categoriess.add(new RecentlyView(R.drawable.juicer));
//        categoriess.add(new RecentlyView(R.drawable.laptop));
//        categoriess.add(new RecentlyView(R.drawable.kurkur));
//        recentlyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
//        recentlyRecycler.setHasFixedSize(true);
//        RecentlyViewAdapter adapter1 =new RecentlyViewAdapter(getApplicationContext(), DashboardActivity.this,categoriess);
//        recentlyRecycler.setAdapter(adapter1);



//        ArrayList<Season> seasons= new ArrayList<>();
//        seasons.add(new Season(R.drawable.heater));
//        seasons.add(new Season(R.drawable.coffee));
//        seasons.add(new Season(R.drawable.heater));
//        seasons.add(new Season(R.drawable.coffee));
//
//        seasonRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        seasonRecycler.setHasFixedSize(true);
//        SeasonListAdapter adapter2 =new SeasonListAdapter(getApplicationContext(), DashboardActivity.this,seasons);
//        seasonRecycler.setAdapter(adapter2);
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {
            // Handle exception when speech recognition is not supported
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String query = result.get(0);
                txtSearch.setText(query);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }

    Dialog drawerDialog;
    LinearLayout transLayer, drawerProfileLayout, drawerMyCartLayout,
            drawerOrderHistoryLayout, drawerAddressLayout, logoutLayout,
            drawerAboutUsLayout, drawerJoinSellerLayout, drawerPrivacyLayout, drawerTermsLayout, drawerReturnLayout, drawerQualityLayout,drawertrackOrderLayout, drawerContactLayout;
    TextView txtVersionName, userName, userNumber;
    CircleImageView userImage;

    public void getDrawerDialog() {
        drawerDialog = new Dialog(DashboardActivity.this);
        drawerDialog.setContentView(R.layout.custom_drawer_layout);
        drawerDialog.setCancelable(true);
        transLayer = drawerDialog.findViewById(R.id.transLayer);
        drawerProfileLayout = drawerDialog.findViewById(R.id.drawerProfileLayout);
        transLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerDialog.dismiss();
            }
        });

        drawerProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerDialog.dismiss();
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        drawerDialog.show();
        drawerDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        drawerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        drawerDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationReport;
        drawerDialog.getWindow().setGravity(Gravity.TOP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawerDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            drawerDialog.getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.white));
        }
    }

//    public void getCategoryList() {
//        dashProgress.setVisibility(View.VISIBLE);
//        categoryRecycler = findViewById(R.id.categoryRecycler);
//        categoryRecycler.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));
//        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
//        Call<CategoryModel> call = api.category();
//        call.enqueue(new Callback<CategoryModel>() {
//            @Override
//            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
//                dashProgress.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    if (response.body().getStatus()) {
//                        categoryLayout.setVisibility(View.VISIBLE);
//                        CategoryListAdapter categoryAdapter = new CategoryListAdapter(getApplicationContext()
//                                , DashboardActivity.this
//                                , response.body().getCatRes());
//                        categoryRecycler.setAdapter(categoryAdapter);
//                    } else {
//                        //      categoryLayout.setVisibility(View.GONE);
//                        /*Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
//                                .setBackgroundTint(Color.parseColor("#EA2525"))
//                                .setTextColor(Color.WHITE)
//                                .show();*/
//                    }
//                } else {
//                    Log.e("BODY", "Body is null");
//                }
//                if (InternetValidation.validation(DashboardActivity.this)) {
//
//                } else {
//                    InternetValidation.getNoConnectionDialog(DashboardActivity.this);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryModel> call, Throwable t) {
//                dashProgress.setVisibility(View.GONE);
//                Log.e("EXCEPTION", t.getLocalizedMessage());
//                if (InternetValidation.validation(DashboardActivity.this)) {
//                    BannerApi();
//                } else {
//                    InternetValidation.getNoConnectionDialog(DashboardActivity.this);
//                }
//            }
//        });
//    }

//    private void BannerApi() {
//        try{
//          //  MyPreferences preferences = new MyPreferences(DashboardActivity.this);
//            ServiceApi serviceApi = ApiClient.getClient().create(ServiceApi.class);
//            Call<BannerModel> call = serviceApi.bannerimg();
//            call.enqueue(new Callback<BannerModel>() {
//                @Override
//                public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
//                    if (response.body() != null) {
//                        if (response.body().getStatus()) {
//
//                            SliderAdapter adapter = new SliderAdapter(DashboardActivity.this,response.body().getBannerRes());
//                            slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//                            slider.setSliderAdapter(adapter);
//                            slider.setScrollTimeInSec(3);
//                            slider.setAutoCycle(true);
//                            slider.startAutoCycle();
//
//
//                            Log.d("response",""+response.body());
//                        } else {
//                            Log.d("response",""+response.body());
//                            Toast.makeText(DashboardActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BannerModel> call, Throwable t) {
//                    //  Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("Error", t.getLocalizedMessage());
//                }
//            });
//
//
//        }catch (Exception e){
//            Log.e("Exception",e.getLocalizedMessage());}
//
//    }
}