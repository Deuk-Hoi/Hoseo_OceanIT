package com.oceanit.hoseo_oceanit;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Member_info extends AppCompatActivity {

    TextView name,position,call,email,bachelor_school,bachelor_major,bachelor_degree
            ,master_school,master_major,master_degree,doctor_school,doctor_major,doctor_degree;
    ImageView member_image2;
    String getintent[]=new String[14];
    Button callbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);

        Intent intent = getIntent();

        member_image2 = (ImageView)findViewById(R.id.member_image2);
        getintent[0] = intent.getExtras().getString("member_image2");
        String packname = this.getPackageName();
        int resID = getResources().getIdentifier(getintent[0],"drawable",packname);
        member_image2.setImageResource(resID);

        name = (TextView)findViewById(R.id.name);
        getintent[1] = intent.getExtras().getString("name");
        name.setText(getintent[1]);

        position = (TextView)findViewById(R.id.position);
        getintent[2] = intent.getExtras().getString("position");
        position.setText(getintent[2]);

        call = (TextView)findViewById(R.id.call);
        getintent[3] = intent.getExtras().getString("call");
        call.setText(getintent[3]);

        email = (TextView)findViewById(R.id.email);
        getintent[4] = intent.getExtras().getString("email");
        email.setText(getintent[4]);

        bachelor_school = (TextView)findViewById(R.id.bachelor_school);
        getintent[5] = intent.getExtras().getString("bachelor_school");
        bachelor_school.setText(getintent[5]);

        bachelor_major = (TextView)findViewById(R.id.bachelor_major);
        getintent[6] = intent.getExtras().getString("bachelor_major");
        bachelor_major.setText(getintent[6]);

        bachelor_degree = (TextView)findViewById(R.id.bachelor_degree);
        getintent[7] = intent.getExtras().getString("bachelor_degree");
        bachelor_degree.setText(getintent[7]);

        master_school = (TextView)findViewById(R.id.master_school);
        getintent[8] = intent.getExtras().getString("master_school");
        master_school.setText(getintent[8]);

        master_major = (TextView)findViewById(R.id.master_major);
        getintent[9] = intent.getExtras().getString("master_major");
        master_major.setText(getintent[9]);

        master_degree = (TextView)findViewById(R.id.master_degree);
        getintent[10] = intent.getExtras().getString("master_degree");
        master_degree.setText(getintent[10]);

        doctor_school = (TextView)findViewById(R.id.doctor_school);
        getintent[11] = intent.getExtras().getString("doctor_school");
        doctor_school.setText(getintent[11]);

        doctor_major = (TextView)findViewById(R.id.doctor_major);
        getintent[12] = intent.getExtras().getString("doctor_major");
        doctor_major.setText(getintent[12]);

        doctor_degree = (TextView)findViewById(R.id.doctor_degree);
        getintent[13] = intent.getExtras().getString("doctor_degree");
        doctor_degree.setText(getintent[13]);

        callbtn = (Button)findViewById(R.id.callbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getintent[3]));
                startActivity(callbtn);
            }
        });
    }
}
