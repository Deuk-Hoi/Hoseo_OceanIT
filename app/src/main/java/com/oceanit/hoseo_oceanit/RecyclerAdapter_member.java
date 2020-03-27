package com.oceanit.hoseo_oceanit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.util.Log.e;

public class RecyclerAdapter_member extends RecyclerView.Adapter<RecyclerAdapter_member.ViewHolder> {

    Context context;
    List<Item_member> items;
    int item_layout;
    Tools tools = new Tools();
    String language = tools.language;

    public RecyclerAdapter_member(Context context, List<Item_member> items, int item_layout)
    {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_member,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Item_member item = items.get(i);
        Drawable drawable = ContextCompat.getDrawable(context, item.getMember_image());
        viewHolder.member_image.setBackground(drawable);
        viewHolder.name.setText(item.getName());
        viewHolder.position.setText(item.getPosition());
        viewHolder.email.setText(item.getEmail());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log = item.getName();
                e("log",log);
                Intent intent = null;
                switch (item.getName()) {
                    case "고학림":
                    case "Hak Rim GO":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/gohaklim");
                            intent.putExtra("name", "고학림");
                            intent.putExtra("position", "정보통신공학부");
                            intent.putExtra("call", "041-540-5691");
                            intent.putExtra("email", "hlko@hoseo.edu");
                            intent.putExtra("bachelor_school", "숭실대학교");
                            intent.putExtra("bachelor_major", "전자공학");
                            intent.putExtra("bachelor_degree", "공학사");
                            intent.putExtra("master_school", "Fairlgh Dickinson Univ.");
                            intent.putExtra("master_major", "전자공학");
                            intent.putExtra("master_degree", "공학석사");
                            intent.putExtra("doctor_school", "North Carolina State Univ.");
                            intent.putExtra("doctor_major", "전기,컴퓨터공학");
                            intent.putExtra("doctor_degree", "공학박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/gohaklim");
                            intent.putExtra("name", "Hak Rim GO");
                            intent.putExtra("position", "ICT Engineering");
                            intent.putExtra("call", "041-540-5691");
                            intent.putExtra("email", "hlko@hoseo.edu");
                            intent.putExtra("bachelor_school", "Soongsil Univ.");
                            intent.putExtra("bachelor_major", "Electronic Engineering");
                            intent.putExtra("bachelor_degree", "Bachelor of Engineer");
                            intent.putExtra("master_school", "Fairlgh Dickinson Univ.");
                            intent.putExtra("master_major", "Electronic Engineering");
                            intent.putExtra("master_degree", "Master of Engineering");
                            intent.putExtra("doctor_school", "North Carolina State Univ.");
                            intent.putExtra("doctor_major", "Electrical and Computer Engineering");
                            intent.putExtra("doctor_degree", "Doctor of Engineering");
                        }
                        context.startActivity(intent);
                        break;

                    case "임태호":
                    case "Tae Ho Im":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/limteaho");
                            intent.putExtra("name", "임태호");
                            intent.putExtra("position", "정보통신공학부");
                            intent.putExtra("call", "041-540-9640");
                            intent.putExtra("email", "taehoim@hoseo.edu");
                            intent.putExtra("bachelor_school", "중앙대학교");
                            intent.putExtra("bachelor_major", "전자전기공학전공");
                            intent.putExtra("bachelor_degree", "공학사");
                            intent.putExtra("master_school", "중앙대학교");
                            intent.putExtra("master_major", "디지털통신전공");
                            intent.putExtra("master_degree", "공학석사");
                            intent.putExtra("doctor_school", "중앙대학교");
                            intent.putExtra("doctor_major", "정보통신공학과");
                            intent.putExtra("doctor_degree", "공학박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/limteaho");
                            intent.putExtra("name", "Tae Ho Im");
                            intent.putExtra("position", "ICT Engineering");
                            intent.putExtra("call", "041-540-9640");
                            intent.putExtra("email", "taehoim@hoseo.edu");
                            intent.putExtra("bachelor_school", "Chung-ang Univ.");
                            intent.putExtra("bachelor_major", "Electronic and Electrical Engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Chung-ang Univ.");
                            intent.putExtra("master_major", "Digital Communication");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Chung-ang Univ.");
                            intent.putExtra("doctor_major", "Information and communication");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "조용호":
                    case "Yong Ho Cho"://이메일 수정해야함.
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/choyongho");
                            intent.putExtra("name", "조용호");
                            intent.putExtra("position", "정보통신공학부");
                            intent.putExtra("call", "041-540-5947");
                            intent.putExtra("email", "ykcho@hoseo.edu");
                            intent.putExtra("bachelor_school", "한국과학기술원");
                            intent.putExtra("bachelor_major", "전기및전자공학");
                            intent.putExtra("bachelor_degree", "공학사");
                            intent.putExtra("master_school", "한국과학기술원");
                            intent.putExtra("master_major", "전기및전자공학");
                            intent.putExtra("master_degree", "공학석사");
                            intent.putExtra("doctor_school", "한국과학기술원");
                            intent.putExtra("doctor_major", "전기및전자공학");
                            intent.putExtra("doctor_degree", "공학박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/choyongho");
                            intent.putExtra("name", "Yong Ho Cho");
                            intent.putExtra("position", "ICT Engineering");
                            intent.putExtra("call", "041-540-5947");
                            intent.putExtra("email", "ykcho@hoseo.edu");
                            intent.putExtra("bachelor_school", "KAIST");
                            intent.putExtra("bachelor_major", "Electrical and electronic engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "KAIST");
                            intent.putExtra("master_major", "Electrical and electronic engineering");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "KAIST");
                            intent.putExtra("doctor_major", "Electrical and electronic engineering");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "김계원":
                    case "Gye Won Kim":
                        Log.e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/kimgyewon");
                            intent.putExtra("name", "김계원");
                            intent.putExtra("position", "정보통신공학부");
                            intent.putExtra("call", "041-540-9831");
                            intent.putExtra("email", "kwkim@hoseo.edu");
                            intent.putExtra("bachelor_school", "호서대학교");
                            intent.putExtra("bachelor_major", "정보통신공학");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "호서대학교");
                            intent.putExtra("master_major", "정보통신공학");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "호서대학교");
                            intent.putExtra("doctor_major", "정보통신공학");
                            intent.putExtra("doctor_degree", "박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/kimgyewon");
                            intent.putExtra("name", "Gye Won Kim");
                            intent.putExtra("position", "ICT Engineering");
                            intent.putExtra("call", "041-540-9831");
                            intent.putExtra("email", "kwkim@hoseo.edu");
                            intent.putExtra("bachelor_school", "Hoseo Univ.");
                            intent.putExtra("bachelor_major", "Information and communication engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Hoseo Univ.");
                            intent.putExtra("master_major", "Information and communication engineering");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Hoseo Univ.");
                            intent.putExtra("doctor_major", "Information and communication engineering");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "박래호":
                    case "Rae Ho Park":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/noimg");
                            intent.putExtra("name", "박래호");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-5945");
                            intent.putExtra("email", "kamrosoo4900@daum.net");
                            intent.putExtra("bachelor_school", "육군사관학교");
                            intent.putExtra("bachelor_major", "전사학과");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "동국대학교");
                            intent.putExtra("master_major", "외교국방국");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "아주대학교");
                            intent.putExtra("doctor_major", "정보통신학");
                            intent.putExtra("doctor_degree", "석사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/noimg");
                            intent.putExtra("name", "Rae Ho Park");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-5945");
                            intent.putExtra("email", "kamrosoo4900@daum.net");
                            intent.putExtra("bachelor_school", "Military Academy");
                            intent.putExtra("bachelor_major", "Warrior's Department");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Dongguk Univ.");
                            intent.putExtra("master_major", "The Ministry of Foreign Affairs and Defense");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Ajou Univ.");
                            intent.putExtra("doctor_major", "Information and Communication Sciences");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "김민상":
                    case "Min Sang Kim":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/kimminsang");
                            intent.putExtra("name", "김민상");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "misang@hoseo.edu");
                            intent.putExtra("bachelor_school", "호서대학교");
                            intent.putExtra("bachelor_major", "정보통신공학");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "호서대학교");
                            intent.putExtra("master_major", "정보통신공학");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "호서대학교");
                            intent.putExtra("doctor_major", "정보통신공학");
                            intent.putExtra("doctor_degree", "박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/kimminsang");
                            intent.putExtra("name", "Min Sang Kim");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "misang@hoseo.edu");
                            intent.putExtra("bachelor_school", "Hoseo Univ.");
                            intent.putExtra("bachelor_major", "Information and communication engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Hoseo Univ.");
                            intent.putExtra("master_major", "Information and communication engineering");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Hoseo Univ.");
                            intent.putExtra("doctor_major", "Information and communication engineering");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "차민혁":
                    case "Min Hyeok Cha":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/chaminhyeok");
                            intent.putExtra("name", "차민혁");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "dwmh86@hoseo.edu");
                            intent.putExtra("bachelor_school", "호서대학교");
                            intent.putExtra("bachelor_major", "정보통신공학");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "호서대학교");
                            intent.putExtra("master_major", "정보통신공학");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "");
                            intent.putExtra("doctor_major", "");
                            intent.putExtra("doctor_degree", "");
                        } else {
                            intent.putExtra("member_image2", "@drawable/chaminhyeok");
                            intent.putExtra("name", "Min Hyeok Cha");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "dwmh86@hoseo.edu");
                            intent.putExtra("bachelor_school", "Hoseo Univ.");
                            intent.putExtra("bachelor_major", "Information and communication engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Hoseo Univ.");
                            intent.putExtra("master_major", "Information and communication engineering");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "");
                            intent.putExtra("doctor_major", "");
                            intent.putExtra("doctor_degree", "");
                        }
                        context.startActivity(intent);
                        break;

                    case "난디만달람모함":
                    case "Mohan Krishna Varma Nandimandalam":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/nandimandalam");
                            intent.putExtra("name", "난디만달람모함");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "drmohankvn@hoseo.edu");
                            intent.putExtra("bachelor_school", "스리 벤카테스와라 대학교");
                            intent.putExtra("bachelor_major", "컴퓨터 응용");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "벨로르 공과대학");
                            intent.putExtra("master_major", "컴퓨터공학과");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "국민대학교 ");
                            intent.putExtra("doctor_major", "비즈니스IT");
                            intent.putExtra("doctor_degree", "박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/nandimandalam");
                            intent.putExtra("name", "Mohan Krishna Varma Nandimandalam");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "drmohankvn@hoseo.edu");
                            intent.putExtra("bachelor_school", "Sri Venkateswara Univ.");
                            intent.putExtra("bachelor_major", "Computer Applications");
                            intent.putExtra("bachelor_degree", "Computer Applications");
                            intent.putExtra("master_school", "VIT Univ.");
                            intent.putExtra("master_major", "Computer Science and Engineering");
                            intent.putExtra("master_degree", "Technology");
                            intent.putExtra("doctor_school", "Kookmin Univ.");
                            intent.putExtra("doctor_major", "Business IT");
                            intent.putExtra("doctor_degree", "Philosophy");
                        }
                        context.startActivity(intent);
                        break;
                    case "이효찬":
                    case "Lee Hyo Chan":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/leehyochan");
                            intent.putExtra("name", "이효찬");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "lhc_104@naver.com");
                            intent.putExtra("bachelor_school", "호서대학교");
                            intent.putExtra("bachelor_major", "정보통신공학");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "호서대학교");
                            intent.putExtra("master_major", "정보통신공학");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "호서대학교 ");
                            intent.putExtra("doctor_major", "정보통신공학");
                            intent.putExtra("doctor_degree", "박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/leehyochan");
                            intent.putExtra("name", "Lee Hyo Chan");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "lhc_104@naver.com");
                            intent.putExtra("bachelor_school", "Hoseo Univ.");
                            intent.putExtra("bachelor_major", "Information and communication engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "Hoseo Univ.");
                            intent.putExtra("master_major", "Information and communication engineering");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Hoseo Univ.");
                            intent.putExtra("doctor_major", "Information and communication engineering");
                            intent.putExtra("doctor_degree", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "칸 티 투 투이":
                    case "Can Thi Thu Thuy":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/canthithuthuy");
                            intent.putExtra("name", "칸 티 투 투이");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "kangchiwa@gmail.com");
                            intent.putExtra("bachelor_school", "국립하노이대학교");
                            intent.putExtra("bachelor_major", "물리 교사 교육");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "국립하노이대학교");
                            intent.putExtra("master_major", "이론 물리 및 수학 물리");
                            intent.putExtra("master_degree", "석사");
                            intent.putExtra("doctor_school", "이화여자대학교");
                            intent.putExtra("doctor_major", "응축 물질 물리학");
                            intent.putExtra("doctor_degree", "박사");
                            intent.putExtra("doctor_school2", "호서대학교");
                            intent.putExtra("doctor_major2", "디스플레이 엔지니어링");
                            intent.putExtra("doctor_degree2", "박사");
                        } else {
                            intent.putExtra("member_image2", "@drawable/canthithuthuy");
                            intent.putExtra("name", "Can Thi Thu Thuy");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "kangchiwa@gmail.com");
                            intent.putExtra("bachelor_school", "VNU-UNIVERSITY of Education");
                            intent.putExtra("bachelor_major", "Teacher Education in Physics");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "VNU-UNIVERSITY of Science");
                            intent.putExtra("master_major", "Theoretical Physics and Mathematical Physics");
                            intent.putExtra("master_degree", "Master");
                            intent.putExtra("doctor_school", "Ewha Womans Univ.");
                            intent.putExtra("doctor_major", "Condensed Matter Physics");
                            intent.putExtra("doctor_degree", "Doctor");
                            intent.putExtra("doctor_school2", "Hoseo Univ.");
                            intent.putExtra("doctor_major2", "Display Engineering");
                            intent.putExtra("doctor_degree2", "Doctor");
                        }
                        context.startActivity(intent);
                        break;

                    case "김세연":
                    case "Se Yeon Kim":
                        e("log", "error_in");
                        intent = new Intent(context, Member_info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (language.equals("ko")) {
                            intent.putExtra("member_image2", "@drawable/kimsaeyun");
                            intent.putExtra("name", "김세연");
                            intent.putExtra("position", "해양IT융합기술연구소");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "seyeon92@hoseo.edu");
                            intent.putExtra("bachelor_school", "호서대학교");
                            intent.putExtra("bachelor_major", "정보통신공학");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master_school", "");
                            intent.putExtra("master_major", "");
                            intent.putExtra("master_degree", "");
                            intent.putExtra("doctor_school", "");
                            intent.putExtra("doctor_major", "");
                            intent.putExtra("doctor_degree", "");
                        } else {
                            intent.putExtra("member_image2", "@drawable/kimsaeyun");
                            intent.putExtra("name", "Se Yeon Kim");
                            intent.putExtra("position", "Ocean IT");
                            intent.putExtra("call", "041-540-9565");
                            intent.putExtra("email", "seyeon92@hoseo.edu");
                            intent.putExtra("bachelor_school", "Hoseo Univ.");
                            intent.putExtra("bachelor_major", "Information and communication engineering");
                            intent.putExtra("bachelor_degree", "Bachelor");
                            intent.putExtra("master_school", "");
                            intent.putExtra("master_major", "");
                            intent.putExtra("master_degree", "");
                            intent.putExtra("doctor_school", "");
                            intent.putExtra("doctor_major", "");
                            intent.putExtra("doctor_degree", "");
                        }
                        context.startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView member_image;
        TextView name;
        TextView position;
        TextView email;
        CardView cardView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            member_image = (ImageView) itemView.findViewById(R.id.member_image);
            name = (TextView) itemView.findViewById(R.id.researcher_name);
            position = (TextView) itemView.findViewById(R.id.researcher_position);
            email = (TextView) itemView.findViewById(R.id.researcher_email);
            cardView = (CardView)itemView.findViewById(R.id.member_card);
        }
    }
}
