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

public class Result_edit extends AppCompatActivity {

    TextView announcement_date_txt;
    EditText thesis_name_ko, thesis_name_en, academic_society_academic_name_ko, academic_society_academic_name_en, author_ko_txt, author_en_txt, presentation_medium_ko_txt, presentation_medium_en_txt, related_task_ko_txt, related_task_en_txt, abstract_ko_txt, abstract_en_txt;
    Spinner classificationedit, country_edit;
    String chooseclassification, choose_country;
    int classification_position, country_position;
    Button research_result_submit_btn;
    Tools tools = new Tools();
    String language = tools.language, take_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_edit);

        announcement_date_txt = (TextView) findViewById(R.id.announcement_date_txt);

        thesis_name_ko = (EditText) findViewById(R.id.thesis_name_ko);
        thesis_name_en = (EditText) findViewById(R.id.thesis_name_en);
        academic_society_academic_name_ko = (EditText) findViewById(R.id.academic_society_academic_name_ko);
        academic_society_academic_name_en = (EditText) findViewById(R.id.academic_society_academic_name_en);
        author_ko_txt = (EditText) findViewById(R.id.author_ko_txt);
        author_en_txt = (EditText) findViewById(R.id.author_en_txt);
        presentation_medium_ko_txt = (EditText) findViewById(R.id.presentation_medium_ko_txt);
        presentation_medium_en_txt = (EditText) findViewById(R.id.presentation_medium_en_txt);
        related_task_ko_txt = (EditText) findViewById(R.id.related_task_ko_txt);
        related_task_en_txt = (EditText) findViewById(R.id.related_task_en_txt);
        abstract_ko_txt = (EditText) findViewById(R.id.abstract_ko_txt);
        abstract_en_txt = (EditText) findViewById(R.id.abstract_en_txt);

        classificationedit = (Spinner) findViewById(R.id.classificationedit);
        country_edit = (Spinner) findViewById(R.id.country_edit);
        classificationedit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object classification = (Object) parent.getAdapter().getItem(position);
                chooseclassification = classification.toString();
                classification_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        country_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object country = (Object) parent.getAdapter().getItem(position);
                choose_country = country.toString();
                country_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        research_result_submit_btn = (Button) findViewById(R.id.research_result_submit_btn);
        research_result_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (classification_position != 0) {
                    if (!(announcement_date_txt.getText().toString().equals(""))) {
                        if (country_position != 0) {
                            if (!(thesis_name_ko.getText().toString().equals("") && thesis_name_en.getText().toString().equals("") && academic_society_academic_name_ko.getText().toString().equals("") && academic_society_academic_name_en.getText().toString().equals("")
                                    && author_ko_txt.getText().toString().equals("") && author_en_txt.getText().toString().equals("") && presentation_medium_ko_txt.getText().toString().equals("") && presentation_medium_en_txt.getText().toString().equals("") && related_task_ko_txt.getText().toString().equals("")
                                    && related_task_en_txt.getText().toString().equals(""))) {
                                if ((!abstract_ko_txt.getText().toString().equals("")) || (!abstract_en_txt.getText().toString().equals(""))) {
                                    ResultDialog(5);
                                } else {
                                    ResultDialog(2);
                                }
                            } else {
                                ResultDialog(2);
                            }
                        } else {
                            ResultDialog(4);
                        }
                    } else {
                        ResultDialog(3);
                    }
                } else {
                    ResultDialog(1);
                }
            }
        });


    }

    public void onCalendar2(View v) {
        DialogFragment newFragment = new DatePickerFragment_for_result(v);   //DatePickerFragment 객체 생성
        newFragment.show(getSupportFragmentManager(), "datePicker");                //프래그먼트 매니저를 이용하여 프래그먼트 보여주기
    }

    public void insert() {

        String classification_en = null, classification_ko = null, country_ko = null, country_en = null;

        if (classification_position == 1) {
            if (language.equals("ko")) {
                classification_en = "Thesis";
                classification_ko = chooseclassification;
            } else {
                classification_en = chooseclassification;
                classification_ko = "논문";
            }
        } else if (classification_position == 2) {
            if (language.equals("ko")) {
                classification_en = "Patent";
                classification_ko = chooseclassification;

            } else {
                classification_en = chooseclassification;
                classification_ko = "특허";
            }
        } else if (classification_position == 3) {
            if (language.equals("ko")) {
                classification_en = "Announcement";
                classification_ko = chooseclassification;

            } else {
                classification_en = chooseclassification;
                classification_ko = "발표";
            }
        }


        String Announcement_date_txt = announcement_date_txt.getText().toString();
        String Thesis_name_ko = thesis_name_ko.getText().toString();
        String Thesis_name_en = thesis_name_en.getText().toString();
        String Academic_society_academic_name_ko = academic_society_academic_name_ko.getText().toString();
        String Academic_society_academic_name_en = academic_society_academic_name_en.getText().toString();
        String Author_ko_txt = author_ko_txt.getText().toString();
        String Author_en_txt = author_en_txt.getText().toString();
        String Presentation_medium_ko_txt = presentation_medium_ko_txt.getText().toString();
        String Presentation_medium_en_txt = presentation_medium_en_txt.getText().toString();
        String Related_task_ko_txt = related_task_ko_txt.getText().toString();
        String Related_task_en_txt = related_task_en_txt.getText().toString();
        String Abstract_ko_txt = abstract_ko_txt.getText().toString();
        String Abstract_en_txt = abstract_en_txt.getText().toString();

        if (country_position == 1) {
            if (language.equals("ko")) {
                country_en = "Korea";
                country_ko = choose_country;
            } else {
                country_en = choose_country;
                country_ko = "대한민국";
            }
        } else if (country_position == 2) {
            if (language.equals("ko")) {
                country_en = "America";
                country_ko = choose_country;
            } else {
                country_en = choose_country;
                country_ko = "미국";
            }
        }


        insertToDatabase(classification_en, classification_ko, country_en, country_ko, Announcement_date_txt, Thesis_name_ko, Thesis_name_en, Academic_society_academic_name_ko, Academic_society_academic_name_en, Author_ko_txt, Author_en_txt
                , Presentation_medium_ko_txt, Presentation_medium_en_txt, Related_task_ko_txt, Related_task_en_txt, Abstract_ko_txt, Abstract_en_txt);
    }

    private void insertToDatabase(final String classification_en, String classification_ko, String country_en, String country_ko, String Announcement_date_txt, String Thesis_name_ko, String Thesis_name_en,
                                  String Academic_society_academic_name_ko, String Academic_society_academic_name_en, String Author_ko_txt, String Author_en_txt, String Presentation_medium_ko_txt, String Presentation_medium_en_txt, String Related_task_ko_txt,
                                  String Related_task_en_txt, String Abstract_ko_txt, String Abstract_en_txt) {

        class InsertData extends AsyncTask<String, Void, String> {
            String result = "";

            @Override
            protected String doInBackground(String... strings) {

                String classification_en = (String) strings[0];
                String classification_ko = (String) strings[1];
                String country_en = (String) strings[2];
                String country_ko = (String) strings[3];
                String Announcement_date_txt = (String) strings[4];
                String Thesis_name_ko = (String) strings[5];
                String Thesis_name_en = (String) strings[6];
                String Academic_society_academic_name_ko = (String) strings[7];
                String Academic_society_academic_name_en = (String) strings[8];
                String Author_ko_txt = (String) strings[9];
                String Author_en_txt = (String) strings[10];
                String Presentation_medium_ko_txt = (String) strings[11];
                String Presentation_medium_en_txt = (String) strings[12];
                String Related_task_ko_txt = (String) strings[13];
                String Related_task_en_txt = (String) strings[14];
                String Abstract_ko_txt = (String) strings[15];
                String Abstract_en_txt = (String) strings[16];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("classification_en", classification_en);
                    jsonObject.accumulate("classification_ko", classification_ko);
                    jsonObject.accumulate("country_en", country_en);
                    jsonObject.accumulate("country_ko", country_ko);
                    jsonObject.accumulate("Announcement_date_txt", Announcement_date_txt);
                    jsonObject.accumulate("Thesis_name_ko", Thesis_name_ko);
                    jsonObject.accumulate("Thesis_name_en", Thesis_name_en);
                    jsonObject.accumulate("Academic_society_academic_name_ko", Academic_society_academic_name_ko);
                    jsonObject.accumulate("Academic_society_academic_name_en", Academic_society_academic_name_en);
                    jsonObject.accumulate("Author_ko_txt", Author_ko_txt);
                    jsonObject.accumulate("Author_en_txt", Author_en_txt);
                    jsonObject.accumulate("Presentation_medium_ko_txt", Presentation_medium_ko_txt);
                    jsonObject.accumulate("Presentation_medium_en_txt", Presentation_medium_en_txt);
                    jsonObject.accumulate("Related_task_ko_txt", Related_task_ko_txt);
                    jsonObject.accumulate("Related_task_en_txt", Related_task_en_txt);
                    jsonObject.accumulate("Abstract_ko_txt", Abstract_ko_txt);
                    jsonObject.accumulate("Abstract_en_txt", Abstract_en_txt);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try {
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/research_results/insert_result");
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
                        Log.e("token", a);
                        writer.write(jsonObject.toString());
                        writer.flush();
                        writer.close();//버퍼를 받아줌

                        //서버로 부터 데이터를 받음
                        InputStream stream = con.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }

                        return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                        try {
                            if (reader != null) {
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
                Log.e("ans",take_answer);
            }
        }
        InsertData task = new InsertData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, classification_en, classification_ko, country_en, country_ko, Announcement_date_txt, Thesis_name_ko, Thesis_name_en, Academic_society_academic_name_ko, Academic_society_academic_name_en, Author_ko_txt, Author_en_txt
                    , Presentation_medium_ko_txt, Presentation_medium_en_txt, Related_task_ko_txt, Related_task_en_txt, Abstract_ko_txt, Abstract_en_txt);
        } else {
            task.execute(classification_en, classification_ko, country_en, country_ko, Announcement_date_txt, Thesis_name_ko, Thesis_name_en, Academic_society_academic_name_ko, Academic_society_academic_name_en, Author_ko_txt, Author_en_txt
                    , Presentation_medium_ko_txt, Presentation_medium_en_txt, Related_task_ko_txt, Related_task_en_txt, Abstract_ko_txt, Abstract_en_txt);
        }

    }

    public void ResultDialog(int num) //경고 알림창 띄우기
    {

        AlertDialog.Builder caution = new AlertDialog.Builder(Result_edit.this);


        if (num == 1) {
            caution.setTitle(R.string.result_warning);
            caution.setMessage(R.string.result_insert_classification).setCancelable(false)
                    .setPositiveButton(R.string.result_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else if (num == 2) {
            caution.setTitle(R.string.result_warning);
            caution.setMessage(R.string.result_fill_all).setCancelable(false)
                    .setPositiveButton(R.string.result_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else if (num == 3) {
            caution.setTitle(R.string.result_warning);
            caution.setMessage(R.string.result_insert_date).setCancelable(false)
                    .setPositiveButton(R.string.result_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else if (num == 4) {
            caution.setTitle(R.string.result_warning);
            caution.setMessage(R.string.result_insert_country).setCancelable(false)
                    .setPositiveButton(R.string.result_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else if (num == 5) {

            caution.setTitle(R.string.result_want_save);
            caution.setMessage(R.string.result_want_research_save).setCancelable(false)
                    .setPositiveButton(R.string.result_submit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insert();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (take_answer.equals("ok")) {
                                        Toast.makeText(getApplicationContext(), R.string.result_succeesed, Toast.LENGTH_LONG).show();
                                        finish();
                                    } else if (take_answer.equals(null)) {
                                        ResultDialog(5);
                                    }
                                }
                            }, 500);


                            Result_edit.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.result_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

        } else if (num == 6) {
            caution.setTitle(R.string.result_error);
            caution.setMessage(R.string.result_no_communication).setCancelable(false)
                    .setPositiveButton(R.string.result_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        AlertDialog Caution = caution.create();
        Caution.show();
    }
}


