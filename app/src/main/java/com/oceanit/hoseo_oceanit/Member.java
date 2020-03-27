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

        LinearLayoutManager layoutManager[] = new LinearLayoutManager[4];

        for(int i = 0; i<4;i++)
        {
            layoutManager[i] = new LinearLayoutManager(getApplicationContext());
        }
        RecyclerView professor_list = (RecyclerView)findViewById(R.id.professor_list);
        RecyclerView professor_fellow_list = (RecyclerView)findViewById(R.id.professor_fellow_list);
        RecyclerView doctor_fellow_list = (RecyclerView)findViewById(R.id.doctor_fellow_list);
        RecyclerView master_fellow_list = (RecyclerView)findViewById(R.id.master_fellow_list);

        professor_list.setHasFixedSize(true);
        professor_list.setLayoutManager(layoutManager[0]);

        professor_fellow_list.setHasFixedSize(true);
        professor_fellow_list.setLayoutManager(layoutManager[1]);

        doctor_fellow_list.setHasFixedSize(true);
        doctor_fellow_list.setLayoutManager(layoutManager[2]);

        master_fellow_list.setHasFixedSize(true);
        master_fellow_list.setLayoutManager(layoutManager[3]);

        List<Item_member> professor_list_items = new ArrayList<>();
        List<Item_member> professor_fellow_list_items = new ArrayList<>();
        List<Item_member> doctor_fellow_list_items = new ArrayList<>();
        List<Item_member> master_fellow_list_items = new ArrayList<>();

        Item_member[] professor_list_item = new Item_member[4];
        Item_member[] professor_fellow_list_item = new Item_member[7];
        Item_member[] doctor_fellow_list_item = new Item_member[8];
        Item_member[] master_fellow_list_item = new Item_member[13];

        if(language.equals("ko")) {
            professor_list_item[0] = new Item_member(R.drawable.gohaklim, "고학림", "정보통신공학부", "hlko@hoseo.edu");
            professor_list_item[1] = new Item_member(R.drawable.limteaho, "임태호", "정보통신공학부", "taehoim@hoseo.edu");
            professor_list_item[2] = new Item_member(R.drawable.choyongho, "조용호", "정보통신공학부", "ykcho@hoseo.edu");
            professor_list_item[3] = new Item_member(R.drawable.kimgyewon, "김계원", "정보통신공학부", "kwkim@hoseo.edu");
            professor_fellow_list_item[0] = new Item_member(R.drawable.noimg, "박래호", "해양IT융합기술연구소", "kamrosoo4900@daum.net");
            professor_fellow_list_item[1] = new Item_member(R.drawable.kimminsang, "김민상", "해양IT융합기술연구소", "minsang@hoseo.edu");
            professor_fellow_list_item[2] = new Item_member(R.drawable.chaminhyeok, "차민혁", "해양IT융합기술연구소", "dwmh86@hoseo.edu");
            professor_fellow_list_item[3] = new Item_member(R.drawable.nandimandalam, "난디만달람모함", "해양IT융합기술연구소", "drmohankvn@hoseo.edu");
            professor_fellow_list_item[4] = new Item_member(R.drawable.leehyochan, "이효찬", "해양IT융합기술연구소", "lhc_104@naver.com");
            professor_fellow_list_item[5] = new Item_member(R.drawable.canthithuthuy, "칸 티 투 투이", "해양IT융합기술연구소", "kangchiwa@gmail.com");
            professor_fellow_list_item[6] = new Item_member(R.drawable.kimsaeyun, "김세연", "해양IT융합기술연구소", "seyeon92@hoseo.edu");
            doctor_fellow_list_item[0] = new Item_member(R.drawable.jocheonchi, "조천치", "정보통신공학", "zhaotianchi123@naver.com");
            doctor_fellow_list_item[1] = new Item_member(R.drawable.kimjoonho, "김준호", "정보통신공학", "katsurablue@naver.com");
            doctor_fellow_list_item[2] = new Item_member(R.drawable.jeongtaegeon, "정태건", "정보통신공학", "a40402@naver.com");
            doctor_fellow_list_item[3] = new Item_member(R.drawable.chaegwangyoung, "채광영", "정보통신공학", "rhkddud0822@naver.com");
            doctor_fellow_list_item[4] = new Item_member(R.drawable.luojingxin, "뤄징신", "정보통신공학", "binglin0716@163.com");
            doctor_fellow_list_item[5] = new Item_member(R.drawable.liupyeong, "류평", "정보통신공학", "kunliupeng@163.com");
            doctor_fellow_list_item[6] = new Item_member(R.drawable.shunhuakui, "쑨화쿠이", "정보통신공학", "sunhuakui@163.com");
            doctor_fellow_list_item[7] = new Item_member(R.drawable.pengjunying, "펑쥔잉", "정보통신공학", "zwpfjy@163.com");
            master_fellow_list_item[0] = new Item_member(R.drawable.komanjae, "고만재", "정보통신공학", "akswoakstp2@naver.com");
            master_fellow_list_item[1] = new Item_member(R.drawable.sonsewoo, "손세우", "정보통신공학", "tpdn333@gmail.com");
            master_fellow_list_item[2] = new Item_member(R.drawable.songhyunhak, "송현학", "정보통신공학", "rainy_930@naver.com");
            master_fellow_list_item[3] = new Item_member(R.drawable.yooyang, "유양", "정보통신공학", "ly960401@naver.com");
            master_fellow_list_item[4] = new Item_member(R.drawable.yoohodong, "유호동", "정보통신공학", "liuhaodong1314@naver.com");
            master_fellow_list_item[5] = new Item_member(R.drawable.leesungjoo, "이성주", "정보통신공학", "sjlee3416@naver.com");
            master_fellow_list_item[6] = new Item_member(R.drawable.jeonhoseok, "전호석", "정보통신공학", "wjsghtjr33@naver.com");
            master_fellow_list_item[7] = new Item_member(R.drawable.kimdohoon, "김도훈", "해양융합기술공학", "ehgns320@naver.com");
            master_fellow_list_item[8] = new Item_member(R.drawable.jeonghaeji, "정해지", "해양융합기술공학", "liuhaodong1314@naver.com");
            master_fellow_list_item[9] = new Item_member(R.drawable.wangbee, "왕비", "해양융합기술공학", "wf765969609@gmail.com");
            master_fellow_list_item[10] = new Item_member(R.drawable.jangyo, "장요", "해양융합기술공학", "zhang5163365@gmail.com");
            master_fellow_list_item[11] = new Item_member(R.drawable.junwon, "전원", "해양융합기술공학", "ty241666@naver.com");
            master_fellow_list_item[12] = new Item_member(R.drawable.philwoonghoo, "필웅후", "해양융합기술공학", "bixionghou960410@naver.com");
        }
        else
        {
            professor_list_item[0] = new Item_member(R.drawable.gohaklim, "Hak Rim GO", "ICT Engineering", "hlko@hoseo.edu");
            professor_list_item[1] = new Item_member(R.drawable.limteaho, "Tae Ho Im", "ICT Engineering", "taehoim@hoseo.edu");
            professor_list_item[2] = new Item_member(R.drawable.choyongho, "Yong Ho Cho", "ICT Engineering", "ykcho@hoseo.edu");
            professor_list_item[3] = new Item_member(R.drawable.kimgyewon, "Gye Won Kim", "ICT Engineering", "kwkim@hoseo.edu");
            professor_fellow_list_item[0] = new Item_member(R.drawable.noimg, "Rae Ho Park", "Ocean IT", "kamrosoo4900@daum.net");
            professor_fellow_list_item[1] = new Item_member(R.drawable.kimminsang, "Min Sang Kim", "Ocean IT", "minsang@hoseo.edu");
            professor_fellow_list_item[2] = new Item_member(R.drawable.chaminhyeok, "Min Hyeok Cha", "Ocean IT", "dwmh86@hoseo.edu");
            professor_fellow_list_item[3] = new Item_member(R.drawable.nandimandalam, "Mohan Krishna Varma Nandimandalam", "Ocean IT", "drmohankvn@hoseo.edu");
            professor_fellow_list_item[4] = new Item_member(R.drawable.leehyochan, "Lee Hyo Chan", "Ocean IT", "lhc_104@naver.com");
            professor_fellow_list_item[5] = new Item_member(R.drawable.canthithuthuy, "Can Thi Thu Thuy", "Ocean IT", "kangchiwa@gmail.com");
            professor_fellow_list_item[6] = new Item_member(R.drawable.kimsaeyun, "Se Yeon Kim", "Ocean IT", "seyeon92@hoseo.edu");
            doctor_fellow_list_item[0] = new Item_member(R.drawable.jocheonchi, "Jo Cheon Chi", "ICT Engineering", "zhaotianchi123@naver.com");
            doctor_fellow_list_item[1] = new Item_member(R.drawable.kimjoonho, " Kim Joon ho", "ICT Engineering", "katsurablue@naver.com");
            doctor_fellow_list_item[2] = new Item_member(R.drawable.jeongtaegeon, "Jeong Tae Geon", "ICT Engineering", "a40402@naver.com");
            doctor_fellow_list_item[3] = new Item_member(R.drawable.chaegwangyoung, "Chae Gwang Young", "ICT Engineering", "rhkddud0822@naver.com");
            doctor_fellow_list_item[4] = new Item_member(R.drawable.luojingxin, "Luo Jingxin", "ICT Engineering", "binglin0716@163.com");
            doctor_fellow_list_item[5] = new Item_member(R.drawable.liupyeong, "Liu Pyeong", "ICT Engineering", "kunliupeng@163.com");
            doctor_fellow_list_item[6] = new Item_member(R.drawable.shunhuakui, "Shunhuakui", "ICT Engineering", "sunhuakui@163.com");
            doctor_fellow_list_item[7] = new Item_member(R.drawable.pengjunying, "Pengjun Ying", "ICT Engineering", "zwpfjy@163.com");
            master_fellow_list_item[0] = new Item_member(R.drawable.komanjae, "Ko man Jae", "ICT Engineering", "akswoakstp2@naver.com");
            master_fellow_list_item[1] = new Item_member(R.drawable.sonsewoo, "Son Se woo", "ICT Engineering", "tpdn333@gmail.com");
            master_fellow_list_item[2] = new Item_member(R.drawable.songhyunhak, "Song Hyun Hak", "ICT Engineering", "rainy_930@naver.com");
            master_fellow_list_item[3] = new Item_member(R.drawable.yooyang, "Yoo Yang", "ICT Engineering", "ly960401@naver.com");
            master_fellow_list_item[4] = new Item_member(R.drawable.yoohodong, "Yoo Ho Dong", "ICT Engineering", "liuhaodong1314@naver.com");
            master_fellow_list_item[5] = new Item_member(R.drawable.leesungjoo, " Lee Sung Joo", "ICT Engineering", "sjlee3416@naver.com");
            master_fellow_list_item[6] = new Item_member(R.drawable.jeonhoseok, "Jeon Ho Seok", "ICT Engineering", "wjsghtjr33@naver.com");
            master_fellow_list_item[7] = new Item_member(R.drawable.kimdohoon, " Kim Do Hoon", "OceanIT Engineering", "ehgns320@naver.com");
            master_fellow_list_item[8] = new Item_member(R.drawable.jeonghaeji, "Jeong Hae Ji", "OceanIT Engineering", "liuhaodong1314@naver.com");
            master_fellow_list_item[9] = new Item_member(R.drawable.wangbee, "Wang Bee", "OceanIT Engineering", "wf765969609@gmail.com");
            master_fellow_list_item[10] = new Item_member(R.drawable.jangyo, "Jang Yo", "OceanIT Engineering", "zhang5163365@gmail.com");
            master_fellow_list_item[11] = new Item_member(R.drawable.junwon, "Jun Won", "OceanIT Engineering", "ty241666@naver.com");
            master_fellow_list_item[12] = new Item_member(R.drawable.philwoonghoo, "Phil Woong Hoo", "OceanIT Engineering", "bixionghou960410@naver.com");
        }

        for(int i =0; i<4;i++)
        {
            professor_list_items.add(professor_list_item[i]);
        }
        for(int i =0; i<7;i++)
        {
            professor_fellow_list_items.add(professor_fellow_list_item[i]);
        }

        for(int i =0; i<8;i++)
        {
            doctor_fellow_list_items.add(doctor_fellow_list_item[i]);
        }

        for(int i =0; i<13;i++)
        {
            master_fellow_list_items.add(master_fellow_list_item[i]);
        }

        professor_list.setAdapter(new RecyclerAdapter_member(getApplicationContext(),professor_list_items,R.layout.activity_member));
        professor_fellow_list.setAdapter(new RecyclerAdapter_member(getApplicationContext(),professor_fellow_list_items,R.layout.activity_member));
        doctor_fellow_list.setAdapter(new RecyclerAdapter_member(getApplicationContext(),doctor_fellow_list_items,R.layout.activity_member));
        master_fellow_list.setAdapter(new RecyclerAdapter_member(getApplicationContext(),master_fellow_list_items,R.layout.activity_member));
    }
}
