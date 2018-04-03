package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


/**
 * Created by JeonWon on 2018-03-10.
 */

//구글맵 API 키
//AIzaSyC-PiJhaeOQcu288CgZD8Dt-xo1idhWViQ
public class MainActivity extends AppCompatActivity {

    protected static DBHelper dbHelper;

    private Button button, buttonMap;
    private String planName[] = {"China"};
    private ListView listView;
    private ListViewPlanAdapter listViewPlanAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Main);

        //Div is Creating DB
        dbHelper = new DBHelper(MainActivity.this, "GazaDB", null, 1);
        dbHelper.testDB();
        //Div is Creating DB

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

        List plan = MainActivity.dbHelper.getAllPlanData();


        for (int i = 0; i < planName.length; i++) {
            listViewPlanAdapter.addPlanItem(planName[i]);
        }


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

}
