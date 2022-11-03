package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;

public class MainActivity extends AppCompatActivity {
    Button multy, single;
    RelativeLayout adview;
    private final String APP_ID = "appbf3bfe59d4fa419199";
    private final String ZONE_ID = "vzae6dcaf98b094ceea6";
    AdColonyAdOptions options;
    AdColonyAdView ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdColony.configure(this, APP_ID, ZONE_ID);
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
        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView adColonyAdView) {
                if (adColonyAdView != null) {
                    ad = adColonyAdView;
                    adview.addView(ad);
                }
            }
        };
        AdColony.requestAdView(ZONE_ID, listener, AdColonyAdSize.BANNER, options);

    }
}