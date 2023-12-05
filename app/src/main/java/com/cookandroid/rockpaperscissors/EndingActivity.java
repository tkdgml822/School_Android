package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndingActivity extends AppCompatActivity {
    Button previousButton, appCloseButton;
    TextView nameText, winRateText, winCountText, loseCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        previousButton = findViewById(R.id.previous);

        nameText = findViewById(R.id.ending_name); // 이름
        winRateText = findViewById(R.id.ending_win_rate); // 승률
        winCountText = findViewById(R.id.ending_win_count); // 승
        loseCountText = findViewById(R.id.ending_lose_count); // 패
        appCloseButton = findViewById(R.id.app_close); // 앱 종료


        // 넘겨진 데이터 받아오기
        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("userName");
            float winRate = intent.getFloatExtra("winRate", 0);
            int winCount = intent.getIntExtra("winCount", 0);
            int lossCount = intent.getIntExtra("lossCount", 0);

            // TextView에 데이터 설정
            nameText.setText("이름: " + userName);
            winRateText.setText("당신의 승률: " + String.format("%.2f", winRate) + "%");
            winCountText.setText("승: " + winCount);
            loseCountText.setText("패: " + lossCount);
        }

        previousButton.setOnClickListener(view -> {
            finish();
        });

        appCloseButton.setOnClickListener(view -> {
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
        });

    }
}