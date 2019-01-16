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
    @SerializedName("city")
    public City city;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public String population;


    public WeatherForecast(WeatherForecast body) {
        this.cod = body.cod;
        this.message = body.message;
        this.cnt = body.cnt;
        this.list = body.list;
        this.city = body.city;
        this.country = body.country;
        this.population = body.population;
    }


    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<ForeCastList> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }
}
