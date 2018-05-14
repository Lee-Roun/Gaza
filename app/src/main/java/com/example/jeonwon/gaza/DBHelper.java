package com.example.jeonwon.gaza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JeonWon on 3/31/2018.
 */
/////
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
        StringBuffer createSQL1 = new StringBuffer();
        //CREATE TABLE TEST_TABLE ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, PEOPLE INTEGER, BUDGET TEXT,  START DATE, END DATE);
        createSQL1.append(" CREATE TABLE P_TABLE ( ");
        createSQL1.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        createSQL1.append(" TITLE TEXT, ");
        createSQL1.append(" PEOPLE TEXT, ");
        createSQL1.append(" BUDGET TEXT, ");
        createSQL1.append(" START TEXT, ");
        createSQL1.append(" END TEXT ) ");
        //ID |   TITLE   |   PEOPLE    |   BUDGET  |   START   |   END
        //----------------------------------------------------------------//DB 테이블 모양

        StringBuffer createSQL2 = new StringBuffer();
        //CREATE TABLE TEST_TABLE ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, PEOPLE INTEGER, BUDGET TEXT,  START DATE, END DATE);
        createSQL2.append(" CREATE TABLE L_TABLE ( ");
        createSQL2.append(" ID INTEGER AUTOINCREMENT, ");
        createSQL2.append(" NDAY INTEGER, ");
        createSQL2.append(" NLIST INTEGER, ");
        createSQL2.append(" LOCATION TEXT, ");
        createSQL2.append(" TIME DATE, ");
        createSQL2.append(" SPENTMONEY TEXT, ");
        createSQL2.append(" MEMO TEXT, ");
        createSQL2.append(" LISTBUDGET TEXT, PRIMARY KEY(NDAY, NLIST), FOREIGN KEY(ID) REFERENCES PLAN_TABLE(ID) ON DELETE CASCADE) ");
        //ID* |   NDAY*   |   NLIST*    |   LOCATION  |   TIME  |   LISTBUDGET  |   SPENTMONEY  |    MEMO   |
        //---------------------------------------------------//DB 테이블 모양

        // SQLite Database로 쿼리 실행
        sqLiteDatabase.execSQL(createSQL1.toString());
        sqLiteDatabase.execSQL(createSQL2.toString());

        Toast.makeText(context, "Table 2개 생성완료", Toast.LENGTH_SHORT).show();
        Log.i("Table 생성","Table 생성 완료함");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
        Log.i("Upgrade","Upgrade 완료");

    }


    public void deletePlan(Plan plan){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("PLAN_TABLE", "TITLE ="+ plan.getTitle(), null);
    }

    public void addPlan(Plan plan) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO P_TABLE ( ");
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
        sb.append("SELECT ID, TITLE, PEOPLE, BUDGET, START, END FROM P_TABLE");

        // 읽기 전용 DB 객체를 만든다.
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);
        List<Plan> plans = new ArrayList();
        Plan plan = null; // moveToNext 다음에 데이터가 있으면 true 없으면 false
        Log.i("DB :","데이터 불러오기");

        while (cursor.moveToNext()) {
            plan = new Plan();
            plan.setTitle((cursor.getString(1)));
            plan.setPeople(cursor.getString(2));
            plan.setBudget(cursor.getString(3));
            plan.setStartDate(cursor.getString(4));
            plan.setEndDate(cursor.getString(5));
            plans.add(plan);
        }

        return plans;
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }


}
