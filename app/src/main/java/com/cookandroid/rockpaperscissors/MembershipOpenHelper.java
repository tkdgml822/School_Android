package com.cookandroid.rockpaperscissors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MembershipOpenHelper extends SQLiteOpenHelper {

    Context context;

    public MembershipOpenHelper(Context context) {
        super(context, "member2205052.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "create table membership2205052 (" +
                    "name varchar(40) not null primary key," +
                    "pwd varchar(72) not null" +
                    ");";
            db.execSQL(sql);
            Toast.makeText(context, "[membership2205052] 테이블 생성", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

