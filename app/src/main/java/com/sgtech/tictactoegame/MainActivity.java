package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
        TextView tv = findViewById(R.id.title);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
          new LinearGradient(0, 0, tv.getPaint().measureText(tv.getText().toString()), tv.getTextSize(),
                    new int[]{getColor(R.color.red), getColor(R.color.blue)},
                    new float[]{0, 1}, Shader.TileMode.CLAMP);
        }
    }

    public void showAd() {
        adview = findViewById(R.id.adView);
        adview.loadAd(new AdRequest.Builder().build());
    }
}