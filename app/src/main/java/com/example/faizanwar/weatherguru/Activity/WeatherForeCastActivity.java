package com.example.faizanwar.weatherguru.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.faizanwar.weatherguru.R;

public class WeatherForeCastActivity extends AppCompatActivity {

    //TODO Create a custom view for search functionality

    private TextView mWeatherTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_activity);
        mWeatherTextView = findViewById(R.id.WeatherTextView);
       mWeatherTextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(WeatherForeCastActivity.this,SearchPlacesActivity.class);
               startActivity(intent);
;           }
       });
    }
}
