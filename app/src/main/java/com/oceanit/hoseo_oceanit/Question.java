package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Question extends AppCompatActivity {

    Button question_btn;
    private static final String TAG_QID = "qid";
    private static final String TAG_WRITER = "writer";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENTS = "contents";
    private static final String TAG_DATE = "date";
    private  static  final String TAG_HITS = "hits";
    private static final String TAG_COMMENTS = "coments";
    private static final String TAG_COMMENTS_DATE = "coments_date";
    private ListView listView;
    String myJSON;
    Tools tools;
    JSONArray question = null;
    ArrayList<HashMap<String,String>> questionlist;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        tools = new Tools();
        listView = (ListView)findViewById(R.id.qnalist);
        questionlist = new ArrayList<HashMap<String, String>>();
        getData(tools.URL+"/question_board/get_question");
        question_btn = (Button)findViewById(R.id.question_btn);
        question_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionbtn = new Intent(Question.this,QuestionEdit.class);
                startActivity(questionbtn);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipelayout); //스와이프 새로고침 부분
        swipeRefreshLayout.setColorSchemeResources( //돌아가는 부분 색깔 지정
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//실질적 새로고침
            @Override
            public void onRefresh() {
                getData(tools.URL+"/question_board/get_question");
                //getData("http://220.94.178.10:9928/question_board/get_question");
                swipeRefreshLayout.setRefreshing(false); //이거 안넣으면 안사라지고 계속 돈다.
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detali = new Intent(Question.this,Question_detail.class);
                detali.putExtra("position",position);
                detali.putExtra("json",myJSON);
                startActivity(detali);
            }
        });
    }

    public void getData(String url)
    {
        class GetDataJSON extends AsyncTask<String,Void,String>
        {
            @Override
            protected String doInBackground(String... strings) {
                String uri = strings[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    StringBuilder stringBuilder = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append(json + "\n");
                    }
                    return stringBuilder.toString().trim();
                }
                catch (Exception e)
                {
                    return null;
                }

            }
            @Override
            protected void onPostExecute(String s) {
                myJSON = s;
                new Handler().postDelayed(new Runnable() { // 안튕기나 지켜봐야함.
                    @Override
                    public void run() {
                        showList();
                    }
                },500);

            }
        }
        GetDataJSON g = new GetDataJSON();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url);
        }
        else {
            g.execute(url);
        }

    }

    protected void showList()
    {
        questionlist.clear();
        try {
            JSONArray jsonArray = new JSONArray(myJSON);
            //question = jsonObject.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject order = jsonArray.getJSONObject(i);
                JSONObject c = jsonArray.getJSONObject(i);
                //String number = c.getString(TAG_NUMBER);
                String qid = c.getString(TAG_QID);
                Log.e("qid","오냐?");
                Log.e("qid",qid);
                String writer = c.getString(TAG_WRITER);
                String title = c.getString(TAG_TITLE);
                String contents = c.getString(TAG_CONTENTS);
                String date = c.getString(TAG_DATE);
                String hits = c.getString(TAG_HITS);
                String coments = c.getString(TAG_COMMENTS);
                String coments_date = c.getString(TAG_COMMENTS_DATE);
                HashMap<String, String> questions = new HashMap<String, String>();

                //questions.put(qid, Integer.toString(question.length()-i));
                questions.put(TAG_WRITER,writer);
                questions.put(TAG_TITLE, title);
                questions.put(TAG_CONTENTS, contents);
                questions.put(TAG_DATE, date);
                questions.put(TAG_HITS, hits);
                questions.put(TAG_COMMENTS,coments);
                questions.put(TAG_COMMENTS_DATE,coments_date);

                questionlist.add(questions);

            }
            ListAdapter adapter = new SimpleAdapter(Question.this, questionlist, R.layout.custom_questionlist,
                    new String[]{TAG_TITLE, TAG_CONTENTS, TAG_DATE, TAG_WRITER, TAG_HITS}, new int[]{R.id.title,R.id.contents, R.id.date, R.id.writer, R.id.hits});
            listView.setAdapter(adapter);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
