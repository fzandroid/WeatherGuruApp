package Data;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    public double temp;
    @SerializedName("temp_min")
    public double minTemp;
    @SerializedName("temp_max")
    public double maxTemp;
    @SerializedName("pressure")
    public double pressure;
    @SerializedName("sea_level")
    public double seaLevel;
    @SerializedName("grnd_level")
    public double groundLevel;

    public double getTemp() {
        return temp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    @SerializedName("humidity")

    public double humidity;
    @SerializedName("temp_kf")
    public double tempKf;
}
