package com.sgtech.tictactoegame;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;


import java.util.Objects;

public class SingleActivity extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9;
    TextView com, you, o, x;
    int[] tagFiled;
    TextView[] txt;
    boolean startGame;
    int i = 0;
    LinearLayout lin1, lin2;
    int[][] winningArray = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    String gameMode;
    boolean showAds;
    private final String APP_ID = "appbf3bfe59d4fa419199";
    private final String ZONE_ID = "vz6626d6faf21e4993b0";
    AdColonyAdOptions options;
    AdColonyInterstitial ad;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        AdColony.configure(this, APP_ID,ZONE_ID);
        chooseMode();
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

    @Override
    public void onBackPressed() {
        exitDialog();
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
        startGame = true;
        showAds = true;
        setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
        txt = new TextView[]{txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9};
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
        startGame = true;
        setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
        tagFiled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
    }

    public void restart(View view) {
        try {
            showAd();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanCode();
        }

    }

    public void exit(View view) {
        exitDialog();
    }


    public void onset(TextView textView) {
        String t = (String) textView.getTag();
        int tag = Integer.parseInt(t);
        if (tagFiled[tag - 1] == -1 && startGame) {
            textView.setText("X");
            tagFiled[tag - 1] = 1;
            startGame = false;
            i++;
            setDrawable(R.drawable.gray_box, R.drawable.icon_bg);

            try {
                if (i > 3) {
                    checkWinningStatus("X");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (i < 9 && !startGame) {
                    if (Objects.equals(gameMode, "H")) {
                        new Handler().postDelayed(this::hardPlay, 600);
                    } else if (gameMode.equals("S")) {
                        new Handler().postDelayed(this::softPlay, 600);
                    } else {
                        new Handler().postDelayed(this::mediumPlay, 600);
                    }
                }
            }
        }
    }

    public void hardPlay() {
        if (tagFiled[4] == -1) {
            setO(4);
        } else if (tagFiled[0] == -1) {
            setO(0);
        } else if (tagFiled[6] == -1) {
            setO(6);
        } else if (tagFiled[3] == -1) {
            setO(3);
        } else if (tagFiled[5] == -1) {
            setO(5);
        } else if (tagFiled[8] == -1) {
            setO(8);
        } else if (tagFiled[2] == -1) {
            setO(2);
        } else if (tagFiled[1] == -1) {
            setO(1);
        } else if (tagFiled[7] == -1) {
            setO(7);
        }
    }

    public void mediumPlay() {
        if (tagFiled[0] == -1) {
            setO(0);
        } else if (tagFiled[1] == -1) {
            setO(1);
        } else if (tagFiled[2] == -1) {
            setO(2);
        } else if (tagFiled[3] == -1) {
            setO(3);
        } else if (tagFiled[4] == -1) {
            setO(4);
        } else if (tagFiled[5] == -1) {
            setO(5);
        } else if (tagFiled[6] == -1) {
            setO(6);
        } else if (tagFiled[7] == -1) {
            setO(7);
        } else if (tagFiled[8] == -1) {
            setO(8);
        }
    }

    public void softPlay() {
        if (tagFiled[0] == -1) {
            setO(0);
        } else if (tagFiled[1] == -1) {
            setO(1);
        } else if (tagFiled[2] == -1) {
            setO(2);
        } else if (tagFiled[3] == -1) {
            setO(3);
        } else if (tagFiled[4] == -1) {
            setO(4);
        } else if (tagFiled[5] == -1) {
            setO(5);
        } else if (tagFiled[6] == -1) {
            setO(6);
        } else if (tagFiled[7] == -1) {
            setO(7);
        } else if (tagFiled[8] == -1) {
            setO(8);
        }

    }


    public void setO(int t) {
        TextView text = txt[t];
        text.setText("O");
        tagFiled[t] = 0;
        startGame = true;
        i++;
        setDrawable(R.drawable.icon_bg, R.drawable.gray_box);
        if (i > 5) {
            checkWinningStatus("O");
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
                    startGame = true;
                    return;
                }
            }

        }
        int[] tagArray = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

        if (i == 9 && tagFiled != tagArray) {
            startGame = true;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setPositiveButton("Play " +
                "Again", (dialog, which) -> {
            try {
                showAd();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dialog.dismiss();
                cleanCode();
            }
        }).setCancelable(false);
        if (t.equals("X")) {
            builder.setTitle("Congratulations").setMessage("You are win the match").create().show();
        } else {
            builder.setTitle("Oops! You Lost ").setMessage("You Lost the match").create().show();

        }

    }

    public void chooseMode() {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog);
        Button easy = dialog.findViewById(R.id.easy);
        Button hard = dialog.findViewById(R.id.hard);
        easy.setOnClickListener(v -> {
            gameMode = "S";
            dialog.dismiss();
        });
        hard.setOnClickListener(v -> {
            gameMode = "H";
            dialog.dismiss();
        });
        dialog.create();
        dialog.show();
    }

    public void setDrawable(int d1, int d2) {
        lin1.setBackgroundDrawable(getResources().getDrawable(d1));
        lin2.setBackgroundDrawable(getResources().getDrawable(d2));
    }

    public void loadAd() {
        AdColony.setRewardListener(adColonyReward -> {

        });
        AdColonyInterstitialListener listener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial ad) {
                SingleActivity.this.ad = ad;
            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {

            }

            @Override
            public void onOpened(AdColonyInterstitial ad) {

            }

            @Override
            public void onExpiring(AdColonyInterstitial ad) {

            }
        };
        AdColony.requestInterstitial(ZONE_ID, listener, options);

    }

    public void showAd() {
        if (ad != null) {
            showAds = true;
            ad.show();

        }
    }
}