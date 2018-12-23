package Data;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("coord")
    public String coordinates;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public long population;
}
