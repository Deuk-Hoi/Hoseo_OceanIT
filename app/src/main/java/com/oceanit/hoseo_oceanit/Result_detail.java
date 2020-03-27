package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Result_detail extends AppCompatActivity {

    private static final String TAG_RRID = "rrid";
    private static final String TAG_RESULT_NAME_KO = "result_name_ko";
    private static final String TAG_RESULT_NAME_EN = "result_name_en";
    private static final String TAG_ACADEMIC_JOURNAL_KO = "academic_journal_ko";
    private static final String TAG_ACADEMIC_JOURNAL_EN = "academic_journal_en";
    private static final String TAG_WRITERS_KO = "writers_ko";
    private static final String TAG_WRITERS_EN = "writers_en";
    private static final String TAG_ABSTRACT_KO = "abstract_ko";
    private static final String TAG_ABSTRACT_EN = "abstract_en";
    private static final String TAG_P_DATE = "p_date";

    TextView title_name, academic_journal_txt, p_date_txt, writers_txt, abstracttxt;
    String intent_rrid, JSON;
    Tools tools = new Tools();
    String language = tools.language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        Intent result_intent = this.getIntent();
        intent_rrid = result_intent.getStringExtra("rrid");
        JSON = result_intent.getStringExtra("resultJSON");
        Log.e("JSON",JSON);
        title_name = (TextView)findViewById(R.id.title_name);
        academic_journal_txt = (TextView)findViewById(R.id.academic_journal_txt);
        p_date_txt = (TextView)findViewById(R.id.p_date_txt);
        writers_txt = (TextView)findViewById(R.id.writers_txt);
        abstracttxt = (TextView)findViewById(R.id.abstracttxt);
        show_result_detail();

    }

    protected void show_result_detail()
    {
        if(language.equals("ko")) {
            try {
                JSONArray jsonArray = new JSONArray(JSON);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rid = c.getString(TAG_RRID);

                    if(rid.equals(intent_rrid)) {

                        String detail_title_name = c.getString(TAG_RESULT_NAME_KO);
                        String detail_academic_journal = c.getString(TAG_ACADEMIC_JOURNAL_KO);
                        String detail_p_date = c.getString(TAG_P_DATE);
                        String detail_writers = c.getString(TAG_WRITERS_KO);
                        String detail_abstract = c.getString(TAG_ABSTRACT_KO);

                        title_name.setText(detail_title_name);
                        academic_journal_txt.setText(detail_academic_journal);
                        p_date_txt.setText(detail_p_date);
                        writers_txt.setText(detail_writers);
                        abstracttxt.setText(detail_abstract);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                JSONArray jsonArray = new JSONArray(JSON);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rid = c.getString(TAG_RRID);

                    if(rid.equals(intent_rrid)) {

                        String detail_title_name = c.getString(TAG_RESULT_NAME_EN);
                        String detail_academic_journal = c.getString(TAG_ACADEMIC_JOURNAL_EN);
                        String detail_p_date = c.getString(TAG_P_DATE);
                        String detail_writers = c.getString(TAG_WRITERS_EN);
                        String detail_abstract = c.getString(TAG_ABSTRACT_EN);
                        title_name.setText(detail_title_name);
                        academic_journal_txt.setText(detail_academic_journal);
                        p_date_txt.setText(detail_p_date);
                        writers_txt.setText(detail_writers);
                        abstracttxt.setText(detail_abstract);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
