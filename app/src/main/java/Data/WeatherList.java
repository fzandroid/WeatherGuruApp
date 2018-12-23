package Data;

import com.google.gson.annotations.SerializedName;

class WeatherList {

    @SerializedName("id")
    public double id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String descrition;
    @SerializedName("icon")
    public String icon;
}
