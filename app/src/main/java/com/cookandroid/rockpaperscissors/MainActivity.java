package com.cookandroid.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button scissorsButton, rockButton, paperButton;
    EditText playerText, result, computerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerText = findViewById(R.id.playerText);
        computerText = findViewById(R.id.computer);

        scissorsButton = findViewById(R.id.scissor);
        rockButton = findViewById(R.id.rock);
        paperButton = findViewById(R.id.paper);

        result = findViewById(R.id.result);

        scissorsButton.setOnClickListener(view -> {
            playerText.setText("가위");
            result(computer(1));

        });

        rockButton.setOnClickListener(view -> {
            playerText.setText("바위");
            result(computer(2));
        });

        paperButton.setOnClickListener(view -> {
            playerText.setText("보");
            result(computer(3));
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
                    return 0;
                }
                else {
                    return 3;
                }
            // 사용자가 보를 냈을 때
            case 3:
                if (randomNumber == 1) {
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
        if (num == 0) {
            result.setText("졌습니다.");
        }
        else if (num == 1) {
            result.setText("이겼습니다.");
        }
        else {
            result.setText("비겼습니다.");
        }
    }

}

