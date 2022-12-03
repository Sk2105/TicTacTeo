package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    Button multy, single;
    AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this);
        showAd();
        multy = findViewById(R.id.multy);
        single = findViewById(R.id.single);
        multy.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MultiActivity.class)));
        single.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SingleActivity.class)));

    }

    public void showAd() {
        adview = findViewById(R.id.adView);
        adview.loadAd(new AdRequest.Builder().build());
    }
}