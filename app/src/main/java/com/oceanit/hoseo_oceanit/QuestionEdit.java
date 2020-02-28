package com.oceanit.hoseo_oceanit;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class QuestionEdit extends AppCompatActivity {

    Tools tools = new Tools(); //언어 추출 기능 구성되있음
    EditText titleedit, writeredit, contentsedit;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        load();

        titleedit = (EditText) findViewById(R.id.titleedit);
        writeredit = (EditText) findViewById(R.id.writeredit);
        contentsedit = (EditText) findViewById(R.id.contentsedit);
        submit_btn = (Button) findViewById(R.id.submit_btn);

        if(tools.loginok==true)
        {
            writeredit.setText(tools.username);
            writeredit.setFocusable(false);
        }
        else
        {
            writeredit.setText(R.string.custom_write);
            writeredit.setFocusable(false);
        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(titleedit.getText().toString().equals("")||contentsedit.getText().toString().equals("")))
                {
                    FinishDialog(2);
                }
                else
                {
                    FinishDialog(1);
                }

            }
        });
    }

    public void FinishDialog(int num) //경고 알림창 띄우기
    {
        String language = tools.language;
        AlertDialog.Builder caution = new AlertDialog.Builder(QuestionEdit.this);
        if(num==1)
        {
            caution.setTitle(R.string.question_warning);
            caution.setMessage(R.string.did_not_fill).setCancelable(false)
                    .setPositiveButton(R.string.warning_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        else if(num==2) {
            caution.setTitle(R.string.want_save);
            caution.setMessage(R.string.do_you_want_upload).setCancelable(false)
                    .setPositiveButton(R.string.upload, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), R.string.upload_succeesed, Toast.LENGTH_LONG).show();
                            insert();
                            QuestionEdit.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.quetion_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

        }
        AlertDialog Caution = caution.create();
        Caution.show();
    }


    public void insert() {
        String Title = titleedit.getText().toString();
        String Writer = writeredit.getText().toString();
        String Contents = contentsedit.getText().toString();

        insertToDatabase(Title, Writer, Contents);
    }

    private void insertToDatabase(final String Title, String Writer, String Contents) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";

            @Override
            protected String doInBackground(String... strings) {

                String Title = (String) strings[0];
                String Writer = (String) strings[1];
                String Contents = (String) strings[2];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("title", Title);
                    jsonObject.accumulate("writer", Writer);
                    jsonObject.accumulate("contents", Contents);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/question_board/get_post");
                        //URL url = new URL("http://220.94.178.10:9928/question_board/get_post");
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
            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Title, Writer, Contents);
        }
        else {
            task.execute(Title, Writer, Contents);
        }

    }

    private void load()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        tools.loginok = loginTF.getBoolean("Save_login_data",false);
        tools.username = loginTF.getString("NAME","");
        tools.saveid= loginTF.getString("ID","");
        tools.checkbox=loginTF.getBoolean("IDSAVE",false);
    }
}