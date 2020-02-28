package com.oceanit.hoseo_oceanit;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CreateAccount extends AppCompatActivity {

    EditText create_phonenumedit, username_edit,passwordredit, password_same_cheke_edit, create_nameedit, create_email_edit, adress_edit;
    Button duplicate_cheke, create_account_btn;
    String searchID, isexist, saveid,create_ok;
    TextView duplicate_cheke_answer,password_cheke_answer;
    ImageView chekeimg;
    Boolean duplicheke = false, passcheke = false;
    ProgressDialog progressdialog;
    Tools tools = new Tools();
    String language = tools.language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        progressdialog = new ProgressDialog(CreateAccount.this);

        duplicate_cheke = (Button)findViewById(R.id.duplicate_cheke);
        username_edit = (EditText)findViewById(R.id.username_edit);
        duplicate_cheke_answer = (TextView)findViewById(R.id.duplicate_cheke_answer);

        passwordredit = (EditText)findViewById(R.id.passwordredit);
        password_same_cheke_edit = (EditText)findViewById(R.id.password_same_cheke_edit);
        password_cheke_answer = (TextView) findViewById(R.id.password_cheke_answer);
        chekeimg = (ImageView)findViewById(R.id.chekeimg);

        create_phonenumedit = (EditText)findViewById(R.id.create_phonenumedit);
        create_phonenumedit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        create_nameedit = (EditText)findViewById(R.id.create_nameedit);
        create_email_edit = (EditText)findViewById(R.id.create_email_edit);
        adress_edit = (EditText)findViewById(R.id.adress_edit);

        create_account_btn = (Button)findViewById(R.id.create_account_btn);
        create_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((username_edit.getText().toString().equals(""))||(passwordredit.getText().toString().equals(""))||(create_nameedit.getText().toString().equals("")))
                {
                    createAccountDialog(v,1);
                }
                else if(duplicheke!=true)
                {
                    createAccountDialog(v,2);
                }
                else if((duplicheke==true)&&(passcheke==true))
                {
                    if(saveid.equals(username_edit.getText().toString()))
                    {
                        createAccountDialog(v,3);
                    }
                    else
                    {
                        createAccountDialog(v,4);
                    }
                }
            }
        });


        password_same_cheke_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (passwordredit.getText().toString().equals(password_same_cheke_edit.getText().toString())) {
                    password_cheke_answer.setText(R.string.password_match);
                    password_cheke_answer.setTextColor(Color.GREEN);
                    chekeimg.setImageResource(R.drawable.ok);
                    passcheke = true;
                } else {
                    password_cheke_answer.setText(R.string.password_not_match);
                    password_cheke_answer.setTextColor(Color.RED);
                    chekeimg.setImageResource(R.drawable.no);
                    passcheke = false;
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        username_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(username_edit.equals(saveid)))
                {
                    duplicate_cheke_answer.setText(R.string.please_duplicate_cheke);
                    duplicate_cheke_answer.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        duplicate_cheke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                searchID = username_edit.getText().toString();
                search_exist_id(searchID);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(searchID.equals(""))
                        {
                            Duplicate_cheke_Dialog(v,1);
                            duplicate_cheke_answer.setText(R.string.not_available_id);
                            duplicate_cheke_answer.setTextColor(Color.RED);
                        }
                        else if(isexist.equals("undefined"))
                        {
                            duplicate_cheke_answer.setText(R.string.available_id);
                            duplicate_cheke_answer.setTextColor(Color.GREEN);
                            Duplicate_cheke_Dialog(v,2);
                        }
                        else if(isexist.equals("exist"))
                        {
                            duplicate_cheke_answer.setText(R.string.duplicate_id);
                            duplicate_cheke_answer.setTextColor(Color.RED);
                            Duplicate_cheke_Dialog(v,3);
                        }
                    }
                },500);
            }
        });


    }

    private void search_exist_id(final String searchID) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";

            @Override
            protected String doInBackground(String... strings) {

                String searchID  = (String) strings[0];

                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("uid", searchID);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/login/isexist_id");
                        //URL url = new URL("http://192.168.35.250:9928/login/isexist_id");
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
                Log.e("get",s);
                isexist = s;
            }
        }
        InsertData task = new InsertData();
        task.execute(searchID);

    }

    public void insert_account() {
        String id = username_edit.getText().toString();
        String password = passwordredit.getText().toString();
        String name = create_nameedit.getText().toString();
        String phonenum = create_phonenumedit.getText().toString();
        String email = create_email_edit.getText().toString();
        String address = adress_edit.getText().toString();

        insertToDatabase(id, password, name, phonenum, email, address);
    }

    private void insertToDatabase(final String id, String password, String name, String phonenum, String email, String address) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            String result ="";


            @Override
            protected void onPreExecute() {
                progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                if(language.equals("ko")) {
                    progressdialog.setMessage("회원가입 처리중");
                }
                else
                {
                    progressdialog.setMessage("Processing account registration...");
                }
                progressdialog.show();
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {

                String id = (String) strings[0];
                String password = (String) strings[1];
                String name = (String) strings[2];
                String phonenum = (String) strings[3];
                String email = (String) strings[4];
                String address = (String) strings[5];


                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("id", id);
                    jsonObject.accumulate("password", password);
                    jsonObject.accumulate("name", name);
                    jsonObject.accumulate("phonenum", phonenum);
                    jsonObject.accumulate("email", email);
                    jsonObject.accumulate("address", address);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        //URL url = new URL("http://192.168.25.16:3000/users");
                        URL url = new URL(tools.URL+"/login/create_account");
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
                Log.e("get",s);
                create_ok = s;

            }
        }
        InsertData task = new InsertData();
        task.execute(id, password, name, phonenum, email, address);

    }


    public void Duplicate_cheke_Dialog(final View v,int i)
    {
        AlertDialog.Builder caution = new AlertDialog.Builder(CreateAccount.this);
        if(i==1)
        {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.not_available_id).setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            username_edit.setText(null);
                        }
                    });
        }
        else if(i==2) {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.available_id_do_you_use).setCancelable(false)
                    .setPositiveButton(R.string.use, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveid = searchID;  //마지막에 사용할 아이디와 마지막까지 입력된아이디 비교할때 사용
                            duplicheke = true;
                            InputMethodManager seekeyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE); // 키보드 숨기기 기능
                            seekeyboard.hideSoftInputFromWindow(username_edit.getWindowToken(), 0);
                            Snackbar.make(v, R.string.will_use, Snackbar.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            username_edit.setText(null);
                            duplicheke = false;
                            duplicate_cheke_answer.setText("");
                            dialog.cancel();
                        }
                    });
        }
        else if(i==3)
        {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.duplicate_id).setCancelable(false)
                   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            username_edit.setText(null);
                        }
                    });
        }
        AlertDialog Caution = caution.create();
        Caution.show();

    }

    public void createAccountDialog(final View v,int num)
    {
        AlertDialog.Builder caution = new AlertDialog.Builder(CreateAccount.this);
        if(num==1)
         {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.please_all_insert).setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
         }
        else if(num==2)
        {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.check_duplicate_id).setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
        else if(num==3)
        {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.do_you_want_signup).setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insert_account();
                            new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 if(create_ok.equals("ok")) {
                                     progressdialog.dismiss();
                                     Toast.makeText(getApplicationContext(), R.string.completed_signup, Toast.LENGTH_LONG).show();
                                     finish();
                                 }
                                 else if(create_ok.equals(null))
                                 {
                                     progressdialog.dismiss();
                                     createAccountDialog(v,5);
                                 }
                             }
                             },500);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        }
        else if(num==4)
        {
            caution.setTitle(R.string.Notification);
            caution.setMessage(R.string.please_duplicate_cheke).setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
        }
        else if(num == 5)
        {
            caution.setTitle(R.string.error);
            caution.setMessage(R.string.unable_communicate).setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
        }
        AlertDialog Caution = caution.create();
        Caution.show();

    }


}
