package com.ecom.buylo.Fragment;

import static com.ecom.buylo.R.layout.fragment_home_main;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ecom.buylo.Activity.SearchPageActivity;
import com.ecom.buylo.Adapter.CategoryListAdapter;
import com.ecom.buylo.Adapter.RecentlyBuyViewAdapter;
import com.ecom.buylo.Adapter.RecentlyViewAdapter;
import com.ecom.buylo.Adapter.SeasonListAdapter;
import com.ecom.buylo.Adapter.SliderAdapter;
import com.ecom.buylo.Model.Category;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Model.Season;
import com.ecom.buylo.Model.SliderData;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.RentActivity;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Locale;


public class HomeMainFragment extends Fragment {

    RecyclerView categoryRecycler,recentlyRecycler,seasonRecycler;
    SliderView slider;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer speechRecognizer;
    ImageView micButton;
    CardView rent;
    EditText txtSearch;
    MotionLayout motionLayout;
    LinearLayout menuLayout,searchLayout;
    NestedScrollView nestedScrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view=inflater.inflate(fragment_home_main, container, false);
        categoryRecycler=view.findViewById(R.id.categoryRecycler);
        recentlyRecycler=view.findViewById(R.id.recentlyRecycler);
        seasonRecycler=view.findViewById(R.id.seasonRecycler);
        txtSearch=view.findViewById(R.id.txtSearch);
        slider=view.findViewById(R.id.slider);
        micButton = view.findViewById(R.id.micButton);
        rent = view.findViewById(R.id.rent);
//        motionLayout = view.findViewById(R.id.motionLayout);
        menuLayout =view.findViewById(R.id.menuLayout);
        searchLayout = view.findViewById(R.id.searchLayout);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList.add(new SliderData(R.drawable.image1));
        sliderDataArrayList.add(new SliderData(R.drawable.images2));
        sliderDataArrayList.add(new SliderData(R.drawable.image1));
        sliderDataArrayList.add(new SliderData(R.drawable.images2));
        sliderDataArrayList.add(new SliderData(R.drawable.image1));

        SliderAdapter SliderAdapter = new SliderAdapter(getContext(),HomeMainFragment.this,null, sliderDataArrayList);
        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        slider.setSliderAdapter(SliderAdapter);
        slider.setScrollTimeInSec(3);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        ArrayList<Category> categoriess= new ArrayList<>();
        categoriess.add(new Category(R.drawable.presser,"Cloth"));
        categoriess.add(new Category(R.drawable.juicer,"Juicer"));
        categoriess.add(new Category(R.drawable.laptop,"Laptop"));
        categoriess.add(new Category(R.drawable.snack,"snack"));
        categoriess.add(new Category(R.drawable.laptop,"Laptop"));
        categoriess.add(new Category(R.drawable.kurkur,"snack"));

        categoryRecycler.setHasFixedSize(true);
        CategoryListAdapter adapter1 =new CategoryListAdapter(getContext(), HomeMainFragment.this,null,categoriess);
        categoryRecycler.setAdapter(adapter1);

        ArrayList<RecentlyView> categories= new ArrayList<>();
        categories.add(new RecentlyView(R.drawable.presser));
        categories.add(new RecentlyView(R.drawable.juicer));
        categories.add(new RecentlyView(R.drawable.laptop));
        categories.add(new RecentlyView(R.drawable.kurkur));
        recentlyRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recentlyRecycler.setHasFixedSize(true);
        RecentlyBuyViewAdapter adapter =new RecentlyBuyViewAdapter(getContext(), HomeMainFragment.this,null,categories);
        recentlyRecycler.setAdapter(adapter);

        ArrayList<Season> seasons= new ArrayList<>();
        seasons.add(new Season(R.drawable.heater));
        seasons.add(new Season(R.drawable.coffee));
        seasons.add(new Season(R.drawable.heater));
        seasons.add(new Season(R.drawable.coffee));

        seasonRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        seasonRecycler.setHasFixedSize(true);
        SeasonListAdapter adapter2 =new SeasonListAdapter(getContext(), HomeMainFragment.this,null,seasons);
        seasonRecycler.setAdapter(adapter2);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
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
                Intent intent=new Intent(getContext(), SearchPageActivity.class);
                startActivity(intent);
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), RentActivity.class);
                startActivity(intent);
            }
        });


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
        return  view;
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