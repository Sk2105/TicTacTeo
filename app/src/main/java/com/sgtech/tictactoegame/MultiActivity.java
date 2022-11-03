package com.sgtech.tictactoegame;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;


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

    boolean showAds;
    private final String APP_ID = "633956b1957587af706e7e8c";
    private final String ZONE_ID = "R1-4404321";

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
        if (showAds) {
            showAds = false;
            new Handler().postDelayed(this::loadAd, 4000);
        }

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
            new AlertDialog.Builder(this).setCancelable(false).setPositiveButton("Play Again",
                    (dialog, which) -> {
                        try {
                            showAd();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            dialog.dismiss();
                            cleanCode();
                        }
                    }).setTitle("Match Draw").setMessage("Play Again").create().show();
        }
    }


    private void dialog(String t) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).setCancelable(false).setPositiveButton("Play " +
                        "Again", (dialog, which) -> {
                    try {
                        showAd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        dialog.dismiss();
                        cleanCode();
                    }
                }).setTitle("Congratulations");
        if (t.equals("X")) {
            builder.setMessage("Player 1 is win the match").create().show();
        } else {
            builder.setMessage("Player 2 is win the match").create().show();
        }

    }

    public void setDrawable(int d1, int d2) {
        lin1.setBackgroundDrawable(getResources().getDrawable(d1));
        lin2.setBackgroundDrawable(getResources().getDrawable(d2));
    }

    public void loadAd() {
        Vungle.init(APP_ID, this, new InitCallback() {
            @Override
            public void onSuccess() {
                Vungle.loadAd(ZONE_ID, new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String placementId) {

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

    public void showAd() {
        if (Vungle.canPlayAd(ZONE_ID)) {
            showAds = true;
            Vungle.playAd(ZONE_ID, null, new PlayAdCallback() {
                @Override
                public void creativeId(String creativeId) {

                }

                @Override
                public void onAdStart(String placementId) {

                }

                @Override
                public void onAdEnd(String placementId, boolean completed, boolean isCTAClicked) {

                }

                @Override
                public void onAdEnd(String placementId) {

                }

                @Override
                public void onAdClick(String placementId) {

                }

                @Override
                public void onAdRewarded(String placementId) {

                }

                @Override
                public void onAdLeftApplication(String placementId) {

                }

                @Override
                public void onError(String placementId, VungleException exception) {

                }

                @Override
                public void onAdViewed(String placementId) {

                }
            });
        }

    }
}