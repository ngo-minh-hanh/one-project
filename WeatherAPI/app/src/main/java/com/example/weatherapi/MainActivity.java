package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button      btnSearch, btnChangeActivity;
    EditText    editTextSearch;
    TextView    textViewCity, textViewCoutry, textViewTemp, textViewStatus, textViewCloud, textViewHumidity, textViewWind, textViewDay;
    ImageView   imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCurrentWeatherData("Saigon");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editTextSearch.getText().toString();
                GetCurrentWeatherData(city);
            }
        });
        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = editTextSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this, ManHinh2Activity.class);
                intent.putExtra("name", city);
                startActivity(intent);

            }
        });
    }
    public void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=aae69af918f654a5fe3c4e3cab61cef6";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            textViewCity.setText("Tên thành phố: "+name);

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String Day = simpleDateFormat.format(date);
                            textViewDay.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");
                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/wn/"+icon+"@2x.png").into(imageViewIcon);
                            textViewStatus.setText(status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String temp = jsonObjectMain.getString("temp");
                            textViewTemp.setText(temp+"oC");

                            String humidity = jsonObjectMain.getString("humidity");
                            textViewHumidity.setText(humidity+"%");

                            JSONObject jsonObjectclouds = jsonObject.getJSONObject("clouds");
                            String cloud = jsonObjectclouds.getString("all");
                            textViewCloud.setText(cloud+"%");

                            JSONObject jsonObjectwind = jsonObject.getJSONObject("wind");
                            String wind = jsonObjectwind.getString("speed");
                            textViewWind.setText(wind+"m/s");

                            JSONObject jsonObjectsys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectsys.getString("country");
                            textViewCoutry.setText("Tên quốc gia: "+country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        btnChangeActivity   = findViewById(R.id.btnChangeActivity);
        btnSearch           = findViewById(R.id.btnSearch);
        editTextSearch      = findViewById(R.id.editTextSearch);
        textViewCity        = findViewById(R.id.textViewCity);
        textViewCloud       = findViewById(R.id.textViewCloud);
        textViewCoutry      = findViewById(R.id.editTextCoutry);
        textViewDay         = findViewById(R.id.textViewDay);
        textViewHumidity    = findViewById(R.id.textViewHumidity);
        textViewStatus      = findViewById(R.id.textViewStatus);
        textViewTemp        = findViewById(R.id.textViewTemp);
        textViewWind        = findViewById(R.id.textViewWind);
        imageViewIcon       = findViewById(R.id.imageViewIcon);
    }
}