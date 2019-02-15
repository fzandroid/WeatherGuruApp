package com.example.faizanwar.weatherguru.Activity.sync;

import android.text.TextUtils;
import android.util.Log;
import Data.LocatorPlaces;
import Data.WeatherForecast;
import Network.GetDataService;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherSyncTask {

    public static void syncWeather(LocatorPlaces places, String tempUnit) {
        String mUserCity = "";
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        if (places != null && !TextUtils.isEmpty(places.getmPlaceName())) {
            mUserCity = places.getmPlaceName();
        }
        Call<WeatherForecast> call = service.getWeatherForecast(mUserCity, GetDataService.API_KEY, tempUnit); //imperial for F
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.d("response received", "--->>>>");


            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.d("response not received", "--->>>>");

            }
        });
    }


}
