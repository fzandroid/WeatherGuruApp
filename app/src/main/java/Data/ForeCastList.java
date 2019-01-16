package Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForeCastList {


    @SerializedName("dt")
    public String dt;
    @SerializedName("main")
    public Main main;
    @SerializedName("weather")
    public List<WeatherList> weather;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("dt_txt")
    public String date;

    public String getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public List<WeatherList> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public String getDate() {
        return date;
    }
}
