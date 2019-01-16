package Data;

import com.google.gson.annotations.SerializedName;

class Wind {

    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
