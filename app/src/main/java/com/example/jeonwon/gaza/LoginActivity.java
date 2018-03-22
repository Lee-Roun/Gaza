package com.example.jeonwon.gaza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by JeonWon on 2018-03-22.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText ID, PW;
    private TextView Find;
    private Button btnLogin, btnJoin;
    private CheckBox chkBoxLogin;
    private boolean saveLoginData;
    private String sID, sPW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);


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
                        Intent intent = new Intent(LoginActivity.this, SubActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        };



        btnJoin.setOnClickListener(listener);
        btnLogin.setOnClickListener(listener);

    }


    private void save(){
        SharedPreferences.Editor editor = MainActivity.appData.edit();
        editor.putBoolean("SAVE_LOGIN_DATA", chkBoxLogin.isChecked());
        editor.putBoolean("SAVE_STAT", true);
        editor.putString("ID", ID.getText().toString());
        editor.putString("PW", PW.getText().toString());
        editor.putBoolean("LOGIN", true);
        editor.apply();
    }



    private void Login(View v) {

        try {
            sID = ID.getText().toString();
            sPW = PW.getText().toString();
        } catch (NullPointerException e) {
            Log.d("Error", e.getMessage());
        }

        LoginActivity.LoginDB loinDB = new LoginActivity.LoginDB();
        loinDB.execute();



    }

    private class LoginDB extends AsyncTask<Void, Integer, Void> {

        String data = "";

        ConnectSERVER conn;
        boolean login;

        @Override
        protected Void doInBackground(Void... voids) {
            String param = "id=" + sID + "&pw=" + sPW + "";
            String connURL = "http://192.168.0.128/loginGaza.v2.0.php";

            Log.e("POST", param);

            try {
                //서버 연결
                URL url = new URL(connURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                //OutputStream은 안드로이드->서버
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                InputStream is = null;
                BufferedReader in = null;

                is = httpURLConnection.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();

                Log.e("RECV DATA", data);

                if (data.equals("0")) {
                    Log.e("RESULT", "성공적으로 처리되었습니다!");
                } else {
                    Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                }

                //return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                //return data;
            } catch (IOException e) {
                e.printStackTrace();
                //return data;
            }



            return null;

        }
        /*
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    login = false;
                    sID = appData.getString("ID", ID.getText().toString());
                    sPW = appData.getString("PW", PW.getText().toString());
                    conn =  new ConnectSERVER(sID, sPW, "http://192.168.0.128/loginGaza.v2.0.php");
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
        */
        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            //if(!login) {
            if (data.equals("0")) {
                Log.e("RESULT", "성공적으로 처리되었습니다!");
                Toast.makeText(LoginActivity.this, sID + "님 환영합니다!", Toast.LENGTH_LONG).show();
                save();
                Intent intent = new Intent(LoginActivity.this, EditActivity.class);
                intent.putExtra("ID",sID);
                startActivity(intent);
            } else if (data.equals("1")) {
                Log.e("RESULT", "비밀번호가 일치하지 않습니다.");
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
            } else {
                Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                Toast.makeText(LoginActivity.this, data + "등록중 에러", Toast.LENGTH_LONG).show();

            }
        }

        //}

    }
}
