package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    Button gameStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        gameStartButton = findViewById(R.id.gameStartButton);

        gameStartButton.setOnClickListener(view -> {
            Intent next = new Intent(getApplicationContext(), PlayerNameInputActivity.class);
            startActivity(next);
        });

    }
}