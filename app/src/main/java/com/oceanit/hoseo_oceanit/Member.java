package com.oceanit.hoseo_oceanit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Member extends AppCompatActivity {

    Tools tools = new Tools();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        String language = tools.language;

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.member_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item_member> items = new ArrayList<>();
        Item_member[] item = new Item_member[9];
        if(language.equals("ko")) {
            item[0] = new Item_member(R.drawable.gohaklim, "고학림", "해양IT융합기술연구소", "hlko@hoseo.edu");
            item[1] = new Item_member(R.drawable.limteaho, "임태호", "해양IT융합기술연구소", "taehoim@hoseo.edu");
            item[2] = new Item_member(R.drawable.choyongho, "조용호", "해양IT융합기술연구소", "ykcho@hoseo.edu");
            item[3] = new Item_member(R.drawable.kimgyewon, "김계원", "해양IT융합기술연구소", "kwkim@hoseo.edu");
            item[4] = new Item_member(R.drawable.kimminsang, "김민상", "해양IT융합기술연구소", "minsang@hoseo.edu");
            item[5] = new Item_member(R.drawable.chaminhyeok, "차민혁", "해양IT융합기술연구소", "dwmh86@hoseo.edu");
            item[6] = new Item_member(R.drawable.kimsaeyun, "김세연", "해양IT융합기술연구소", "seyeon92@hoseo.edu");
            item[7] = new Item_member(R.drawable.nandimandalam, "난디만달람모함", "해양IT융합기술연구소", "drmohankvn@hoseo.edu");
            item[8] = new Item_member(R.drawable.leehyochan, "이효찬", "해양IT융합기술연구소", "lhc_104@naver.com");
        }
        else
        {
            item[0] = new Item_member(R.drawable.gohaklim, "Hak Rim GO", "Ocean IT", "hlko@hoseo.edu");
            item[1] = new Item_member(R.drawable.limteaho, "Tae Ho Im", "Ocean IT", "taehoim@hoseo.edu");
            item[2] = new Item_member(R.drawable.choyongho, "Yong Ho Cho", "Ocean IT", "ykcho@hoseo.edu");
            item[3] = new Item_member(R.drawable.kimgyewon, "Gye Won Kim", "Ocean IT", "kwkim@hoseo.edu");
            item[4] = new Item_member(R.drawable.kimminsang, "Min Sang Kim", "Ocean IT", "minsang@hoseo.edu");
            item[5] = new Item_member(R.drawable.chaminhyeok, "Min Hyeok Cha", "Ocean IT", "dwmh86@hoseo.edu");
            item[6] = new Item_member(R.drawable.kimsaeyun, "Se Yeon Kim", "Ocean IT", "seyeon92@hoseo.edu");
            item[7] = new Item_member(R.drawable.nandimandalam, "Mohan Krishna Varma Nandimandalam", "Ocean IT", "drmohankvn@hoseo.edu");
            item[8] = new Item_member(R.drawable.leehyochan, "Lee Hyo Chan", "Ocean IT", "lhc_104@naver.com");
        }

        for(int i =0; i<9;i++)
        {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter_member(getApplicationContext(),items,R.layout.activity_member));
    }
}
