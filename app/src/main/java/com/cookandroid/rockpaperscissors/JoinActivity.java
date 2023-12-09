package com.cookandroid.rockpaperscissors;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.rockpaperscissors.LoginActivity;
import com.cookandroid.rockpaperscissors.MainActivity;
import com.cookandroid.rockpaperscissors.MembershipOpenHelper;


public class JoinActivity extends AppCompatActivity {

    MembershipOpenHelper openHelper;
    SQLiteDatabase db;
    EditText userNameEt, pwdEt;
    Button joinBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();
        userNameEt = findViewById(R.id.userNameEt2);
        pwdEt = (EditText) findViewById(R.id.pwdEt2);
        joinBtn = (Button) findViewById(R.id.joinBtn2);
        joinBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.joinBtn2) {
                String name = userNameEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                String sql = "select * from membership where name = '"+name+"'";
                Cursor cursor = db.rawQuery(sql, null);
                if(cursor.getCount() == 1) {
                    // 해당 이메일과 아이디가 있으면 1개의 row를 가져옴
                    Toast.makeText(JoinActivity.this, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JoinActivity.this, MainActivity.class));
                    finish();
                } else {
                    // 없다면 아무 값도 가져오지 않으므로 count 가 0
                    String sql2 ="insert into membership(name, pwd) values('"+name+"','"+pwd+"')";
                    db.execSQL(sql2);
                    Toast.makeText(JoinActivity.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JoinActivity.this, LoginActivity.class));
                }
                cursor.close();
            }
        }
    };
}
