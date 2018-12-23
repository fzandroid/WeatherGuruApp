package Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {

    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public List<ForeCastList> list;
    @SerializedName("City")
    public City city;
    @SerializedName("country")
    public City country;
    @SerializedName("population")
    public City population;


}
