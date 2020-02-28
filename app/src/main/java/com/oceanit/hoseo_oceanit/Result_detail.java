package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Result_detail extends AppCompatActivity {

    private static final String TAG_RRID = "rrid";
    private static final String TAG_CLASSIFICATION_KO = "classification_ko";
    private static final String TAG_CLASSIFICATION_EN = "classification_en";
    private static final String TAG_RESULT_NAME_KO = "result_name_ko";
    private static final String TAG_RESULT_NAME_EN = "result_name_en";
    private static final String TAG_ACADEMIC_JOURNAL_KO = "academic_journal_ko";
    private static final String TAG_ACADEMIC_JOURNAL_EN = "academic_journal_en";
    private static final String TAG_WRITERS_KO = "writers_ko";
    private static final String TAG_WRITERS_EN = "writers_en";
    private static final String TAG_ANNOUNCE_MEDIA_KO = "announce_media_ko";
    private static final String TAG_ANNOUNCE_MEDIA_EN = "announce_media_en";
    private static final String TAG_COUNTRY_KO = "country_ko";
    private static final String TAG_COUNTRY_EN = "country_en";
    private static final String TAG_RELATION_TASK_KO = "relation_task_ko";
    private static final String TAG_RELATION_TASK_EN = "relation_task_en";
    private static final String TAG_ABSTRACT_KO = "abstract_ko";
    private static final String TAG_ABSTRACT_EN = "abstract_en";
    private static final String TAG_P_DATE = "p_date";

    TextView title_name, main_organization, academic_journal_txt, p_date_txt, country, announce_media_txt, classification_txt, writers_txt, relation_task_txt, abstracttxt;
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
        main_organization = (TextView)findViewById(R.id.main_organization);
        academic_journal_txt = (TextView)findViewById(R.id.academic_journal_txt);
        p_date_txt = (TextView)findViewById(R.id.p_date_txt);
        country = (TextView)findViewById(R.id.country);
        announce_media_txt = (TextView)findViewById(R.id.announce_media_txt);
        classification_txt = (TextView)findViewById(R.id.classification_txt);
        writers_txt = (TextView)findViewById(R.id.writers_txt);
        relation_task_txt = (TextView)findViewById(R.id.relation_task_txt);
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
                        String detail_country = c.getString(TAG_COUNTRY_KO);
                        String detail_announce_media = c.getString(TAG_ANNOUNCE_MEDIA_KO);
                        String detail_classification = c.getString(TAG_CLASSIFICATION_KO);
                        String detail_writers = c.getString(TAG_WRITERS_KO);
                        String detail_relation_task = c.getString(TAG_RELATION_TASK_KO);
                        String detail_abstract = c.getString(TAG_ABSTRACT_KO);

                        title_name.setText(detail_title_name);
                        main_organization.setText("해양IT 연구소");
                        academic_journal_txt.setText(detail_academic_journal);
                        p_date_txt.setText(detail_p_date);
                        country.setText(detail_country);
                        announce_media_txt.setText(detail_announce_media);
                        classification_txt.setText(detail_classification);
                        writers_txt.setText(detail_writers);
                        relation_task_txt.setText(detail_relation_task);
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
                        String detail_country = c.getString(TAG_COUNTRY_EN);
                        String detail_announce_media = c.getString(TAG_ANNOUNCE_MEDIA_EN);
                        String detail_classification = c.getString(TAG_CLASSIFICATION_EN);
                        String detail_writers = c.getString(TAG_WRITERS_EN);
                        String detail_relation_task = c.getString(TAG_RELATION_TASK_EN);
                        String detail_abstract = c.getString(TAG_ABSTRACT_EN);
                        title_name.setText(detail_title_name);
                        main_organization.setText("Ocean_IT");
                        academic_journal_txt.setText(detail_academic_journal);
                        p_date_txt.setText(detail_p_date);
                        country.setText(detail_country);
                        announce_media_txt.setText(detail_announce_media);
                        classification_txt.setText(detail_classification);
                        writers_txt.setText(detail_writers);
                        relation_task_txt.setText(detail_relation_task);
                        abstracttxt.setText(detail_abstract);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
