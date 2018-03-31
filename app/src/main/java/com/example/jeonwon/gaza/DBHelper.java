package com.example.jeonwon.gaza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by JeonWon on 3/31/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    //생성자
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE TEST_TABLE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" AGE INTEGER, ");
        sb.append(" PHONE TEXT ) ");

        // SQLite Database로 쿼리 실행
        sqLiteDatabase.execSQL(sb.toString());
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();

    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }


}
