package Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class LocatorPlaces implements Parcelable {

    private String mPlaceId;
    private String mPlaceName;
    private LatLng mPlaceLatLng;

    public LocatorPlaces(String mPlaceId, String mPlaceName) {
        this.mPlaceId = mPlaceId;
        this.mPlaceName = mPlaceName;
    }

    public String getmPlaceId() {
        return mPlaceId;
    }

    public void setmPlaceId(String mPlaceId) {
        this.mPlaceId = mPlaceId;
    }

    public String getmPlaceName() {
        return mPlaceName;
    }

    public void setmPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    public LatLng getmPlaceLatLng() {
        return mPlaceLatLng;
    }

    public void setmPlaceLatLng(LatLng mPlaceLatLng) {
        this.mPlaceLatLng = mPlaceLatLng;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPlaceId);
        dest.writeString(this.mPlaceName);
        dest.writeParcelable(this.mPlaceLatLng, flags);
    }

    protected LocatorPlaces(Parcel in) {
        this.mPlaceId = in.readString();
        this.mPlaceName = in.readString();
        this.mPlaceLatLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Parcelable.Creator<LocatorPlaces> CREATOR = new Parcelable.Creator<LocatorPlaces>() {
        @Override
        public LocatorPlaces createFromParcel(Parcel source) {
            return new LocatorPlaces(source);
        }

        @Override
        public LocatorPlaces[] newArray(int size) {
            return new LocatorPlaces[size];
        }
    };
}
