package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class HomeActivity extends AppCompatActivity {
    Uri u = Uri.parse("https://play.google.com/store/apps/details?id=com.sgtech.tictactoegame");
    AdView adview;
    LinearLayout singlePlay, multiPlay, rateUs, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findId();
        MobileAds.initialize(this);
        showAd();
        singlePlay.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, SingleActivity.class)));
        multiPlay.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MultiActivity.class)));
        rateUs.setOnClickListener(v -> setRateUs());
        share.setOnClickListener(v -> startShare());
    }

    private void startShare() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Download this fantastic app \n Play Tic tac teo game ." +
                " You Can Play with your friends now! SINGLE PLAYER and MULTI PLAYER options are " +
                "OPEN! \n share with your friends. \n\n " + u.toString());
        startActivity(Intent.createChooser(intent, "Share Now"));

    }

    private void setRateUs() {
        Intent intent = new Intent(Intent.ACTION_VIEW, u);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void findId() {
        singlePlay = findViewById(R.id.single_player);
        multiPlay = findViewById(R.id.multi_player);
        rateUs = findViewById(R.id.rate_us);
        share = findViewById(R.id.share);
        adview = findViewById(R.id.adView);
    }

    public void showAd() {
        adview.loadAd(new AdRequest.Builder().build());
    }
}