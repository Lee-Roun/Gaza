package com.example.jeonwon.gaza;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by JeonWon on 2018-03-18.
 */

public class LoginDB extends AsyncTask<Void, Integer, Void> {
    private String data = "";
    private String sID, sPW;
    private boolean login;

    public LoginDB(String ID, String PW)
    {
        this.sID = ID;
        this.sPW = PW;
    }
/*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        login = false;
        conn = new ConnectSERVER(sID, sPW, "http://192.168.0.128/loginGaza.v2.0.php");
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

                /* 서버에서 응답 */
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

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);

        //if(!login) {
        if (data.equals("0")) {
            Log.e("RESULT", "성공적으로 처리되었습니다!");
            //Toast.makeText(context, sID + "님 환영합니다!", Toast.LENGTH_LONG).show();
            //save();
            //Intent intent = new Intent(context, EditActivity.class);
            //startActivity(intent);
        } else if (data.equals("1")) {
            Log.e("RESULT", "비밀번호가 일치하지 않습니다.");
            //Toast.makeText(context, "로그인 실패", Toast.LENGTH_LONG).show();
        } else {
            Log.e("RESULT", "에러 발생! ERRCODE = " + data);
            //Toast.makeText(context, data + "등록중 에러", Toast.LENGTH_LONG).show();

        }
    //}

    }

}


