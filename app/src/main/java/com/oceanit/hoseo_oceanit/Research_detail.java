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
    TextView research_nametxt, business_nametxt, departmenttxt, support_organizationtxt, subjectivity_txt, research_chargetxt, organizationstxt, research_charge_belongtxt, datetxt,
            researchprogresstxt, research_purposetxt, research_contentstxt, research_expectedtxt;
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



        research_nametxt = (TextView)findViewById(R.id.research_nametxt);
        business_nametxt = (TextView)findViewById(R.id.business_nametxt);
        departmenttxt = (TextView)findViewById(R.id.departmenttxt);
        support_organizationtxt = (TextView)findViewById(R.id.support_organizationtxt);
        subjectivity_txt = (TextView)findViewById(R.id.subjectivity_txt);
        research_chargetxt = (TextView)findViewById(R.id.research_chargetxt);
        organizationstxt = (TextView)findViewById(R.id.organizationstxt);
        research_charge_belongtxt = (TextView)findViewById(R.id.research_charge_belongtxt);
        datetxt = (TextView)findViewById(R.id.datetxt);
        researchprogresstxt = (TextView)findViewById(R.id.researchprogresstxt);
        research_purposetxt = (TextView)findViewById(R.id.research_purposetxt);
        research_contentstxt = (TextView)findViewById(R.id.research_contentstxt);
        research_expectedtxt = (TextView)findViewById(R.id.research_expectedtxt);


        departmenttxt.setSelected(true);
        support_organizationtxt.setSelected(true);
        subjectivity_txt.setSelected(true);
        research_chargetxt.setSelected(true);
        organizationstxt.setSelected(true);
        research_charge_belongtxt.setSelected(true);
        datetxt.setSelected(true);
        researchprogresstxt.setSelected(true);

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
                        String detail_business_name = c.getString(TAG_BUSINESS_NAME_KO);
                        String detail_department_name = c.getString(TAG_DEPARTMENT_NAME_KO);
                        String detail_subjectivity_organization = c.getString(TAG_SUBJECTIVITY_ORGANIZATION_KO);
                        String detail_support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_KO);
                        String detail_participation_organization = c.getString(TAG_PARTICIPATION_ORGANIZATION_KO);
                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_KO);
                        String detail_research_contents = c.getString(TAG_RESEARCH_CONTENTS_KO);
                        String detail_research_expected = c.getString(TAG_RESEARCH_EXPECTED_KO);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);
                        String detail_research_charge = c.getString(TAG_RESEARCH_CHARGE_KO);
                        String detail_research_charge_belong = c.getString(TAG_RESEARCH_CHARGE_BELONG_KO);
                        String detail_field_picture = c.getString(TAG_FIELD_PICTURE);
                        String detail_progress = TAG_PROGRESS_KO;
                        String detail_finish = TAG_FINISH_KO;
                        long Time = GetTime(detail_date_end);
                        research_nametxt.setText(detail_research_name);
                        business_nametxt.setText(detail_business_name);
                        departmenttxt.setText(detail_department_name);
                        subjectivity_txt.setText(detail_subjectivity_organization);
                        support_organizationtxt .setText(detail_support_organization);
                        organizationstxt .setText(detail_participation_organization);
                        research_chargetxt.setText(detail_research_charge);
                        research_charge_belongtxt .setText(detail_research_charge_belong);
                        research_purposetxt.setText(detail_research_purpose);
                        research_contentstxt.setText(detail_research_contents);
                        research_expectedtxt.setText(detail_research_expected);
                        datetxt.setText(detail_date_start+" ~ "+detail_date_end);
                        if(Time>0)
                        {
                            researchprogresstxt.setText(detail_progress);
                        }
                        else
                        {
                            researchprogresstxt.setText(detail_finish);
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
                        String detail_business_name = c.getString(TAG_BUSINESS_NAME_EN);
                        String detail_department_name = c.getString(TAG_DEPARTMENT_NAME_EN);
                        String detail_subjectivity_organization = c.getString(TAG_SUBJECTIVITY_ORGANIZATION_EN);
                        String detail_support_organization = c.getString(TAG_SUPPORT_ORGANIZATION_EN);
                        String detail_participation_organization = c.getString(TAG_PARTICIPATION_ORGANIZATION_EN);
                        String detail_research_purpose = c.getString(TAG_RESEARCH_PURPOSE_EN);
                        String detail_research_contents = c.getString(TAG_RESEARCH_CONTENTS_EN);
                        String detail_research_expected = c.getString(TAG_RESEARCH_EXPECTED_EN);
                        String detail_date_start = c.getString(TAG_DATE_START);
                        String detail_date_end = c.getString(TAG_DATE_END);
                        String detail_research_charge = c.getString(TAG_RESEARCH_CHARGE_EN);
                        String detail_research_charge_belong = c.getString(TAG_RESEARCH_CHARGE_BELONG_EN);
                        String detail_field_picture = c.getString(TAG_FIELD_PICTURE);
                        String detail_progress = TAG_PROGRESS_EN;
                        String detail_finish = TAG_FINISH_EN;

                        long Time = GetTime(detail_date_end);
                        research_nametxt.setText(detail_research_name);
                        business_nametxt.setText(detail_business_name);
                        departmenttxt.setText(detail_department_name);
                        subjectivity_txt.setText(detail_subjectivity_organization);
                        support_organizationtxt .setText(detail_support_organization);
                        organizationstxt .setText(detail_participation_organization);
                        research_chargetxt.setText(detail_research_charge);
                        research_charge_belongtxt .setText(detail_research_charge_belong);
                        research_purposetxt.setText(detail_research_purpose);
                        research_contentstxt.setText(detail_research_contents);
                        research_expectedtxt.setText(detail_research_expected);
                        datetxt.setText(detail_date_start+" ~ "+detail_date_end);
                        if(Time>0)
                        {
                            researchprogresstxt.setText(detail_progress);
                        }
                        else
                        {
                            researchprogresstxt.setText(detail_finish);
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
