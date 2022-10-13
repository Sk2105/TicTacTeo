package com.sgtech.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button multy, single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multy = findViewById(R.id.multy);
        single = findViewById(R.id.single);
        multy.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MultiActivity.class)));
        single.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SingleActivity.class)));
    }
}