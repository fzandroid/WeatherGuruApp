package Data;

import com.google.gson.annotations.SerializedName;

public class WeatherList {

    @SerializedName("id")
    public double id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String descrition;
    @SerializedName("icon")
    public String icon;

    public double getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescrition() {
        return descrition;
    }

    public String getIcon() {
        return icon;
    }
}
