package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ResearchField extends AppCompatActivity {

    Spinner researchspinner;
    private ListView listView;

    private static final String TAG_RID = "rid";
    private static final String TAG_RESEARCH_NAME_KO = "research_name_ko";
    private static final String TAG_RESEARCH_NAME_EN = "research_name_en";
    private static final String TAG_SUPPORT_ORGANIZATION_KO = "support_organization_ko";
    private static final String TAG_SUPPORT_ORGANIZATION_EN = "support_organization_en";
    private static final String TAG_DATE_START = "date_start";
    private static final String TAG_DATE_END = "date_end";
    private static final String TAG_PROGRESS_KO = "연구진행중";
    private static final String TAG_PROGRESS_EN = "Researching";
    private static final String TAG_FINISH_KO = "연구완료";
    private static final String TAG_FINISH_EN = "Finish";

    String myJSON; //나중에 리스너로 세부정보넘길때 사용하면 좋을거 같다는 생각이 든다.
    ArrayList<HashMap<String,String>> research_progresslist;
    ArrayList<HashMap<String,String>> research_finishlist;
    ArrayList<HashMap<String,String>> research_ALLlist;
    Tools tools = new Tools();
    String language = tools.language;
    int stateposition = 0;
    SwipeRefreshLayout research_swipe;
    Button researchedit_btn, maingo_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_field);
        load();
        listView = (ListView)findViewById(R.id.researchlist);
        research_progresslist = new ArrayList<HashMap<String, String>>();
        research_finishlist = new ArrayList<HashMap<String, String>>();
        research_ALLlist = new ArrayList<HashMap<String, String>>();
        researchedit_btn = (Button)findViewById(R.id.researchedit_btn);
        maingo_btn = (Button)findViewById(R.id.maingo_btn);

        if(tools.loginok==true)
        {
            maingo_btn.setVisibility(View.GONE);
            researchedit_btn.setVisibility(View.VISIBLE);
        }
        else
        {
            maingo_btn.setVisibility(View.VISIBLE);
            researchedit_btn.setVisibility(View.INVISIBLE);
        }
        researchedit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchedit_btn = new Intent(ResearchField.this, ResearchField_edit.class);
                startActivity(researchedit_btn);
            }
        });

        maingo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        research_swipe = (SwipeRefreshLayout)findViewById(R.id.research_swipe);
        research_swipe.setColorSchemeResources( //돌아가는 부분 색깔 지정
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        research_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//실질적 새로고침
            @Override
            public void onRefresh() {
                getData(tools.URL+"/research_fields/get_research");
                //getData("http://220.94.178.10:9928/question_board/get_question");
                research_swipe.setRefreshing(false); //이거 안넣으면 안사라지고 계속 돈다.
            }
        });

        researchspinner= (Spinner)findViewById(R.id.researchingspinner);
        researchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_fields/get_research");
                }
                else if(position==1)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_fields/get_research");
                    //getData("http://220.94.178.10:9928/research_fields/get_research");

                }
                else if(position==2)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_fields/get_research");
                    //getData("http://220.94.178.10:9928/research_fields/get_research");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object vo = (Object)parent.getAdapter().getItem(position);
                /*StringTokenizer tokens = new StringTokenizer(vo.toString());
                int count = tokens.countTokens();*/
                String rid = null;
                //Toast.makeText(getApplicationContext(),vo.toString(), Toast.LENGTH_LONG).show();

                rid = vo.toString();
                Log.e("ridid3",rid);
                int indexnum = rid.indexOf("rid");
                rid = rid.substring(indexnum);
                if(rid.indexOf(",")!=-1)
                {
                    indexnum = rid.indexOf(",");
                    rid = rid.substring(0,indexnum);
                }
                else
                {
                    rid = rid.replace("}","");
                }
                Log.e("ridid",rid);
                indexnum = rid.indexOf("=");
                rid = rid.substring(indexnum+1);
                Log.e("ridid2",rid);

               /*String rid = null;
                for(int i=0;i<count;i++)
                {
                    String taketoken = tokens.nextToken(",");
                    Log.e("token",taketoken);
                    if(i==count-1)
                    {

                        StringTokenizer ridtok = new StringTokenizer(taketoken);
                        rid = ridtok.nextToken("=");
                        rid = ridtok.nextToken("=");
                        char[] r = rid.toCharArray();
                        char[] c = new char[r.length-1];
                        for(int j = 0;j<r.length-1;j++)
                        {
                            c[j]=r[j];
                        }
                        rid = String.valueOf(c);
                        //Toast.makeText(getApplicationContext(),rid, Toast.LENGTH_LONG).show();
                        break;
                    }
                }*/

                Intent detail = new Intent(ResearchField.this,Research_detail.class);
                detail.putExtra("rid",rid);
                detail.putExtra("researchJSON",myJSON);
                startActivity(detail);
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
                showList();
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

        research_progresslist.clear();
        research_finishlist.clear();
        research_ALLlist.clear();
        if(language.equals("ko")) {
            try {
                JSONArray jsonArray = new JSONArray(myJSON);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rid = c.getString(TAG_RID);
                    String research_name = c.getString(TAG_RESEARCH_NAME_KO);
                    String support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_KO);
                    String date_start = c.getString(TAG_DATE_START);
                    String date_end = c.getString(TAG_DATE_END);
                    String progress = TAG_PROGRESS_KO;
                    String finish = TAG_FINISH_KO;

                    HashMap<String, String> Research_progress = new HashMap<String, String>();
                    HashMap<String, String> Research_finish = new HashMap<String, String>();
                    HashMap<String, String> Research_all = new HashMap<String, String>();
                    long Time = GetTime(date_end);

                    if(stateposition==0)
                    {
                        Research_all.put(TAG_RID,rid);
                        Research_all.put(TAG_RESEARCH_NAME_KO, research_name);
                        Research_all.put(TAG_SUPPORT_ORGANIZATION_KO, support_organization);
                        Research_all.put(TAG_DATE_START, date_start);
                        Research_all.put(TAG_DATE_END, date_end);
                        if(Time>0) {
                            Research_all.put(TAG_PROGRESS_KO, "연구진행중");
                        }
                        else
                        {
                            Research_all.put(TAG_PROGRESS_KO, "연구완료");
                        }
                        research_ALLlist.add(Research_all);
                    }
                    //배열을 따로 만들어서 포지션에 따라 맞는 배열에 삽입  조건 (기간이 지났냐 안지났냐)

                    if(Time>0) {
                        Research_progress.put(TAG_RID,rid);
                        Research_progress.put(TAG_RESEARCH_NAME_KO, research_name);
                        Research_progress.put(TAG_SUPPORT_ORGANIZATION_KO, support_organization);
                        Research_progress.put(TAG_DATE_START, date_start);
                        Research_progress.put(TAG_DATE_END, date_end);
                        Research_progress.put(TAG_PROGRESS_KO, progress);

                        research_progresslist.add(Research_progress);
                    }
                    else
                    {
                        Research_finish.put(TAG_RID,rid);
                        Research_finish.put(TAG_RESEARCH_NAME_KO, research_name);
                        Research_finish.put(TAG_SUPPORT_ORGANIZATION_KO, support_organization);
                        Research_finish.put(TAG_DATE_START, date_start);
                        Research_finish.put(TAG_DATE_END, date_end);
                        Research_finish.put(TAG_FINISH_KO, finish);

                        research_finishlist.add(Research_finish);
                    }
                }

                //이곳은 포지션 0 1 에따라서 달라지게 만들면 깰끔.
                if(stateposition==0) {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_ALLlist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_KO, TAG_SUPPORT_ORGANIZATION_KO, TAG_DATE_START, TAG_DATE_END,TAG_PROGRESS_KO}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "전체과제", Toast.LENGTH_LONG).show();
                }
                else if(stateposition==1) {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_progresslist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_KO, TAG_SUPPORT_ORGANIZATION_KO, TAG_DATE_START, TAG_DATE_END, TAG_PROGRESS_KO}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "진행과제", Toast.LENGTH_LONG).show();
                }
                else if(stateposition == 2)
                {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_finishlist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_KO, TAG_SUPPORT_ORGANIZATION_KO, TAG_DATE_START, TAG_DATE_END, TAG_FINISH_KO}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "완료과제", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else //한국어 이외 언어일때 진행과제
        {
            try {
                JSONArray jsonArray = new JSONArray(myJSON);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rid = c.getString(TAG_RID);
                    String research_name = c.getString(TAG_RESEARCH_NAME_EN);
                    String support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_EN);
                    String date_start = c.getString(TAG_DATE_START);
                    String date_end = c.getString(TAG_DATE_END);
                    String progress = TAG_PROGRESS_EN;
                    String finish = TAG_FINISH_EN;

                    HashMap<String, String> Research_progress = new HashMap<String, String>();
                    HashMap<String, String> Research_finish = new HashMap<String, String>();
                    HashMap<String, String> Research_all = new HashMap<String, String>();

                    long Time = GetTime(date_end);
                    if(stateposition==0)
                    {
                        Research_all.put(TAG_RID,rid);
                        Research_all.put(TAG_RESEARCH_NAME_EN, research_name);
                        Research_all.put(TAG_SUPPORT_ORGANIZATION_EN, support_organization);
                        Research_all.put(TAG_DATE_START, date_start);
                        Research_all.put(TAG_DATE_END, date_end);
                        if(Time>0) {
                            Research_all.put(TAG_PROGRESS_EN, "Researching");
                        }
                        else
                        {
                            Research_all.put(TAG_PROGRESS_EN, "Finish");
                        }

                        research_ALLlist.add(Research_all);
                    }
                    if(Time>0) {
                        Research_progress.put(TAG_RID,rid);
                        Research_progress.put(TAG_RESEARCH_NAME_EN, research_name);
                        Research_progress.put(TAG_SUPPORT_ORGANIZATION_EN, support_organization);
                        Research_progress.put(TAG_DATE_START, date_start);
                        Research_progress.put(TAG_DATE_END, date_end);
                        Research_progress.put(TAG_PROGRESS_EN, progress);

                        research_progresslist.add(Research_progress);
                    }
                    else
                    {
                        Research_finish.put(TAG_RID,rid);
                        Research_finish.put(TAG_RESEARCH_NAME_EN, research_name);
                        Research_finish.put(TAG_SUPPORT_ORGANIZATION_EN, support_organization);
                        Research_finish.put(TAG_DATE_START, date_start);
                        Research_finish.put(TAG_DATE_END, date_end);
                        Research_finish.put(TAG_FINISH_EN, finish);

                        research_finishlist.add(Research_finish);
                    }

                }
                if(stateposition==0) {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_ALLlist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_EN, TAG_SUPPORT_ORGANIZATION_EN, TAG_DATE_START, TAG_DATE_END, TAG_PROGRESS_EN}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "View All", Toast.LENGTH_LONG).show();
                }
                else if(stateposition == 1) {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_progresslist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_EN, TAG_SUPPORT_ORGANIZATION_EN, TAG_DATE_START, TAG_DATE_END, TAG_PROGRESS_EN}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Progress task", Toast.LENGTH_LONG).show();
                }
                else if(stateposition == 2)
                {
                    ListAdapter adapter = new SimpleAdapter(ResearchField.this, research_finishlist, R.layout.custom_researchfield,
                            new String[]{TAG_RESEARCH_NAME_EN, TAG_SUPPORT_ORGANIZATION_EN, TAG_DATE_START, TAG_DATE_END,  TAG_FINISH_EN}, new int[]{R.id.research_name, R.id.support_organization, R.id.date_start, R.id.date_end, R.id.progress});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Finish task", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public long GetTime(String TAG_DATE_END)  //이거 써볼예정.
    {
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
        long Timesub = 0;
        try {
            Date end_time = dataformat.parse(TAG_DATE_END);
            Date today = new Date();
            Timesub = end_time.getTime()-today.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Timesub;
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
}
