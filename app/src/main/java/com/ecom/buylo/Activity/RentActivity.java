package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecom.buylo.Adapter.CategoryRentAdapter;
import com.ecom.buylo.Adapter.RecentlyViewAdapter;
import com.ecom.buylo.Adapter.SeasonListAdapter;
import com.ecom.buylo.Adapter.SliderAdapter;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Model.RentCategory;
import com.ecom.buylo.Model.Season;
import com.ecom.buylo.Model.SliderData;
import com.ecom.buylo.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Locale;

public class RentActivity extends AppCompatActivity {

    CardView buy;
    RecyclerView categoryRecycler,recentlyRecycler,seasonRecycler;
    SliderView slider;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer speechRecognizer;
    TextView txtSearch;
    ImageView micButton;
    private MotionLayout motionLayout;
    CardView rent;
    LinearLayout homelayout,homeshowlayout,cateLayout,cateShowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#d1ebed"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        buy=findViewById(R.id.buy);
        categoryRecycler=findViewById(R.id.categoryRecycler);
        recentlyRecycler=findViewById(R.id.recentlyRecycler);
        seasonRecycler=findViewById(R.id.seasonRecycler);
        txtSearch=findViewById(R.id.txtSearch);
        slider=findViewById(R.id.slider);
        micButton = findViewById(R.id.micButton);
        rent = findViewById(R.id.rent);
        homelayout=findViewById(R.id.homelayout);
        homeshowlayout=findViewById(R.id.homeshowlayout);
        cateLayout=findViewById(R.id.cateLayout);
        cateShowLayout=findViewById(R.id.cateShowLayout);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RentActivity.this, DashBoardMainActivity.class);
                startActivity(intent);
            }
        });
//        homelayout.setVisibility(View.VISIBLE);
//        homeshowlayout.setVisibility(View.GONE);
//        cateLayout.setVisibility(View.VISIBLE);
//        cateShowLayout.setVisibility(View.GONE);



        cateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RentActivity.this, CategoryRentActivity.class);
                if (homelayout.getVisibility() == View.VISIBLE){
                    homelayout.setVisibility(View.GONE);
                    cateLayout.setVisibility(View.GONE);
                    cateShowLayout.setVisibility(View.VISIBLE);
                    homeshowlayout.setVisibility(View.VISIBLE);
                }
                else {


                }
                startActivity(intent);

            }
        });





//        motionLayout = view.findViewById(R.id.motionLayout);


        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList.add(new SliderData(R.drawable.image1));
        sliderDataArrayList.add(new SliderData(R.drawable.images2));
        sliderDataArrayList.add(new SliderData(R.drawable.image1));
        sliderDataArrayList.add(new SliderData(R.drawable.images2));
        sliderDataArrayList.add(new SliderData(R.drawable.image1));

        SliderAdapter SliderAdapter = new SliderAdapter(getApplicationContext(),null,RentActivity.this, sliderDataArrayList);
        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        slider.setSliderAdapter(SliderAdapter);
        slider.setScrollTimeInSec(3);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        ArrayList<RentCategory> categoriess= new ArrayList<>();
        categoriess.add(new RentCategory(R.drawable.presser,"Cloth","200/Day"));
        categoriess.add(new RentCategory(R.drawable.juicer,"Juicer","100/Day"));
        categoriess.add(new RentCategory(R.drawable.laptop,"Laptop","120/hour"));
        categoriess.add(new RentCategory(R.drawable.sound,"Sound Box","1120/hour"));
        categoriess.add(new RentCategory(R.drawable.car,"Car","120/km"));
        categoriess.add(new RentCategory(R.drawable.guitar,"Guitar","900/Day"));
        categoryRecycler.setHasFixedSize(true);
        CategoryRentAdapter adapter1 =new CategoryRentAdapter(getApplicationContext(), RentActivity.this,categoriess);
        categoryRecycler.setAdapter(adapter1);

        ArrayList<RecentlyView> categories= new ArrayList<>();
        categories.add(new RecentlyView(R.drawable.presser));
        categories.add(new RecentlyView(R.drawable.juicer));
        categories.add(new RecentlyView(R.drawable.laptop));
        categories.add(new RecentlyView(R.drawable.kurkur));
        recentlyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        recentlyRecycler.setHasFixedSize(true);
        RecentlyViewAdapter adapter =new RecentlyViewAdapter(getApplicationContext(), null,RentActivity.this,categories);
        recentlyRecycler.setAdapter(adapter);

        ArrayList<Season> seasons= new ArrayList<>();
        seasons.add(new Season(R.drawable.heater));
        seasons.add(new Season(R.drawable.coffee));
        seasons.add(new Season(R.drawable.heater));
        seasons.add(new Season(R.drawable.coffee));

        seasonRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        seasonRecycler.setHasFixedSize(true);
        SeasonListAdapter adapter2 =new SeasonListAdapter(getApplicationContext(), null,RentActivity.this,seasons);
        seasonRecycler.setAdapter(adapter2);


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RentActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
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

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SearchPageActivity.class);
                startActivity(intent);
            }
        });
//        Rent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), RentActivity.class);
//                startActivity(intent);
//            }
//        });


        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

//        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
//            @Override
//            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
//                // Called when the transition position changes
//                // You can use this method to update UI elements based on the animation progress
//            }
//
//            @Override
//            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
//                // Called when the transition completes
//                if (currentId == R.id.end) {
//                    // The animation has completed and the layout is in the 'end' state
//                    // Add any specific actions you want to perform when the animation finishes
//                }
//            }
//
//            @Override
//            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
//                // Called when the transition starts
//                if (startId == R.id.start && endId == R.id.end) {
//                    // The transition is from 'start' to 'end'
//                    // Add any specific actions you want to perform when the animation starts
//                }
//            }
//
//            @Override
//            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
//                // Called when a trigger is reached during the transition
//                if (triggerId == R.id.motionLayout && positive) {
//                    // The specified trigger is reached, and it is in a positive direction
//                    // Add any specific actions you want to perform when the trigger is reached
//                }
//            }
//        });


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
}