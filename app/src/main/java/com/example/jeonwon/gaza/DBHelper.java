package com.example.jeonwon.gaza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
        //CREATE TABLE TEST_TABLE ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, PEOPLE INTEGER, BUDGET TEXT,  START DATE, END DATE);
        sb.append(" CREATE TABLE PLAN_TABLE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" TITLE TEXT, ");
        sb.append(" PEOPLE INTEGER, ");
        sb.append(" BUDGET TEXT, ");
        sb.append(" START DATE, ");
        sb.append(" END DATE ) ");

        //_ID |   TITLE   |   PEOPLE    |   BUDGET  |   START   |   END
        //----------------------------------------------------------------//DB 테이블 모양


        // SQLite Database로 쿼리 실행
        sqLiteDatabase.execSQL(sb.toString());

        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();

    }

    public void addPlan(Plan plan) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO PLAN_TABLE ( ");
        sb.append(" TITLE, PEOPLE, BUDGET ) ");
        sb.append(" VALUES ( ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        plan.getTitle(),
                        plan.getPeople(),
                        plan.getBudget()});

        Toast.makeText(context, "Insert 완료", Toast.LENGTH_SHORT).show();
    }

    public List getAllPlanData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, TITLE, PEOPLE, BUDGET FROM PLAN_TABLE ");

        // 읽기 전용 DB 객체를 만든다.
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.rawQuery(sb.toString(), null);
        List plans = new ArrayList();
        Plan plan = null; // moveToNext 다음에 데이터가 있으면 true 없으면 false

        while (cursor.moveToNext()) {
            plan = new Plan();
            plan.setTitle((cursor.getString(0)));
            plan.setPeople(cursor.getInt(1));
            plan.setBudget(cursor.getString(2));
            plans.add(plan);
        }
        return plans;
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }


}
