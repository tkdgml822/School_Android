package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button scissorsButton, rockButton, paperButton;
    EditText playerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerText = findViewById(R.id.playerText);
        scissorsButton = findViewById(R.id.scissor);
        rockButton = findViewById(R.id.rock);
        paperButton = findViewById(R.id.paper);

        scissorsButton.setOnClickListener(view -> {
            playerText.setText("가위");
        });

        rockButton.setOnClickListener(view -> {
            playerText.setText("바위");
        });

        paperButton.setOnClickListener(view -> {
            playerText.setText("보");
        });
    }
    private int random() {
        Random rd = new Random();

        return (rd.nextInt(3) + 1);
    }

}

