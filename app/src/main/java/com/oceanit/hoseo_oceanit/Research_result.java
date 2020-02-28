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
import java.util.ArrayList;
import java.util.HashMap;

public class Research_result extends AppCompatActivity {

    Spinner researching_resultspinner;
    private ListView listView;

    private static final String TAG_RRID = "rrid";
    private static final String TAG_CLASSIFICATION_KO = "classification_ko";
    private static final String TAG_CLASSIFICATION_EN = "classification_en";
    private static final String TAG_RESULT_NAME_KO = "result_name_ko";
    private static final String TAG_RESULT_NAME_EN = "result_name_en";
    private static final String TAG_ACADEMIC_JOURNAL_KO = "academic_journal_ko";
    private static final String TAG_ACADEMIC_JOURNAL_EN = "academic_journal_en";
    private static final String TAG_WRITERS_KO = "writers_ko";
    private static final String TAG_WRITERS_EN = "writers_en";
    private static final String TAG_P_DATE = "p_date";

    String myJSON; //나중에 리스너로 세부정보넘길때 사용하면 좋을거 같다는 생각이 든다.
    ArrayList<HashMap<String,String>> research_thesis;
    ArrayList<HashMap<String,String>> research_license;
    ArrayList<HashMap<String,String>> research_announcement;
    ArrayList<HashMap<String,String>> research_production;
    ArrayList<HashMap<String,String>> research_alllist;

    HashMap<String, String> result_thesis;
    HashMap<String, String> result_license;
    HashMap<String, String> result_announcement;
    HashMap<String, String> result_production;
    HashMap<String, String> result_all;

