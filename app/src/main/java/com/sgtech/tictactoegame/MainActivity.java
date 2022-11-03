package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.vungle.warren.AdConfig;
import com.vungle.warren.Banners;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

public class MainActivity extends AppCompatActivity {
    Button multy, single;
    RelativeLayout adview;
    private final String APP_ID = "633956b1957587af706e7e8c";
    private final String ZONE_ID = "B1-3352096";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Vungle.init(APP_ID, this, new InitCallback() {
            @Override
            public void onSuccess() {
                Banners.loadBanner(ZONE_ID, AdConfig.AdSize.BANNER, new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String placementId) {
                        if (Banners.canPlayAd(placementId, AdConfig.AdSize.BANNER)) {
                            adview.addView(Banners.getBanner(placementId, AdConfig.AdSize.BANNER,
                                    null));
                        }
                    }

                    @Override
                    public void onError(String placementId, VungleException exception) {

                    }
                });
            }

            @Override
            public void onError(VungleException exception) {

            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {

            }
        });


    }
}