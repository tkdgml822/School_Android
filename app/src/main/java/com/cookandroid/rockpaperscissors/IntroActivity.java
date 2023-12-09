package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class IntroActivity extends AppCompatActivity {
    Button gameStartButton, ruleButton;
    TextView userName;
    Dialog ruleDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        gameStartButton = findViewById(R.id.gameStartButton);

        // 유저 이름 가져오기
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        userName = findViewById(R.id.intro_userName);
        userName.setText(name);

        // 게임 시작 버튼
        gameStartButton.setOnClickListener(view -> {
            Intent next = new Intent(getApplicationContext(), PlayerNameInputActivity.class);
            startActivity(next);
        });

        ruleDialog = new Dialog(this);
        ruleDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ruleDialog.setContentView(R.layout.rule);

        // 룰 설명 버튼
        ruleButton = findViewById(R.id.rule);
        ruleButton.setOnClickListener(view -> {
            showRuleDialog();
        });

    }

    public void showRuleDialog() {
        ruleDialog.show();
        Objects.requireNonNull(ruleDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 닫기 버튼
        Button noBtn = ruleDialog.findViewById(R.id.noButton);
        noBtn.setOnClickListener(view -> {
            ruleDialog.dismiss(); // 닫기
        });
    }
}