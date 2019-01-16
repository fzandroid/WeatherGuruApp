package Data;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("coord")
    public Coordinates coordinates;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public long population;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }
}
