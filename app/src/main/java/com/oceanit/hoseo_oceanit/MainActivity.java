package com.oceanit.hoseo_oceanit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Button.OnClickListener{

    final Tools tools = new Tools();
    String laguage = tools.language;
    public static Context CONTEXT;
    TextView username;
    Button loginbtn,location,pcbtn,active,archive,logoutbtn, data_btn;
    ImageButton introduce_icon, member_icon, question_icon, research_icon;
    //hello test commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //clearAlldata(); // 전체 삭제할때 사용 현재는 사용 안할 계획
        load();

        if(tools.checkbox==true)
        {
            exitcleardata();
        }
        else {
            clearAlldata();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username = (TextView)findViewById(R.id.usernametxt);
        CONTEXT = this;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        loginbtn = (Button)findViewById(R.id.loginbtn);
        logoutbtn = (Button)findViewById(R.id.logoutbtn);
        location = (Button)findViewById(R.id.location);
        pcbtn = (Button)findViewById(R.id.pcbtn);
        archive = (Button)findViewById(R.id.archive);
        active = (Button)findViewById(R.id.active);
        data_btn = (Button)findViewById(R.id.data_btn);
        introduce_icon = (ImageButton)findViewById(R.id.introduce_icon);
        member_icon = (ImageButton)findViewById(R.id.member_icon);
        question_icon = (ImageButton)findViewById(R.id.question_icon);
        research_icon = (ImageButton)findViewById(R.id.research_icon);


        introduce_icon.setOnClickListener(this);
        member_icon.setOnClickListener(this);
        research_icon.setOnClickListener(this);
        question_icon.setOnClickListener(this);
        active.setOnClickListener(this);
        archive.setOnClickListener(this);
        location.setOnClickListener(this);
        data_btn.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
        logoutbtn.setOnClickListener(this);
        pcbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.introduce_icon:
                Intent introduce = new Intent(MainActivity.this,Introduce.class);
                startActivity(introduce);
                break;

            case R.id.member_icon:
                Intent member = new Intent(MainActivity.this,Member.class);
                startActivity(member);
                break;

            case R.id.research_icon:
                Intent research_icon = new Intent(MainActivity.this,Research_result.class);
                startActivity(research_icon);
                break;

            case R.id.question_icon:
                Intent question = new Intent(MainActivity.this,Question.class);
                startActivity(question);
                break;

            case R.id.active:
                Intent result = new Intent(MainActivity.this,ResearchActive.class);
                startActivity(result);
                break;

            case R.id.archive:
                //Intent research = new Intent(MainActivity.this,ResearchField.class); //추후 수정
                //startActivity(research);
                break;

            case R.id.location:
                Intent location = new Intent(MainActivity.this,Location.class);
                startActivity(location);
                break;

            case R.id.data_btn:
                Intent data_btn = new Intent(MainActivity.this, Oceandata_hompage.class);
                startActivity(data_btn);
                break;

            case R.id.loginbtn:
                Intent login = new Intent(MainActivity.this,Login.class);
                startActivity(login);
                break;

            case R.id.logoutbtn:
                LogoutDialog(v);
                break;

            case R.id.pcbtn:
                Intent pcbtn = new Intent(MainActivity.this,PCwebsite.class);
                startActivity(pcbtn);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Dialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_privacy) {
            Toast.makeText(getApplicationContext(),"sdf",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.introduce_tools) {
            Intent introduce = new Intent(MainActivity.this,Introduce.class);
            startActivity(introduce);

        } else if (id == R.id.member_tools) {
            Intent member = new Intent(MainActivity.this,Member.class);
            startActivity(member);
        } else if (id == R.id.active_tools) {
            Intent research_fields = new Intent(MainActivity.this,ResearchActive.class);
            startActivity(research_fields);
        } else if (id == R.id.archive_tools) {
            /*Intent research_result = new Intent(MainActivity.this,Research_result.class);
            startActivity(research_result);*/
        } else if (id == R.id.question_tools) {
            Intent question = new Intent(MainActivity.this,Question.class);
            startActivity(question);
        } else if (id == R.id.location_tools) {
            Intent location = new Intent(MainActivity.this,Location.class);
            startActivity(location);
        } else if (id == R.id.pcwed_tools) {
            Intent pcweb = new Intent(MainActivity.this,PCwebsite.class);
            startActivity(pcweb);
        } else if (id == R.id.Ocean_data) {
            Intent school = new Intent(MainActivity.this, Oceandata_hompage.class);
            startActivity(school);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
        if(tools.loginok==true) {
            loginbtn.setVisibility(View.GONE);
            logoutbtn.setVisibility(View.VISIBLE);

            if(laguage.equals("ko"))
            {

               username.setText(tools.username +" 님 환영합니다.");
            }
            else
            {
                username.setText(tools.username +", welcome");
            }


        }
        else
        {
            loginbtn.setVisibility(View.VISIBLE);
            logoutbtn.setVisibility(View.GONE);
            if(laguage.equals("ko"))
            {
                username.setText("방문자님 환영합니다.");
            }
            else
            {
                username.setText("Welcome guest.");
            }
        }
    }

    private void clearAlldata()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTF.edit();
        editor.clear();
        editor.commit();
    }

    private void exitcleardata()
    {
        SharedPreferences loginTF = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTF.edit();
        editor.remove("Save_login_data");
        editor.remove("PASS");
        editor.remove("NAME");
        editor.remove("ADMIN");
        editor.commit();
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


    public void Dialog() //경고 알림창 띄우기
    {
        Log.e("test",tools.checkbox.toString());
        AlertDialog.Builder caution = new AlertDialog.Builder(MainActivity.this);
            caution.setTitle(R.string.app_exit);
            caution.setMessage(R.string.Dialog).setCancelable(false)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(tools.checkbox==true)
                            {
                                Log.e("test2",tools.checkbox.toString());
                                exitcleardata();
                            }
                            else {
                                clearAlldata();
                            }
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        AlertDialog Caution = caution.create();
        Caution.show();
    }
    public void LogoutDialog(final View v)
    {
        AlertDialog.Builder caution = new AlertDialog.Builder(MainActivity.this);
        caution.setTitle(R.string.logout_exit);
        caution.setMessage(R.string.logoutDialog).setCancelable(false)
                .setPositiveButton(R.string.logoutyes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(tools.checkbox==true)
                        {
                            exitcleardata();
                        }
                        else {
                            clearAlldata();
                        }
                        tools.loginok = false;
                        ((MainActivity)MainActivity.CONTEXT).onResume();
                        Snackbar.make(v,R.string.logout,Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog Caution = caution.create();
        Caution.show();
    }

}
