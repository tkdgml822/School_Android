package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PlayerNameInput extends AppCompatActivity {
    Button nextButton;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_input);
        nextButton = findViewById(R.id.nextButton);
        userName = findViewById(R.id.userName);

        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        nextButton.setOnClickListener(v -> {
            mainIntent.putExtra("name", userName.getText().toString());
            startActivity(mainIntent);
        });
    }
}