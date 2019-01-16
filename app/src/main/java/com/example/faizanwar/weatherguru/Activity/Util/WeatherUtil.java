package com.example.faizanwar.weatherguru.Activity.Util;

import android.widget.ImageView;

import com.example.faizanwar.weatherguru.Activity.Enums.TemperatureUnitOfMeasurement;
import com.example.faizanwar.weatherguru.Activity.Enums.WeatherConditionTypes;
import com.example.faizanwar.weatherguru.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherUtil {

    public static void mapWeatherConditionToImage(String weatherDescription, ImageView imageView) {
        if (weatherDescription.equalsIgnoreCase(WeatherConditionTypes.WEATHER_CONDITION_TYPE_CLEAR)) {
            imageView.setImageResource(R.drawable.sunny);
        } else if (weatherDescription.equalsIgnoreCase(WeatherConditionTypes.WEATHER_CONDITION_TYPE_CLOUDS)) {
            imageView.setImageResource(R.drawable.clouds);
        } else if (weatherDescription.equalsIgnoreCase(WeatherConditionTypes.WEATHER_CONDITION_TYPE_RAIN)) {
            imageView.setImageResource(R.drawable.rain);
        }
    }

    public static String getDayFromDate(String date) {
        String dayOfTheWeek = "";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date date1 = format.parse(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
            dayOfTheWeek = simpleDateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheWeek;
    }

    public static String formatTempratureWithUnit(String temp, String temperatureUnit)

    {
        if (temperatureUnit.equalsIgnoreCase(TemperatureUnitOfMeasurement.TEMPERATURE_UNIT_CELCIUS)) {
            return temp + " \u2103";
        } else
            return temp + " \u2109";

    }
}
