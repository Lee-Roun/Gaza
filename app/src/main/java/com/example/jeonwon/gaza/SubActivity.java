package com.example.jeonwon.gaza;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JeonWon on 2018-03-09.
 */

public class SubActivity extends AppCompatActivity {
    private static String TAG = "phptest_MainActivity";

    private EditText mEditTextName;
    private EditText mEditTextId;
    private EditText mEditTextPw;
    private EditText mEditTextPwChk;
    private EditText mEditTextAge;
    private EditText mEditTextPhone;
    private EditText mEditTextEmail;
    private EditText mEditTextGender;
    private RadioButton mRadioBtnMan, mRadioBtnWoman;
    private RadioGroup mRadioGrp;
    private Button btnSend;

    String name, id, pw, pwcheck, age, phone, gender;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //Button btnCancel = (Button)findViewById(R.id.buttonCancel);
         btnSend = (Button)findViewById(R.id.signUpFinish);

        mEditTextName = (EditText)findViewById(R.id.edtName);
        mEditTextId = (EditText)findViewById(R.id.edtSignUpID);
        mEditTextPw = (EditText)findViewById(R.id.edtSignUpPW);
        mEditTextPwChk = (EditText)findViewById(R.id.edtCheckPW);
        mEditTextAge = (EditText)findViewById(R.id.edtBirthDay);
        mEditTextPhone = (EditText)findViewById(R.id.edtPhone);
        //mEditTextEmail = (EditText)findViewById(R.id.editTextEmail);
        //mEditTextGender = (EditText)findViewById(R.id.edtg);
        mRadioBtnMan = (RadioButton)findViewById(R.id.radioBtnMan);
        mRadioBtnWoman = (RadioButton)findViewById(R.id.radioBtnWoman);
        mRadioGrp = (RadioGroup)findViewById(R.id.genderBtnGroup);
        mRadioBtnMan.setChecked(true);
        gender="Man";

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(view.getId()){
                    case R.id.buttonCancel:

                        finish();
                        break;
                    case R.id.signUpFinish:
                        name = mEditTextName.getText().toString();
                        id = mEditTextId.getText().toString();
                        pw = mEditTextPw.getText().toString();
                        pwcheck = mEditTextPwChk.getText().toString();
                        age = mEditTextAge.getText().toString();
                        phone = mEditTextPhone.getText().toString();

                        //String email = mEditTextEmail.getText().toString();

                        //비밀번호가 같을때
                        if(pw.equals(pwcheck)&&!pw.equals("")){
                            InsertData task = new InsertData();
                            task.execute(id, pw, name, age, phone, gender);

                            //mEditTextName.setText("");
                            //mEditTextId.setText("");
                            //mEditTextPw.setText("");
                            //mEditTextPwChk.setText("");
                            //mEditTextAge.setText("");
                            //mEditTextPhone.setText("");
                            //mEditTextEmail.setText("");
                            //EditTextGender.setText("");

                        }
                        else{
                            Toast.makeText(SubActivity.this, "비밀번호가 서로 다릅니다", Toast.LENGTH_LONG);

                            mEditTextPw.setText("");
                            mEditTextPwChk.setText("");
                        }
                        break;

                }

            }
        };

        RadioGroup.OnCheckedChangeListener radioGrpListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioBtnMan){
                    gender = "Man";
                }
                else if(i==R.id.radioBtnWoman){
                    gender = "Woman";
                }
            }
        };

        //btnCancel.setOnClickListener(listener);
        btnSend.setOnClickListener(listener);
        mRadioGrp.setOnCheckedChangeListener(radioGrpListener);

    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SubActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
            Toast.makeText(SubActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[0];
            String pw = (String)params[1];
            String name = (String)params[2];
            String age = (String)params[3];
            String phone = (String)params[4];
            //String email = (String)params[5];
            String gender = (String)params[5];

            String serverURL = "http://192.168.0.128/insertGaza.v2.1.php";
            String postParameters = "id=" + id + "&pw=" + pw + "&name=" + name + "&age=" + age + "&phone=" + phone + "&gender=" + gender;


            try {
                //서버 연결
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                //OutputStream은 안드로이드->서버
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);


                //InputStream은 서버->안드로이드
                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }

}
