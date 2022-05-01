package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManHinh2Activity extends AppCompatActivity {

    ImageView imageViewBack;
    TextView textViewCity;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_2);

        imageViewBack = findViewById(R.id.imageViewBack);
        textViewCity = findViewById(R.id.textViewCity);
        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        String city = intent.getStringExtra("name");

        ArrayList<Thoitiet> mangthoitiet = new ArrayList<Thoitiet>();

        Thoitiet thoitiet1 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet2 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet3 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet4 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet5 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet6 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");
        Thoitiet thoitiet7 = new Thoitiet("1","mưa",R.drawable.backgroud_main2,"27","20");

        mangthoitiet.add(thoitiet1);
        mangthoitiet.add(thoitiet2);
        mangthoitiet.add(thoitiet3);
        mangthoitiet.add(thoitiet4);
        mangthoitiet.add(thoitiet5);
        mangthoitiet.add(thoitiet6);
        mangthoitiet.add(thoitiet7);


        CustomAdapterListview customAdapterListview = new CustomAdapterListview(ManHinh2Activity.this,mangthoitiet);
        listView.setAdapter(customAdapterListview);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }
}