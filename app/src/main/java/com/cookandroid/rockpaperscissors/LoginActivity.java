package com.cookandroid.rockpaperscissors;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    MembershipOpenHelper openHelper;
    EditText userNameEt, pwdEt;
    SQLiteDatabase db;
    Button loginBtn, joinBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();
        userNameEt = findViewById(R.id.userNameEt);
        pwdEt = (EditText) findViewById(R.id.pwdEt);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        joinBtn = (Button) findViewById(R.id.joinBtn);
        loginBtn.setOnClickListener(listener);
        joinBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.joinBtn) {
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                finish();
            }
            else if (v.getId() == R.id.loginBtn) {
                String name = userNameEt.getText().toString();
                String pwd = pwdEt.getText().toString();

                String sql = "select * from membership where name = '"+name+"' and pwd = '"+pwd+"'";
//                String sql = "DROP TABLE membership;";
                Cursor cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    String no = cursor.getString(0);
                    String rest_id = cursor.getString(1);
                    Log.d("select ", "no : " + no + "\nrest_id : " + rest_id);
                }
                if(cursor.getCount() == 1) {
                    // 해당 이메일과 아이디가 있으면 1개의 row를 가져옴
                    Toast.makeText(LoginActivity.this, name+ "님 환영합니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("pwd", pwd);
                    startActivity(intent);
                } else {
                    // 없다면 아무 값도 가져오지 않으므로 count 가 0
                    Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        }
    };
}

