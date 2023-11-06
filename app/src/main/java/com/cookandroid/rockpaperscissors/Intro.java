package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity {
    Button gameStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        gameStartButton = findViewById(R.id.gameStartButton);

        gameStartButton.setOnClickListener(view -> {
            Intent next = new Intent(getApplicationContext(), PlayerNameInput.class);
            startActivity(next);
        });

    }
}