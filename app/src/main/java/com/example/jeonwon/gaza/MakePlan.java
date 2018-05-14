package com.example.jeonwon.gaza;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MakePlan extends AppCompatActivity{

    private static final int REQUEST_CODE = 188;

    int sYear,sMonth,sDay; //여행 출발일 날짜정보 저장할 변수
    int eYear,eMonth,eDay; //여행 마지막일 날짜정보 저장할 변수
    int mYear,mMonth,mDay; //계획세우는 날(현재시간) 날짜정보 저장할 변수
    TextView startDay,endDay; //첫 날 / 마지막 날 표시할 텍스트뷰

    EditText editPlanName,editBudget; // 계획 명 , 예산 입력하는 EditText
    NumberPicker editPeople; // 인원 수
    Button cancle,next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeplan);

        NumberPicker edtPeople=(NumberPicker)findViewById(R.id.editPeople);

        edtPeople.setMinValue(1); // 최소 인원수 설정
        edtPeople.setMaxValue(100); //최대 인원수 설정
        edtPeople.setWrapSelectorWheel(false); //넘버 피커가 최대 설정값까지 돌아가게하는 메소드



        startDay=(TextView) findViewById(R.id.startDay);
        endDay=(TextView) findViewById(R.id.endDay);

        //mYear mMonth mDay 변수에 각각 현재 날짜의 정보를 저장
        Calendar cal=new GregorianCalendar();
        mYear =cal.get(Calendar.YEAR);
        mMonth =cal.get(Calendar.MONTH);
        mDay =cal.get(Calendar.DAY_OF_MONTH);


        //출발하는 날 선택
        final DatePickerDialog.OnDateSetListener sDateSetListener =
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        sYear = year;
                        sMonth = monthOfYear+1;
                        sDay = dayOfMonth;
                        startDay.setText(sYear+"."+sMonth+"."+sDay);
                        startDay.setTextColor(Color.BLACK);
                    }
                };
        //마지막 날 선택
        final DatePickerDialog.OnDateSetListener eDateSetListener =
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        eYear = year;
                        eMonth = monthOfYear+1;
                        eDay = dayOfMonth;
                        endDay.setText(eYear+"."+eMonth+"."+eDay);
                        endDay.setTextColor(Color.BLACK);
                    }
                };

        startDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MakePlan.this,sDateSetListener,mYear,mMonth,mDay).show();

            }
        });
        endDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MakePlan.this,eDateSetListener,mYear,mMonth,mDay).show();
            }
        });

        editPlanName=(EditText)findViewById(R.id.editPlanName);
        editBudget=(EditText)findViewById(R.id.editBudget);
        editPeople=(NumberPicker)findViewById(R.id.editPeople);
        next=(Button)findViewById(R.id.next);
        cancle=(Button)findViewById(R.id.cancel);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long tripPeriod;
                String start = startDay.getText().toString();
                String end = endDay.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                try {
                    Date startDate = formatter.parse(start);
                    Date endDate = formatter.parse(end);
                    tripPeriod = endDate.getTime() - startDate.getTime();
                    tripPeriod /= (24 * 60 * 60 * 1000);
                    tripPeriod++;
                    if(tripPeriod<=0||
                            editPlanName.getText().toString().equals("")||
                            editBudget.getText().toString().equals("")){
                        Toast.makeText(MakePlan.this, "여행 정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), ScheduleList.class);
                        intent.putExtra("TripPeriod", tripPeriod);
                        intent.putExtra("PlanName", editPlanName.getText().toString());
                        intent.putExtra("Budget", editBudget.getText().toString());
                        intent.putExtra("People", editPeople.getValue());
                        insertDB(editPlanName.getText().toString(), Integer.parseInt(editBudget.getText().toString()), editPeople.getValue());
                        Log.i("gaza","ㅎㅇ");
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }

    private void insertDB(String title, int budget, int people){
        Plan plan = new Plan(title, budget, people);

        MainActivity.dbHelper.addPlan(plan);
    }

}