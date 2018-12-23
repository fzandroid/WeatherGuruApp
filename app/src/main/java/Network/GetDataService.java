package Network;

import Data.WeatherForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

     String API_KEY = "59f33f5179ca1c02833317e3ae5db345";

    @GET("data/2.5/forecast")
    Call<WeatherForecast> getWeatherForecast(@Query("q")String location, @Query("APPID")String API_KEY);
}
