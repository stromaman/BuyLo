package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.ecom.buylo.R;

public class SplashActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView shopNearbyTextView;
    private TextView saveTimeTextView;
    private TextView saveMoneyTextView;

    CardView Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        videoView = findViewById(R.id.VideoPlay);
        shopNearbyTextView = findViewById(R.id.textViewShopNearby);
        saveTimeTextView = findViewById(R.id.textViewSaveTime);
        saveMoneyTextView = findViewById(R.id.textViewSaveMoney);


        Next=findViewById(R.id.Next);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this, OtpSenderActivity.class);
                startActivity(intent);
            }
        });

        playVideo(R.raw.video5, shopNearbyTextView, R.raw.video4, saveTimeTextView, R.raw.video4, saveMoneyTextView);

        // Additional: If you want to start the sequence when the "Get Started" button is clicked

    }

    private void playVideo(final int videoResource1, final TextView textView1,
                           final int videoResource2, final TextView textView2,
                           final int videoResource3, final TextView textView3) {
        // Set video source
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResource1;
        videoView.setVideoURI(Uri.parse(videoPath));

        // Set onPreparedListener to start the video and highlight the corresponding TextView
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                highlightTextView(textView1);

                // Set onCompletionListener to play the next video and highlight the next TextView
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // Play the second video
                        playVideo(videoResource2, textView2, videoResource3, textView3, videoResource1, textView1);
                    }
                });
            }
        });
    }

    private void highlightTextView(TextView textView) {
        // Reset previous highlights
        shopNearbyTextView.setTextColor(getResources().getColor(R.color.grey3));
        saveTimeTextView.setTextColor(getResources().getColor(R.color.grey3));
        saveMoneyTextView.setTextColor(getResources().getColor(R.color.grey3));

        // Set the current TextView highlight
        textView.setTextColor(getResources().getColor(R.color.white));
    }
}