    Tools tools = new Tools();
    String language = tools.language;
    int stateposition = 0;
    Button research_resultedit_btn, Maingo_btn;
    SwipeRefreshLayout result_swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_result);
        load();
        listView = (ListView)findViewById(R.id.resultlist);
        research_thesis = new ArrayList<HashMap<String, String>>();
        research_license = new ArrayList<HashMap<String, String>>();
        research_announcement = new ArrayList<HashMap<String, String>>();
        research_production = new ArrayList<HashMap<String, String>>();
        research_alllist = new ArrayList<HashMap<String, String>>();

        research_resultedit_btn = (Button) findViewById(R.id.research_resultedit_btn);
        Maingo_btn = (Button)findViewById(R.id.Maingo_btn);
        if(tools.loginok==true)
        {
            Maingo_btn.setVisibility(View.GONE);
            research_resultedit_btn.setVisibility(View.VISIBLE);
        }
        else
        {
            Maingo_btn.setVisibility(View.VISIBLE);
            research_resultedit_btn.setVisibility(View.INVISIBLE);
        }

        research_resultedit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent research_resultedit_btn = new Intent(Research_result.this, Result_edit.class);
                startActivity(research_resultedit_btn);
            }
        });

        Maingo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        researching_resultspinner = (Spinner)findViewById(R.id.researching_resultspinner);

        researching_resultspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_results/get_result");

                }
                else if(position==1)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_results/get_result");
                }
                else if(position==2)
                {
                    Log.e("thsis", String.valueOf(position));
                    stateposition=position;
                    getData(tools.URL+"/research_results/get_result");
                }
                else if(position==3)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_results/get_result");
                }
                else if(position==4)
                {
                    stateposition=position;
                    getData(tools.URL+"/research_results/get_result");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        result_swipe = (SwipeRefreshLayout)findViewById(R.id.result_swipe);
        result_swipe.setColorSchemeResources( //돌아가는 부분 색깔 지정
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        result_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//실질적 새로고침
            @Override
            public void onRefresh() {
                getData(tools.URL+"/research_results/get_result");
                //getData("http://220.94.178.10:9928/question_board/get_question");
                result_swipe.setRefreshing(false); //이거 안넣으면 안사라지고 계속 돈다.
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = (Object)parent.getAdapter().getItem(position);

                String rrid = null;
                rrid = item.toString();
                Log.e("ridid2",rrid);
                int indexnum = rrid.indexOf("rrid");
                rrid = rrid.substring(indexnum);
                if(rrid.indexOf(",")!=-1)
                {
                    indexnum = rrid.indexOf(",");
                    rrid = rrid.substring(0,indexnum);
                }
                else
                {
                    rrid = rrid.replace("}","");
                }
                Log.e("ridid",rrid);
                indexnum = rrid.indexOf("=");
                rrid = rrid.substring(indexnum+1);
                Log.e("ridid2",rrid);
                Intent detail = new Intent(Research_result.this,Result_detail.class);
                detail.putExtra("rrid",rrid);
                detail.putExtra("resultJSON",myJSON);
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
        research_thesis.clear();
        research_license.clear();
        research_announcement.clear();
        research_production.clear();
        research_alllist.clear();

        if(language.equals("ko")) {
            try {
                JSONArray jsonArray = new JSONArray(myJSON);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rrid = c.getString(TAG_RRID);
                    String classification = c.getString(TAG_CLASSIFICATION_KO);
                    String result_name = c.getString(TAG_RESULT_NAME_KO);
                    String academic_journal = c.getString(TAG_ACADEMIC_JOURNAL_KO);
                    String writers = c.getString(TAG_WRITERS_KO);
                    String date = c.getString(TAG_P_DATE);

                    result_thesis = new HashMap<String, String>();
                    result_license = new HashMap<String, String>();
                    result_announcement = new HashMap<String, String>();
                    result_production = new HashMap<String, String>();
                    result_all = new HashMap<String, String>();

                    if (stateposition == 0) {
                        result_all.put(TAG_RRID, rrid);
                        result_all.put(TAG_CLASSIFICATION_KO, classification);
                        result_all.put(TAG_RESULT_NAME_KO, result_name);
                        result_all.put(TAG_ACADEMIC_JOURNAL_KO, academic_journal);
                        result_all.put(TAG_WRITERS_KO, writers);
                        result_all.put(TAG_P_DATE, date);

                        research_alllist.add(result_all);
                    }
                    //배열을 따로 만들어서 포지션에 따라 맞는 배열에 삽입  조건 (기간이 지났냐 안지났냐)

                    if (classification.equals("논문")) {
                        result_thesis.put(TAG_RRID, rrid);
                        result_thesis.put(TAG_CLASSIFICATION_KO, classification);
                        result_thesis.put(TAG_RESULT_NAME_KO, result_name);
                        result_thesis.put(TAG_ACADEMIC_JOURNAL_KO, academic_journal);
                        result_thesis.put(TAG_WRITERS_KO, writers);
                        result_thesis.put(TAG_P_DATE, date);

                        research_thesis.add(result_thesis);
                    }
                    else if(classification.equals("특허")) {
                        result_license.put(TAG_RRID, rrid);
                        result_license.put(TAG_CLASSIFICATION_KO, classification);
                        result_license.put(TAG_RESULT_NAME_KO, result_name);
                        result_license.put(TAG_ACADEMIC_JOURNAL_KO, academic_journal);
                        result_license.put(TAG_WRITERS_KO, writers);
                        result_license.put(TAG_P_DATE, date);

                        research_license.add(result_license);
                    }
                    else if(classification.equals("발표"))
                    {
                        result_announcement.put(TAG_RRID, rrid);
                        result_announcement.put(TAG_CLASSIFICATION_KO, classification);
                        result_announcement.put(TAG_RESULT_NAME_KO, result_name);
                        result_announcement.put(TAG_ACADEMIC_JOURNAL_KO, academic_journal);
                        result_announcement.put(TAG_WRITERS_KO, writers);
                        result_announcement.put(TAG_P_DATE, date);

                        research_announcement.add(result_announcement);
                    }
                    else if(classification.equals("저서"))
                    {
                        result_production.put(TAG_RRID, rrid);
                        result_production.put(TAG_CLASSIFICATION_KO, classification);
                        result_production.put(TAG_RESULT_NAME_KO, result_name);
                        result_production.put(TAG_ACADEMIC_JOURNAL_KO, academic_journal);
                        result_production.put(TAG_WRITERS_KO, writers);
                        result_production.put(TAG_P_DATE, date);

                        research_production.add(result_production);
                    }
                }

                //이곳은 포지션 0 1 에따라서 달라지게 만들면 깰끔.
                if (stateposition == 0) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_alllist, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_KO, TAG_ACADEMIC_JOURNAL_KO, TAG_CLASSIFICATION_KO, TAG_WRITERS_KO, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "전체과제", Toast.LENGTH_SHORT).show();
                } else if (stateposition == 1) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_thesis, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_KO, TAG_ACADEMIC_JOURNAL_KO, TAG_CLASSIFICATION_KO, TAG_WRITERS_KO, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "논문", Toast.LENGTH_SHORT).show();
                } else if (stateposition == 2) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_license, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_KO, TAG_ACADEMIC_JOURNAL_KO, TAG_CLASSIFICATION_KO, TAG_WRITERS_KO, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "특허", Toast.LENGTH_SHORT).show();
                }
                else if(stateposition==3)
                {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_announcement, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_KO, TAG_ACADEMIC_JOURNAL_KO, TAG_CLASSIFICATION_KO, TAG_WRITERS_KO, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "발표", Toast.LENGTH_SHORT).show();
                }
                else if(stateposition==4)
                {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_production, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_KO, TAG_ACADEMIC_JOURNAL_KO, TAG_CLASSIFICATION_KO, TAG_WRITERS_KO, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "저서", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                JSONArray jsonArray = new JSONArray(myJSON);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rrid = c.getString(TAG_RRID);
                    String classification = c.getString(TAG_CLASSIFICATION_EN);
                    String result_name = c.getString(TAG_RESULT_NAME_EN);
                    String academic_journal = c.getString(TAG_ACADEMIC_JOURNAL_EN);
                    String writers = c.getString(TAG_WRITERS_EN);
                    String date = c.getString(TAG_P_DATE);

                    result_thesis = new HashMap<String, String>();
                    result_license = new HashMap<String, String>();
                    result_announcement = new HashMap<String, String>();
                    result_production = new HashMap<String, String>();
                    result_all = new HashMap<String, String>();

                    if (stateposition == 0) {
                        result_all.put(TAG_RRID, rrid);
                        result_all.put(TAG_CLASSIFICATION_EN, classification);
                        result_all.put(TAG_RESULT_NAME_EN, result_name);
                        result_all.put(TAG_ACADEMIC_JOURNAL_EN, academic_journal);
                        result_all.put(TAG_WRITERS_EN, writers);
                        result_all.put(TAG_P_DATE, date);

                        research_alllist.add(result_all);
                    }
                    //배열을 따로 만들어서 포지션에 따라 맞는 배열에 삽입  조건 (기간이 지났냐 안지났냐)

                    if (classification.equals("Thesis")) {
                        result_thesis.put(TAG_RRID, rrid);
                        result_thesis.put(TAG_CLASSIFICATION_EN, classification);
                        result_thesis.put(TAG_RESULT_NAME_EN, result_name);
                        result_thesis.put(TAG_ACADEMIC_JOURNAL_EN, academic_journal);
                        result_thesis.put(TAG_WRITERS_EN, writers);
                        result_thesis.put(TAG_P_DATE, date);

                        research_thesis.add(result_thesis);
                    }
                    else if(classification.equals("Patent")) {
                        result_license.put(TAG_RRID, rrid);
                        result_license.put(TAG_CLASSIFICATION_EN, classification);
                        result_license.put(TAG_RESULT_NAME_EN, result_name);
                        result_license.put(TAG_ACADEMIC_JOURNAL_EN, academic_journal);
                        result_license.put(TAG_WRITERS_EN, writers);
                        result_license.put(TAG_P_DATE, date);

                        research_license.add(result_license);
                    }
                    else if(classification.equals("Presentation"))
                    {
                        result_announcement.put(TAG_RRID, rrid);
                        result_announcement.put(TAG_CLASSIFICATION_EN, classification);
                        result_announcement.put(TAG_RESULT_NAME_EN, result_name);
                        result_announcement.put(TAG_ACADEMIC_JOURNAL_EN, academic_journal);
                        result_announcement.put(TAG_WRITERS_EN, writers);
                        result_announcement.put(TAG_P_DATE, date);

                        research_announcement.add(result_announcement);
                    }
                    else if(classification.equals("Production"))
                    {
                        result_production.put(TAG_RRID, rrid);
                        result_production.put(TAG_CLASSIFICATION_EN, classification);
                        result_production.put(TAG_RESULT_NAME_EN, result_name);
                        result_production.put(TAG_ACADEMIC_JOURNAL_EN, academic_journal);
                        result_production.put(TAG_WRITERS_EN, writers);
                        result_production.put(TAG_P_DATE, date);

                        research_production.add(result_production);
                    }
                }

                //이곳은 포지션 0 1 에따라서 달라지게 만들면 깰끔.
                if (stateposition == 0) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_alllist, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_EN, TAG_ACADEMIC_JOURNAL_EN, TAG_CLASSIFICATION_EN, TAG_WRITERS_EN, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "View All", Toast.LENGTH_SHORT).show();
                } else if (stateposition == 1) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_thesis, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_EN, TAG_ACADEMIC_JOURNAL_EN, TAG_CLASSIFICATION_EN, TAG_WRITERS_EN, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Thesis", Toast.LENGTH_SHORT).show();
                } else if (stateposition == 2) {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_license, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_EN, TAG_ACADEMIC_JOURNAL_EN, TAG_CLASSIFICATION_EN, TAG_WRITERS_EN, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Patent", Toast.LENGTH_SHORT).show();
                }
                else if(stateposition==3)
                {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_announcement, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_EN, TAG_ACADEMIC_JOURNAL_EN, TAG_CLASSIFICATION_EN, TAG_WRITERS_EN, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Announcement", Toast.LENGTH_SHORT).show();
                }
                else if(stateposition==4)
                {
                    ListAdapter adapter = new SimpleAdapter(Research_result.this, research_production, R.layout.custom_researchresult,
                            new String[]{TAG_RESULT_NAME_EN, TAG_ACADEMIC_JOURNAL_EN, TAG_CLASSIFICATION_EN, TAG_WRITERS_EN, TAG_P_DATE}, new int[]{R.id.result_name, R.id.academic_journal,R.id.classification, R.id.writers, R.id.date});
                    listView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Production", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
