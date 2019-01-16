package com.example.faizanwar.weatherguru.Activity.Enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TemperatureUnitOfMeasurement {

        String TEMPERATURE_UNIT_CELCIUS= "metric";
        String TEMPERATURE_UNIT_FARENHEIT= "imperial";

    }
