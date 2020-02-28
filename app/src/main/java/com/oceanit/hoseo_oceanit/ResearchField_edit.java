package com.oceanit.hoseo_oceanit;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

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

public class ResearchField_edit extends AppCompatActivity {

    TextView start_time, end_time;
    Button research_field_submit_btn;
    Spinner classificationedit;
    EditText Support_Agency_ko, Support_Agency_en, Research_Name_ko_txt, Research_Name_en_txt, research_officer_ko_txt, research_officer_en_txt, belong_research_officer_ko_txt, belong_research_officer_en_txt
            , research_goal_ko_txt, research_goal_en_txt, research_content_ko_txt, research_content_en_txt, expected_performance_ko_txt, expected_performance_en_txt;
    String chooseclassification;
    int chooseposition;
    Tools tools = new Tools();
    String language = tools.language, take_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_field_edit);

        start_time = (TextView) findViewById(R.id.start_time);
        end_time = (TextView)findViewById(R.id.end_time);

        Support_Agency_ko = (EditText)findViewById(R.id.Support_Agency_ko);
        Support_Agency_en = (EditText)findViewById(R.id.Support_Agency_en);
        Research_Name_ko_txt = (EditText)findViewById(R.id.Research_Name_ko_txt);
        Research_Name_en_txt = (EditText)findViewById(R.id.Research_Name_en_txt);
        research_officer_ko_txt = (EditText)findViewById(R.id.research_officer_ko_txt);
        research_officer_en_txt = (EditText)findViewById(R.id.research_officer_en_txt);
        belong_research_officer_ko_txt = (EditText)findViewById(R.id.belong_research_officer_ko_txt);
        belong_research_officer_en_txt = (EditText)findViewById(R.id.belong_research_officer_en_txt);
        research_goal_ko_txt = (EditText)findViewById(R.id.research_goal_ko_txt);
        research_goal_en_txt = (EditText)findViewById(R.id.research_goal_en_txt);
        research_content_ko_txt = (EditText)findViewById(R.id.research_content_ko_txt);
        research_content_en_txt = (EditText)findViewById(R.id.research_content_en_txt);
        expected_performance_ko_txt = (EditText)findViewById(R.id.expected_performance_ko_txt);
        expected_performance_en_txt = (EditText)findViewById(R.id.expected_performance_en_txt);

        classificationedit = (Spinner)findViewById(R.id.classificationedit);
        classificationedit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object object = (Object)parent.getAdapter().getItem(position);
                chooseclassification = object.toString();
                chooseposition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        research_field_submit_btn = (Button)findViewById(R.id.research_field_submit_btn);
        research_field_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseposition != 0) {
                    if((!start_time.getText().toString().equals("")&&!end_time.getText().toString().equals(""))) {
                        if (!(Support_Agency_ko.getText().toString().equals("") && Support_Agency_en.getText().toString().equals("") && Research_Name_ko_txt.getText().toString().equals("") && Research_Name_en_txt.getText().toString().equals("") && research_officer_ko_txt.getText().toString().equals("")
                                && research_officer_en_txt.getText().toString().equals("") && belong_research_officer_ko_txt.getText().toString().equals("") && belong_research_officer_en_txt.getText().toString().equals(""))) {
                            if (((!research_goal_ko_txt.getText().toString().equals("")) || (!research_goal_en_txt.getText().toString().equals(""))) && ((!research_content_ko_txt.getText().toString().equals("")) || (!research_content_en_txt.getText().toString().equals(""))) && (
                                    (!expected_performance_ko_txt.getText().toString().equals("")) || (!expected_performance_en_txt.getText().toString().equals("")))) {
                                FinishDialog(2);
                            } else {
                                FinishDialog(1);
                            }
                        } else {
                            FinishDialog(1);
                        }
                    }
                    else
                    {
                        FinishDialog(4);
                    }
                }
                else
                {
                    FinishDialog(3);
                }
            }
        });


    }
    public void onCalendar (View v) {
        DialogFragment newFragment = new DatePickerFragment(v);   //DatePickerFragment 객체 생성
        newFragment.show(getSupportFragmentManager(), "datePicker");                //프래그먼트 매니저를 이용하여 프래그먼트 보여주기
    }

    public void FinishDialog(int num) //경고 알림창 띄우기
    {
        String language = tools.language;
        AlertDialog.Builder caution = new AlertDialog.Builder(ResearchField_edit.this);
        if(num == 1)
        {
            caution.setTitle(R.string.field_warning);
            caution.setMessage(R.string.field_fill_all).setCancelable(false)
                    .setPositiveButton(R.string.field_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        else if(num == 2) {

            caution.setTitle(R.string.field_want_save);
            caution.setMessage(R.string.field_want_research_save).setCancelable(false)
                    .setPositiveButton(R.string.field_submit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insert();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (take_answer.equals("ok")) {
                                        Toast.makeText(getApplicationContext(), R.string.field_succeesed, Toast.LENGTH_LONG).show();
                                        finish();
                                    } else if (take_answer.equals(null)) {
                                        FinishDialog(5);
                                    }
                                }
                            }, 500);


                            ResearchField_edit.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.field_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

        }
        else if(num == 3)
        {
            caution.setTitle(R.string.field_warning);
            caution.setMessage(R.string.field_insert_classification).setCancelable(false)
                    .setPositiveButton(R.string.field_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        else if(num == 4)
        {
            caution.setTitle(R.string.field_warning);
            caution.setMessage(R.string.field_insert_date).setCancelable(false)
                    .setPositiveButton(R.string.field_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        else if(num == 5)
        {
            caution.setTitle(R.string.field_error);
            caution.setMessage(R.string.no_communication).setCancelable(false)
                    .setPositiveButton(R.string.field_ok, new DialogInterface.OnClickListener() {
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

        String classification_en = null, classification_ko = null;

        if(chooseposition == 1)
        {
            if(language.equals("ko"))
            {
                classification_en = "Consignment";
                classification_ko = chooseclassification;
            }
            else
            {
                classification_en = chooseclassification;
                classification_ko = "위탁";
            }
        }
        else if(chooseposition == 2)
        {
            if(language.equals("ko"))
            {
                classification_en = "Cooperation";
                classification_ko = chooseclassification;

            }
            else
            {
                classification_en = chooseclassification;
                classification_ko = "협동";
            }
        }

        else if(chooseposition == 3)
        {
            if(language.equals("ko"))
            {
                classification_en = "Service";
                classification_ko = chooseclassification;

            }
            else
            {
                classification_en = chooseclassification;
                classification_ko = "용역";
            }
        }
        else if(chooseposition == 4)
        {
            if(language.equals("ko"))
            {
                classification_en = "Joint";
                classification_ko = chooseclassification;

            }
            else
            {
                classification_en = chooseclassification;
                classification_ko = "공동";
            }
        }
        else if(chooseposition == 5)
        {
            if(language.equals("ko"))
            {
                classification_en = "Subject";
                classification_ko = chooseclassification;

            }
            else
            {
                classification_en = chooseclassification;
                classification_ko = "주관";
            }
        }
        String start_date = start_time.getText().toString();
        String end_date = end_time.getText().toString();
        String support_Agency_ko = Support_Agency_ko.getText().toString();
        String support_Agency_en = Support_Agency_en.getText().toString();
        String research_Name_ko = Research_Name_ko_txt.getText().toString();
        String research_Name_en = Research_Name_en_txt .getText().toString();
        String research_officer_ko = research_officer_ko_txt.getText().toString();
        String research_officer_en = research_officer_en_txt .getText().toString();
        String belong_research_officer_ko = belong_research_officer_ko_txt .getText().toString();
        String belong_research_officer_en = belong_research_officer_en_txt .getText().toString();
        String research_goal_ko = research_goal_ko_txt .getText().toString();
        String research_goal_en = research_goal_en_txt .getText().toString();
        String research_content_ko = research_content_ko_txt.getText().toString();
        String research_content_en = research_content_en_txt.getText().toString();
        String expected_performance_ko = expected_performance_ko_txt.getText().toString();
        String expected_performance_en = expected_performance_en_txt.getText().toString();



        insertToDatabase(classification_en, classification_ko, start_date, end_date,support_Agency_ko, support_Agency_en, research_Name_ko, research_Name_en, research_officer_ko, research_officer_en, belong_research_officer_ko
        , belong_research_officer_en, research_goal_ko, research_goal_en, research_content_ko, research_content_en, expected_performance_ko, expected_performance_en);
    }

    private void insertToDatabase(final String classification_en, String classification_ko, String start_date, String end_date, String support_Agency_ko, String support_Agency_en, String research_Name_ko,
                                  String research_Name_en, String research_officer_ko, String research_officer_en, String belong_research_officer_ko, String belong_research_officer_en, String research_goal_ko, String research_goal_en,
                                  String research_content_ko, String research_content_en, String expected_performance_ko, String expected_performance_en) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";

            @Override
            protected String doInBackground(String... strings) {

                String classification_en = (String) strings[0];
                String classification_ko = (String) strings[1];
                String start_date = (String) strings[2];
                String end_date = (String) strings[3];
                String support_Agency_ko = (String) strings[4];
                String support_Agency_en = (String) strings[5];
                String research_Name_ko = (String) strings[6];
                String research_Name_en = (String) strings[7];
                String research_officer_ko = (String) strings[8];
                String research_officer_en = (String) strings[9];
                String belong_research_officer_ko = (String) strings[10];
                String belong_research_officer_en = (String) strings[11];
                String research_goal_ko = (String) strings[12];
                String research_goal_en = (String) strings[13];
                String research_content_ko = (String) strings[14];
                String research_content_en = (String) strings[15];
                String expected_performance_ko = (String) strings[16];
                String expected_performance_en = (String) strings[17];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("classification_en", classification_en);
                    jsonObject.accumulate("classification_ko", classification_ko);
                    jsonObject.accumulate("start_date", start_date);
                    jsonObject.accumulate("end_date", end_date);
                    jsonObject.accumulate("support_Agency_ko", support_Agency_ko);
                    jsonObject.accumulate("support_Agency_en", support_Agency_en);
                    jsonObject.accumulate("research_Name_ko", research_Name_ko);
                    jsonObject.accumulate("research_Name_en", research_Name_en);
                    jsonObject.accumulate("research_officer_ko", research_officer_ko);
                    jsonObject.accumulate("research_officer_en", research_officer_en);
                    jsonObject.accumulate("belong_research_officer_ko", belong_research_officer_ko);
                    jsonObject.accumulate("belong_research_officer_en", belong_research_officer_en);
                    jsonObject.accumulate("research_goal_ko", research_goal_ko);
                    jsonObject.accumulate("research_goal_en", research_goal_en);
                    jsonObject.accumulate("research_content_ko", research_content_ko);
                    jsonObject.accumulate("research_content_en", research_content_en);
                    jsonObject.accumulate("expected_performance_ko", expected_performance_ko);
                    jsonObject.accumulate("expected_performance_en", expected_performance_en);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/research_fields/insert_research");
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
                take_answer = s;
            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,classification_en, classification_ko, start_date, end_date,support_Agency_ko, support_Agency_en, research_Name_ko, research_Name_en, research_officer_ko, research_officer_en, belong_research_officer_ko
                    , belong_research_officer_en, research_goal_ko, research_goal_en, research_content_ko, research_content_en, expected_performance_ko, expected_performance_en);
        }
        else {
            task.execute(classification_en, classification_ko, start_date, end_date,support_Agency_ko, support_Agency_en, research_Name_ko, research_Name_en, research_officer_ko, research_officer_en, belong_research_officer_ko
                    , belong_research_officer_en, research_goal_ko, research_goal_en, research_content_ko, research_content_en, expected_performance_ko, expected_performance_en);
        }

    }
}