package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by JeonWon on 2018-03-10.
 */

//구글맵 API 키
//AIzaSyC-PiJhaeOQcu288CgZD8Dt-xo1idhWViQ
public class MainActivity extends AppCompatActivity {

    protected static DBHelper dbHelper;

    private Button button, buttonMap;
    //private String planName[] = {"China"};
    private ListView listView;
    private ListViewPlanAdapter listViewPlanAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Div is Creating DB
        dbHelper = new DBHelper(MainActivity.this, "GazaDB", null, 1);
        dbHelper.testDB();
        //Div is Creating DB

        //툴바
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);//뒤로가기 버튼
        //actionBar.setHomeAsUpIndicator(R.drawable.round_button);//뒤로가기버튼 모양 커스텀


        //툴바 설정

        button = (Button) findViewById(R.id.buttonMakePlan);
        buttonMap = (Button) findViewById(R.id.buttonMap);

        //My Plan List in Main
        listViewPlanAdapter = new ListViewPlanAdapter();
        listView = (ListView) findViewById(R.id.listViewPlan);

        listView.setAdapter(listViewPlanAdapter);

        //if DB is not exist
        if (MainActivity.dbHelper == null) {
            MainActivity.dbHelper = new DBHelper(this, "GazaDB", null, 1);
        }

        List<Plan> plan = MainActivity.dbHelper.getAllPlanData();

        for (int i = 0; i < plan.size(); i++) {
            listViewPlanAdapter.addPlanItem(plan.get(i).getTitle());
        }

        /*for (int i = 0; i < planName.length; i++) {
            listViewPlanAdapter.addPlanItem(planName[i]);
        }*/


        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MakePlan.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuProfile:
                //프로필 눌렀을때
                Toast.makeText(this, "메뉴 프로필", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuNotice:
                //공지사항 눌렀을때
                Toast.makeText(this, "메뉴 공지사항", Toast.LENGTH_SHORT).show();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
