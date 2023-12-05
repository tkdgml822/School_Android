package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button scissorsButton, rockButton, paperButton, winRateButton;
    TextView playerText, computerText, resultWindow, playerName, totalCount, winsCount;

    Dialog winRateDialog;

    int winCount = 1; // 이긴 횟수
    int totalGameCount = 0; // 총 게임 횟수
    int drawCount = 0;
    int lossCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerText = findViewById(R.id.playerText);
        playerName = findViewById(R.id.player_name);
        computerText = findViewById(R.id.computer);

        scissorsButton = findViewById(R.id.scissor);
        rockButton = findViewById(R.id.rock);
        paperButton = findViewById(R.id.paper);

        totalCount = findViewById(R.id.total_game_count);
        winsCount = findViewById(R.id.win_count);

        winRateButton = findViewById(R.id.win_rate);

        winRateDialog = new Dialog(this);
        winRateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        winRateDialog.setContentView(R.layout.win_rate);

        // Player 이름 바꾸기
        Intent playerIntent =  getIntent();
        String name = playerIntent.getStringExtra("name");
        playerName.setText(name);

        scissorsButton.setOnClickListener(view -> {
            totalGameCount++;
            playerText.setText("가위");
            result(computer(1));
        });

        rockButton.setOnClickListener(view -> {
            totalGameCount++;
            playerText.setText("바위");
            result(computer(2));
        });

        paperButton.setOnClickListener(view -> {
            totalGameCount++;
            playerText.setText("보");
            result(computer(3));
        });

        winRateButton.setOnClickListener(view -> {
            showWinrate();
        });
    }

    // 1 : 가위, 2 : 바위 : ,3 : 보
    private int computer(int human) {
        Random rd = new Random();
        int randomNumber = (rd.nextInt(3) + 1);
        if (randomNumber == 1) {
            computerText.setText("가위");
        }
        else if (randomNumber == 2) {
            computerText.setText("바위");
        }
        else {
            computerText.setText("보");
        }

        switch (human) {
            // 사용자가 가위를 냈을 때
            case 1:
                if (randomNumber == 2) {
                    winCount++;
                    return 0;
                }
                else if (randomNumber == 3) {
                    return 1;
                }
                else {
                    return 3;
                }

            // 사용자가 바위를 냈을 때
            case 2:
                if (randomNumber == 1) {
                    return 1;
                }
                else if (randomNumber == 3) {
                    winCount++;
                    return 0;
                }
                else {
                    return 3;
                }
            // 사용자가 보를 냈을 때
            case 3:
                if (randomNumber == 1) {
                    winCount++;
                    return 0;
                }
                else if (randomNumber == 2) {
                    return 1;
                }
                else {
                    return 3;
                }
        }
        return  0;
    }

    private void result(int num) {
        resultWindow = findViewById(R.id.result_window);

        if (num == 0) {
            lossCount++;
            totalCount.setText("총 게임 횟수 :" + totalGameCount);
            resultWindow.setText("졌습니다");
        }
        else if (num == 1) {
            totalCount.setText("총 게임 횟수 :" + totalGameCount);
            winsCount.setText("이긴 횟수 : " + winCount);
            resultWindow.setText("이겼습니다");
        }
        else {
            drawCount++;
            totalCount.setText("총 게임 횟수 :" + totalGameCount);
            resultWindow.setText("비겼습니다");
        }
    }

    public void showWinrate() {
        winRateDialog.show();
        Objects.requireNonNull(winRateDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 승률 계산
        int totalGames = winCount + lossCount;
        float winRate = (float) winCount / totalGames * 100;

        // TextView에 값 설정
        TextView winRateText = winRateDialog.findViewById(R.id.winRateText);
        TextView winCountText = winRateDialog.findViewById(R.id.winCountText);
        TextView lossCountText = winRateDialog.findViewById(R.id.lossCountText);

        winRateText.setText("현재 승률: " + Math.round(winRate) + "%");
        winCountText.setText("승리 횟수: " + winCount);
        lossCountText.setText("진 횟수: " + lossCount);

        // 닫기 버튼
        Button noBtn = winRateDialog.findViewById(R.id.noButton);
        noBtn.setOnClickListener(view -> {
            winRateDialog.dismiss(); // 닫기
        });
    }

}