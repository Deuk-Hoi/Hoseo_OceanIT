package com.oceanit.hoseo_oceanit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Question_detail extends AppCompatActivity {

    public static Context question_detail;
    private ArrayList<HashMap<String,String>> commentData = new ArrayList<HashMap<String, String>>();
    TextView detail_title,detail_contents,detail_date,detail_writer,detail_hits, comment_date, comment_txt, admincomment, Separation_line;
    private static final String TAG_QID = "qid";
    private static final String TAG_WRITER = "writer";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENTS = "contents";
    private static final String TAG_DATE = "date";
    private static final String TAG_HITS = "hits";
    private static final String TAG_COMMENTS = "coments";
    private static final String TAG_COMMENTS_DATE = "coments_date";
    EditText question_edit;
    Button answer_btn,backlist_btn;
    Tools tools = new Tools();
    int postqid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        load();
        detail_title = (TextView)findViewById(R.id.detail_title);
        detail_contents = (TextView)findViewById(R.id.detail_contents);
        detail_date = (TextView)findViewById(R.id.detail_date);
        detail_writer = (TextView)findViewById(R.id.detail_writer);
        detail_hits = (TextView)findViewById(R.id.detail_hits);
        admincomment = (TextView)findViewById(R.id.admincomment);
        Separation_line = (TextView)findViewById(R.id.Separation_line);
        comment_date = (TextView)findViewById(R.id.comment_date);
        comment_txt = (TextView)findViewById(R.id.comment_txt);
        question_edit = (EditText)findViewById(R.id.question_edit);
        answer_btn = (Button)findViewById(R.id.answer_btn);
        backlist_btn = (Button)findViewById(R.id.backlist_btn);
        if(tools.admin.equals("null"))
        {
            question_edit.setVisibility(View.GONE);
            answer_btn.setVisibility(View.GONE);
            backlist_btn.setVisibility(View.VISIBLE);
        }
        Intent questionIntent = this.getIntent();
        String questionJSON = questionIntent.getStringExtra("json");
        int questionPosition = questionIntent.getIntExtra("position",0);

        showquestion(questionJSON,questionPosition);
        answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcomment(question_edit);
                question_edit.setText(null);
                InputMethodManager seekeyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE); // 키보드 숨기기 기능
                seekeyboard.hideSoftInputFromWindow(question_edit.getWindowToken(), 0);
            }
        });
        backlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showquestion(String JSON, int position )
    {
        try {
            JSONArray jsonArray = new JSONArray(JSON);
            JSONObject c = jsonArray.getJSONObject(position);
            String writer = c.getString(TAG_WRITER);
            String title = c.getString(TAG_TITLE);
            String contents = c.getString(TAG_CONTENTS);
            String date = c.getString(TAG_DATE);
            String hits = c.getString(TAG_HITS);
            String coments = c.getString(TAG_COMMENTS);
            String coments_date = c.getString(TAG_COMMENTS_DATE);
            String qid = c.getString(TAG_QID);
            postqid = Integer.parseInt(qid);

            detail_title.setText(title);
            detail_contents.setText(contents);
            detail_date.setText(date);
            detail_writer.setText(writer);
            detail_hits.setText(hits);
            if(coments.equals("null")||coments_date.equals("null"))
            {
                comment_date.setVisibility(View.GONE);
                comment_txt.setVisibility(View.GONE);
                admincomment.setVisibility(View.GONE);
                Separation_line.setVisibility(View.GONE);
            }
            else
            {
                comment_date.setText(coments_date);
                comment_txt.setText(coments);
                question_edit.setVisibility(View.GONE);
                answer_btn.setVisibility(View.GONE);
                backlist_btn.setVisibility(View.VISIBLE);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    public void showcomment(EditText questionedit)
    {
        SimpleDateFormat current = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String current_time = current.format (System.currentTimeMillis());
        String Comment = questionedit.getText().toString();
        comment_date.setVisibility(View.VISIBLE);
        comment_txt.setVisibility(View.VISIBLE);
        admincomment.setVisibility(View.VISIBLE);
        Separation_line.setVisibility(View.VISIBLE);

        answer_btn.setVisibility(View.GONE);
        question_edit.setVisibility(View.GONE);
        backlist_btn.setVisibility(View.VISIBLE);

        comment_date.setText(current_time);
        comment_txt.setText(Comment);
        insert(questionedit);
    }


    public void insert(EditText question_edit) {
        String Comment = question_edit.getText().toString();
        insertToDatabase(Comment);
    }

    private void insertToDatabase(final String Comment) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";

            @Override
            protected String doInBackground(String... strings) {

                SimpleDateFormat current = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
                String current_time = current.format (System.currentTimeMillis());
                String comment = (String) strings[0];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("comment", comment);
                    jsonObject.accumulate("qid", postqid);
                    jsonObject.accumulate("time", current_time);
                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/question_board/get_comment");
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
                //Log.e("get",s);
            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Comment);
        }
        else {
            task.execute(Comment);
        }

    }

    private void load()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        tools.loginok = loginTF.getBoolean("Save_login_data",false);
        tools.username = loginTF.getString("NAME","");
        tools.saveid= loginTF.getString("ID","");
        tools.checkbox=loginTF.getBoolean("IDSAVE",false);
        tools.admin = loginTF.getString("ADMIN","null");
        /*saveid = loginTF.getString("ID","");
        savepass = loginTF.getString("PASS","");*/
    }

}
