package com.example.faizanwar.weatherguru.Activity.Enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface WeatherConditionTypes {

    String WEATHER_CONDITION_TYPE_CLOUDS = "broken clouds";
    String WEATHER_CONDITION_TYPE_RAIN = "light rain";
    String WEATHER_CONDITION_TYPE_CLEAR = "clear sky";

}
