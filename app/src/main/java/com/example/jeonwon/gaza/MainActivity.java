package com.example.jeonwon.gaza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText ID, PW;
    private TextView Find;
    private Button btnLogin, btnJoin, btnFind;
    private CheckBox chkBoxLogin;

    private String sID, sPW;
    private boolean saveLoginData;
    private SharedPreferences appData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        //앱 설정값 저장하는 변수
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        ID = (EditText) findViewById(R.id.edtID);
        PW = (EditText) findViewById(R.id.edtPW);
        chkBoxLogin = (CheckBox)findViewById(R.id.checkBoxLogin);

        btnLogin = (Button) findViewById(R.id.login);
        btnJoin = (Button) findViewById(R.id.signUp);
        Find = (TextView) findViewById(R.id.findIDorPW);


        if(saveLoginData){
            ID.setText(sID);
            PW.setText(sPW);
            chkBoxLogin.setChecked(saveLoginData);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.login:
                        Login(view);
                        break;
                    case R.id.findIDorPW:

                        break;
                    case R.id.signUp:
                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        };

        btnJoin.setOnClickListener(listener);
        //btnFind.setOnClickListener(listener);
        btnLogin.setOnClickListener(listener);


    }

    private void load(){
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA",false);
        sID = appData.getString("ID","");
        sPW = appData.getString("PW","");

    }

    private void save(){
        SharedPreferences.Editor editor = appData.edit();
        editor.putBoolean("SAVE_LOGIN_DATA", chkBoxLogin.isChecked());
        editor.putBoolean("SAVE_STAT", true);
        editor.putString("ID", ID.getText().toString());
        editor.putString("PW", PW.getText().toString());
        editor.apply();
    }

    private void Login(View v) {

        try {
            sID = ID.getText().toString();
            sPW = PW.getText().toString();
        } catch (NullPointerException e) {
            Log.d("Error", e.getMessage());
        }

        LoginDB loinDB = new LoginDB();
        loinDB.execute();
    }

    private class LoginDB extends AsyncTask<Void, Integer, Void> {

        String data = "";
        ConnectSERVER conn = new ConnectSERVER();
        boolean login = false;

        @Override
        protected Void doInBackground(Void... voids) {

            if(!login) {
                conn.setUserInfo(sID, sPW);
                data = conn.ConnectSERVER();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            sID = appData.getString("ID", ID.getText().toString());
            sPW = appData.getString("PW", PW.getText().toString());
            conn.setUserInfo(sID, sPW);
            data = conn.ConnectSERVER();

            if (data.equals("0")) {
                Log.e("RESULT", "성공적으로 처리되었습니다!");
                Toast.makeText(MainActivity.this, sID + "님 환영합니다!", Toast.LENGTH_LONG).show();
                login = true;
                save();
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("ID", ID.getText().toString());
                startActivity(intent);
            } else if (data.equals("1")) {
                Log.e("RESULT", "비밀번호가 일치하지 않습니다.");
                Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
            } else {
                Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                Toast.makeText(MainActivity.this, data + "등록중 에러", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            if(!login) {
                if (data.equals("0")) {
                    Log.e("RESULT", "성공적으로 처리되었습니다!");
                    Toast.makeText(MainActivity.this, sID + "님 환영합니다!", Toast.LENGTH_LONG).show();
                    save();
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    startActivity(intent);
                } else if (data.equals("1")) {
                    Log.e("RESULT", "비밀번호가 일치하지 않습니다.");
                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                    Toast.makeText(MainActivity.this, data + "등록중 에러", Toast.LENGTH_LONG).show();

                }
            }

        }

    }

}
