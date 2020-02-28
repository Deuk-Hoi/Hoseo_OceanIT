package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Research_detail extends AppCompatActivity {

    private static final String TAG_RID = "rid";
    private static final String TAG_RESEARCH_NAME_KO = "research_name_ko";
    private static final String TAG_RESEARCH_NAME_EN = "research_name_en";
    private static final String TAG_SUPPORT_ORGANIZATION_KO = "support_organization_ko";
    private static final String TAG_SUPPORT_ORGANIZATION_EN = "support_organization_en";
    private static final String TAG_RESEARCH_PURPOSE_KO = "research_purpose_ko";
    private static final String TAG_RESEARCH_PURPOSE_EN = "research_purpose_en";
    private static final String TAG_RESEARCH_CONTENTS_KO = "research_contents_ko";
    private static final String TAG_RESEARCH_CONTENTS_EN = "research_contents_en";
    private static final String TAG_RESEARCH_EXPECTED_KO = "research_expected_ko";
    private static final String TAG_RESEARCH_EXPECTED_EN = "research_expected_en";
    private static final String TAG_RESEARCH_DIRECTOR_KO = "research_charge_ko";
    private static final String TAG_RESEARCH_DIRECTOR_EN = "research_charge_en";
    private static final String TAG_DATE_START = "date_start";
    private static final String TAG_DATE_END = "date_end";
    private static final String TAG_CLASSIFICATION_KO = "classification_ko";
    private static final String TAG_CLASSIFICATION_EN = "classification_en";
    private static final String TAG_RESEARCH_CHARGE_BELONG_KO = "research_charge_belong_ko";
    private static final String TAG_RESEARCH_CHARGE_BELONG_EN = "research_charge_belong_en";
    private static final String TAG_PROGRESS_KO = "연구진행중";
    private static final String TAG_PROGRESS_EN = "Researching";
    private static final String TAG_FINISH_KO = "연구완료";
    private static final String TAG_FINISH_EN = "Finish";
    TextView title_name, main_organization, support_organization, research_director_position_txt, research_director_name, date_start, date_end, research_state_txt, classification_txt, research_goal_txt,research_contents_txt, research_result_txt;
    String intent_rid, JSON;
    Tools tools = new Tools();
    String language = tools.language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_detail);
        Intent research_intent = this.getIntent();
        intent_rid = research_intent.getStringExtra("rid");
        JSON =research_intent.getStringExtra("researchJSON");


        title_name = (TextView)findViewById(R.id.title_name);
        main_organization = (TextView)findViewById(R.id.main_organization);
        support_organization = (TextView)findViewById(R.id.support_organization);
        research_director_position_txt = (TextView)findViewById(R.id.research_director_position_txt);
        research_director_name = (TextView)findViewById(R.id.research_director_name);
        date_start = (TextView)findViewById(R.id.date_start);
        date_end = (TextView)findViewById(R.id.date_end);
        research_state_txt = (TextView)findViewById(R.id.research_state_txt);
        classification_txt = (TextView)findViewById(R.id.classification_txt);
        research_goal_txt = (TextView)findViewById(R.id.research_goal_txt);
        research_contents_txt = (TextView)findViewById(R.id.research_contents_txt);
        research_result_txt = (TextView)findViewById(R.id.research_result_txt);
        research_state_txt = (TextView)findViewById(R.id.research_state_txt);
        showdetail();

    }
    protected void showdetail()
    {
        if(language.equals("ko")) {
            try {
                JSONArray jsonArray = new JSONArray(JSON);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String rid = c.getString(TAG_RID);

                    if(rid.equals(intent_rid)) {

                        String detail_research_name = c.getString(TAG_RESEARCH_NAME_KO);
                        String detail_support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_KO);
                        String detail_research_director_position = c.getString(TAG_RESEARCH_CHARGE_BELONG_KO);
                        String detail_research_charge = c.getString(TAG_RESEARCH_DIRECTOR_KO);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);
                        String detail_progress = TAG_PROGRESS_KO;
                        String detail_finish = TAG_FINISH_KO;
                        String detail_classification = c.getString(TAG_CLASSIFICATION_KO);
                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_KO);
                        String detail_research_contents = c.getString(TAG_RESEARCH_CONTENTS_KO);
                        String detail_research_expected = c.getString(TAG_RESEARCH_EXPECTED_KO);
                        long Time = GetTime(detail_date_end);
                        title_name.setText(detail_research_name);
                        main_organization.setText("해양IT 연구소");
                        support_organization.setText(detail_support_organization);
                        research_director_position_txt.setText(detail_research_director_position);
                        research_director_name.setText(detail_research_charge);
                        date_start.setText(detail_date_start);
                        date_end.setText(detail_date_end);
                        classification_txt.setText(detail_classification);
                        research_goal_txt.setText(detail_research_purpose);
                        research_contents_txt.setText(detail_research_contents);
                        research_result_txt.setText(detail_research_expected);
                        if(Time>0)
                        {
                            research_state_txt.setText(detail_progress);
                        }
                        else
                        {
                            research_state_txt.setText(detail_finish);
                        }

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
                    String rid = c.getString(TAG_RID);
                    Log.e("ssdsasdasdasdsadas",rid);
                    if(rid.equals(intent_rid)) {

                        String detail_research_name = c.getString(TAG_RESEARCH_NAME_EN);
                        String detail_support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_EN);
                        String detail_research_director_position = c.getString(TAG_RESEARCH_CHARGE_BELONG_EN);
                        String detail_research_charge = c.getString(TAG_RESEARCH_DIRECTOR_EN);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);
                        String detail_progress = TAG_PROGRESS_EN;
                        String detail_finish = TAG_FINISH_EN;
                        String detail_classification = c.getString(TAG_CLASSIFICATION_EN);
                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_EN);
                        String detail_research_contents = c.getString(TAG_RESEARCH_CONTENTS_EN);
                        String detail_research_expected = c.getString(TAG_RESEARCH_EXPECTED_EN);
                        long Time = GetTime(detail_date_end);
                        main_organization.setText("Ocean-it");
                        title_name.setText(detail_research_name);
                        support_organization.setText(detail_support_organization);
                        research_director_position_txt.setText(detail_research_director_position);
                        research_director_name.setText(detail_research_charge);
                        date_start.setText(detail_date_start);
                        date_end.setText(detail_date_end);
                        classification_txt.setText(detail_classification);
                        research_goal_txt.setText(detail_research_purpose);
                        research_contents_txt.setText(detail_research_contents);
                        research_result_txt.setText(detail_research_expected);
                        if(Time>0)
                        {
                            research_state_txt.setText(detail_progress);
                        }
                        else
                        {
                            research_state_txt.setText(detail_finish);
                        }

                    }

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
}
