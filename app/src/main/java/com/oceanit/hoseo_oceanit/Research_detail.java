package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
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
    private static final String TAG_BUSINESS_NAME_KO = "business_name_ko";
    private static final String TAG_BUSINESS_NAME_EN = "business_name_en";
    private static final String TAG_DEPARTMENT_NAME_KO = "department_name_ko";
    private static final String TAG_DEPARTMENT_NAME_EN = "department_name_en";
    private static final String TAG_SUBJECTIVITY_ORGANIZATION_KO = "subjectivity_organization_ko";
    private static final String TAG_SUBJECTIVITY_ORGANIZATION_EN = "subjectivity_organization_en";
    private static final String TAG_SUPPORT_ORGANIZATION_KO = "support_organization_ko";
    private static final String TAG_SUPPORT_ORGANIZATION_EN = "support_organization_en";
    private static final String TAG_PARTICIPATION_ORGANIZATION_KO = "participation_organization_ko";
    private static final String TAG_PARTICIPATION_ORGANIZATION_EN = "participation_organization_en";
    private static final String TAG_RESEARCH_PURPOSE_KO = "research_purpose_ko";
    private static final String TAG_RESEARCH_PURPOSE_EN = "research_purpose_en";
    private static final String TAG_RESEARCH_CONTENTS_KO = "research_contents_ko";
    private static final String TAG_RESEARCH_CONTENTS_EN = "research_contents_en";
    private static final String TAG_RESEARCH_EXPECTED_KO = "research_expected_ko";
    private static final String TAG_RESEARCH_EXPECTED_EN = "research_expected_en";
    private static final String TAG_DATE_START = "date_start";
    private static final String TAG_DATE_END = "date_end";
    private static final String TAG_RESEARCH_CHARGE_KO = "research_charge_ko";
    private static final String TAG_RESEARCH_CHARGE_EN = "research_charge_en";
    private static final String TAG_RESEARCH_CHARGE_BELONG_KO = "research_charge_belong_ko";
    private static final String TAG_RESEARCH_CHARGE_BELONG_EN = "research_charge_belong_en";
    private static final String TAG_FIELD_PICTURE = "field_picture";
    private static final String TAG_PROGRESS_KO = "연구진행중";
    private static final String TAG_PROGRESS_EN = "Researching";
    private static final String TAG_FINISH_KO = "연구완료";
    private static final String TAG_FINISH_EN = "Finish";
    TextView research_datetxt, research_supporttxt, research_purposetxt;
    String intent_rid, JSON;
    Tools tools = new Tools();
    String language = tools.language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_research_detail);
        Intent research_intent = this.getIntent();
        intent_rid = research_intent.getStringExtra("rid");
        JSON =research_intent.getStringExtra("researchJSON");


        research_datetxt = (TextView)findViewById(R.id.research_datetxt);
        research_supporttxt = (TextView)findViewById(R.id.research_supporttxt);
        research_purposetxt = (TextView)findViewById(R.id.research_purposetxt);

        research_purposetxt.setSelected(true);

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

                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_KO);
                        String detail_research_supporttxt = c.getString(TAG_SUPPORT_ORGANIZATION_KO);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);

                        research_purposetxt.setText(detail_research_purpose);
                        research_supporttxt.setText(detail_research_supporttxt);
                        research_datetxt.setText(detail_date_start+" ~ "+detail_date_end);

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

                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_EN);
                        String detail_research_supporttxt = c.getString(TAG_SUPPORT_ORGANIZATION_EN);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);

                        research_purposetxt.setText(detail_research_purpose);
                        research_supporttxt.setText(detail_research_supporttxt);
                        research_datetxt.setText(detail_date_start+" ~ "+detail_date_end);

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
