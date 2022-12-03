package com.sgtech.tictactoegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;


public class MultiActivity extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9;
    TextView com, you, o, x;
    int[] tagFiled;
    TextView[] txt;
    int i = 0;
    LinearLayout lin1, lin2;
    int[][] winningArray = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    int player_1 = 1;
    int player_2 = 0;
    int actionPlayer = player_1;
    RewardedInterstitialAd ad;
    String adId = "ca-app-pub-2602459603500864/1090783526";
    boolean showAds;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        findId();
        txt1.setOnClickListener(v -> onset(txt1));
        txt9.setOnClickListener(v -> onset(txt9));
        txt8.setOnClickListener(v -> onset(txt8));
        txt7.setOnClickListener(v -> onset(txt7));
        txt6.setOnClickListener(v -> onset(txt6));
        txt5.setOnClickListener(v -> onset(txt5));
        txt4.setOnClickListener(v -> onset(txt4));
        txt3.setOnClickListener(v -> onset(txt3));
        txt2.setOnClickListener(v -> onset(txt2));
    }

    private void findId() {
        tagFiled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt7);
        txt8 = findViewById(R.id.txt8);
        txt9 = findViewById(R.id.txt9);
        you = findViewById(R.id.you);
        o = findViewById(R.id.o);
        x = findViewById(R.id.x);
        com = findViewById(R.id.com);
        lin1 = findViewById(R.id.lin1);
        lin2 = findViewById(R.id.lin2);
        setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
        txt = new TextView[]{txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9};
        showAds = true;
    }

    public void cleanCode() {
        showAd();
        if (showAds) {
            showAds = false;
            new Handler().postDelayed(this::loadAd, 4000);
        }
        actionPlayer = player_1;
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt8.setText("");
        txt9.setText("");
        i = 0;
        setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
        tagFiled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
    }

    public void restart(View view) {
        cleanCode();
    }

    public void exitDialog() {
        new AlertDialog.Builder(this).setCancelable(false).setTitle("Alert").setMessage("Are your" +
                " sure you can exit").setNegativeButton("No", (dialog, which) -> dialog.dismiss()).setPositiveButton("Exit", (dialog, which) -> {
            try {
                showAd();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dialog.dismiss();
                finish();
            }
        }).create().show();
    }

    public void exit(View view) {
        exitDialog();
    }

    @Override
    public void onBackPressed() {
        exitDialog();
    }

    public void onset(TextView textView) {
        String t = (String) textView.getTag();
        int tag = Integer.parseInt(t);
        if (tagFiled[tag - 1] == -1) {
            if (actionPlayer == player_1) {
                textView.setText("X");
                tagFiled[tag - 1] = 1;
                i++;
                actionPlayer = player_2;
                setDrawable(R.drawable.gray_box, R.drawable.icon_bg);
                if (i > 3) {
                    checkWinningStatus("X");
                }
            } else {
                textView.setText("O");
                tagFiled[tag - 1] = 0;
                i++;
                actionPlayer = player_1;
                setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
                if (i > 4) {
                    checkWinningStatus("O");
                }
            }
        }
    }

    public void checkWinningStatus(String t) {
        for (int[] ints : winningArray) {
            int val0 = ints[0];
            int val1 = ints[1];
            int val2 = ints[2];
            if (tagFiled[val0] == tagFiled[val1] && tagFiled[val2] == tagFiled[val1]) {
                if (tagFiled[val0] != -1) {
                    dialog(t);
                    return;
                }
            }
        }
        int[] tagArray = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        if (i == 9 && tagFiled != tagArray) {
            showDialogs("Match Draw", "Play Again");
            img.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.replay));
        }
    }


    private void dialog(String t) {
        if (t.equals("X")) {
            showDialogs("Congratulations", "Player 1 is win the match");
        } else {
            showDialogs("Congratulations", "Player 2 is win the match");
        }
    }

    public void setDrawable(int d1, int d2) {
        lin1.setBackground(AppCompatResources.getDrawable(this,d1));
        lin2.setBackground(AppCompatResources.getDrawable(this,d2));
    }

    public void loadAd() {
        RewardedInterstitialAd.load(this, adId, new AdRequest.Builder().build(),
                new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        ad = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                        super.onAdLoaded(rewardedInterstitialAd);
                        ad = rewardedInterstitialAd;
                        ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                super.onAdClicked();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }
                        });
                    }
                });
    }

    public void showDialogs(String title, String win_text) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.wining_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btn = dialog.findViewById(R.id.play_again);
        img = dialog.findViewById(R.id.img);
        btn.setOnClickListener(v -> {
            cleanCode();
            dialog.dismiss();
        });
        img.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.celebration));
        TextView text_title = dialog.findViewById(R.id.text_title);
        TextView win_title = dialog.findViewById(R.id.win_txt);
        win_title.setText(win_text);
        text_title.setText(title);
        dialog.setCanceledOnTouchOutside(false);
        dialog.create();
        dialog.show();
    }

    public void showAd() {
        if (ad != null) {
            ad.show(this, rewardItem -> {
                showAds = true;
            });
        }
    }
}