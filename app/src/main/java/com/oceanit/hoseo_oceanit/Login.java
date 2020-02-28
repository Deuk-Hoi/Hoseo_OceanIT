package com.oceanit.hoseo_oceanit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public class Login extends AppCompatActivity implements Button.OnClickListener{

    TextView create_account;
    EditText edit_id,edit_pass;
    Button logingo;
    Tools tools = new Tools();
    CheckBox checkBox;
    String language = tools.language;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        load();
        //clearAlldata();
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_pass = (EditText)findViewById(R.id.edit_pass);
        progressdialog = new ProgressDialog(Login.this);
        checkBox = (CheckBox)findViewById(R.id.save_id);
        create_account = (TextView)findViewById(R.id.create_account);
        logingo = (Button)findViewById(R.id.logingo);

        if(tools.checkbox==true)
        {
            checkBox.setChecked(true);
            edit_id.setText(tools.saveid);
        }

        create_account.setOnClickListener(this);
        logingo.setOnClickListener(this);


    }
    //--------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onClick(final View v) {
        switch (v.getId())
        {
            case R.id.create_account:
                Intent create_account = new Intent(Login.this,CreateAccount.class);
                startActivity(create_account);
                break;

            case R.id.logingo:
                String Id = edit_id.getText().toString();
                String Pass = edit_pass.getText().toString();
                insertToDatabase(Id, Pass);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("name",tools.username);
                        if(tools.username.equals("undefined"))
                        {
                            edit_id.setText(null);
                            edit_pass.setText(null);
                            load();
                            String b =tools.loginok.toString();
                            Log.e("true?",b);
                            progressdialog.dismiss();
                            //Toast.makeText(getApplicationContext(),"아이디/비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                            Snackbar.make(v,R.string.mismatch,Snackbar.LENGTH_SHORT).show();
                        }
                        else
                        {
                            tools.loginok = true;
                            String a =tools.loginok.toString();
                            Log.e("login2",a);
                            save();
                            if(checkBox.isChecked()==true)
                            {
                                tools.checkbox = true;
                            }
                            ((MainActivity)MainActivity.CONTEXT).onResume(); //메인액티비티 새로고침
                            progressdialog.dismiss();
                            finish();
                            Toast.makeText(getApplicationContext(),R.string.loginsucceeded,Toast.LENGTH_SHORT).show();
                        }
                    }
                },500);
                break;

        }
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------

    private void insertToDatabase(final String Id, String Pass) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";

            @Override
            protected void onPreExecute() {
                progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                if(language.equals("ko")) {
                    progressdialog.setMessage("로그인중 입니다 잠시만 기다려 주세요...");
                }
                else
                {
                    progressdialog.setMessage("Logging in Please wait...");
                }
                progressdialog.show();
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {

                String Id  = (String) strings[0];
                String Pass = (String) strings[1];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("uid", Id);
                    jsonObject.accumulate("upw", Pass);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/login/match_info");
                        //URL url = new URL("http://220.94.178.10:9928/login/match_info");
                        //연결을 함
                        con = (HttpURLConnection) url.openConnection();

                        con.setRequestMethod("POST");//POST방식으로 보냄
                        con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");//application JSON 형식으로 전송
                        con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                        con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                        con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                        con.connect();

                        //서버로 보내기위해서 스트림 만듬
                        OutputStream outStream = con.getOutputStream();
                        //버퍼를 생성하고 넣음
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                        String a = jsonObject.toString();
                        Log.e("token",a);
                        writer.write(jsonObject.toString());
                        writer.flush();
                        writer.close();//버퍼를 받아줌

                        //서버로 부터 데이터를 받음
                        InputStream stream = con.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while((line = reader.readLine()) != null){
                            buffer.append(line);
                        }

                        return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(con != null){
                            con.disconnect();
                        }
                        try {
                            if(reader != null){
                                reader.close();//버퍼를 닫아줌
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.e("get",s);
                if(!(s.equals("undefined"))) {
                    StringTokenizer tokens = new StringTokenizer(s);
                    tools.username = tokens.nextToken(",");
                    tools.admin = tokens.nextToken(",");
                    Log.e("name",tools.username);
                    Log.e("admin",tools.admin);
                }
                else {
                    tools.username = s;
                    Log.e("name", tools.username);
                }
            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Id, Pass);
        }
        else {
            task.execute(Id, Pass);
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        save();
        finish();
    }

    private void save()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTF.edit();
        editor.putBoolean("Save_login_data",tools.loginok);
        editor.putString("ID",edit_id.getText().toString());
        editor.putString("PASS",edit_pass.getText().toString());
        editor.putString("NAME",tools.username);
        editor.putString("ADMIN",tools.admin);
        editor.putBoolean("IDSAVE",checkBox.isChecked());
        editor.commit();
    }
    private void load()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        tools.loginok = loginTF.getBoolean("Save_login_data",false);
        tools.username = loginTF.getString("NAME","");
        tools.saveid= loginTF.getString("ID","");
        tools.checkbox=loginTF.getBoolean("IDSAVE",false);
        /*saveid = loginTF.getString("ID","");
        savepass = loginTF.getString("PASS","");*/
    }
    private void clearAlldata()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTF.edit();
        editor.clear();
        editor.commit();
    }

    private void exitcleardata()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTF.edit();
        editor.remove("Save_login_data");
        editor.remove("PASS");
        editor.remove("NAME");
        editor.remove("ADMIN");
        editor.commit();
    }
}
