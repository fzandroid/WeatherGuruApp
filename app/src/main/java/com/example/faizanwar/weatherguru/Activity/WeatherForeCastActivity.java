package com.example.faizanwar.weatherguru.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faizanwar.weatherguru.Activity.Adapter.WeatherForecastListAdapter;
import com.example.faizanwar.weatherguru.Activity.Constants.RequestCodeConstants;
import com.example.faizanwar.weatherguru.Activity.Enums.TemperatureUnitOfMeasurement;
import com.example.faizanwar.weatherguru.Activity.Util.WeatherUtil;
import com.example.faizanwar.weatherguru.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import Data.LocatorPlaces;
import Data.WeatherForecast;
import Fragments.SearchFragment;
import Network.GetDataService;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForeCastActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, WeatherForecastListAdapter.WeatherListClickListener, SearchFragment.OnFragmentInteractionListener {

    //TODO Create a custom view for search functionality

    private Toolbar mToolbar;
    private TextView mWeatherTextView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager locationManager;
    private LocationRequest mLocationRequest;
    private String mUserCity;
    private TextView mDateLabel;
    private ImageView mWeatherConditionImage;
    private TextView mWeatherConditionText;
    private TextView mMaxTemperatureLabel;
    private TextView mMinTemperatureLabel;
    private TextView mCityName;
    private RecyclerView mWeatherForecastList;
    private WeatherForecastListAdapter mWeatherAdapter;
    private LocatorPlaces mPlaces;
    private WeatherForecast mWeatherForecast;
    private String mTemperatureUnit = TemperatureUnitOfMeasurement.TEMPERATURE_UNIT_CELCIUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_activity);
        initializeUiComponents();
        initializeGoogleClient();
        if (getIntent() != null && getIntent().getExtras() != null) {
            checkExtra();
            dispatchrequest();
        } else if (!hasLocationPermission()) {
            requestLocationPermission(this, 101, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void initializeUiComponents() {

        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        mDateLabel = findViewById(R.id.DateLabel);
        mWeatherConditionImage = findViewById(R.id.WeatherConditionImage);
        mWeatherConditionText = findViewById(R.id.WeatherConditionLabel);
        mMaxTemperatureLabel = findViewById(R.id.MaxTempLabel);
        mMinTemperatureLabel = findViewById(R.id.MinTempLabel);
        mWeatherForecastList = findViewById(R.id.WeatherforecastList);
        mCityName = findViewById(R.id.CityName);
    }

    private void initializeFragment() {
        findViewById(R.id.SearchPlaceFragmentContainer).setVisibility(View.VISIBLE);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        SearchFragment fragment = new SearchFragment();
        fragmentTransaction.add(R.id.SearchPlaceFragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    private void dispatchrequest() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        if (mPlaces != null && !TextUtils.isEmpty(mPlaces.getmPlaceName())) {
            mUserCity = mPlaces.getmPlaceName();
        }
        Call<WeatherForecast> call = service.getWeatherForecast(mUserCity, GetDataService.API_KEY, mTemperatureUnit); //imperial for F
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.d("response received", "--->>>>");
                mWeatherForecast = new WeatherForecast(response.body());
                mWeatherAdapter = new WeatherForecastListAdapter(mWeatherForecast, getApplicationContext(), mTemperatureUnit);
                mWeatherForecastList.setAdapter(mWeatherAdapter);
                mWeatherForecastList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mCityName.setText(mWeatherForecast.city.name);
                mDateLabel.setText(WeatherUtil.getDayFromDate(mWeatherForecast.getList().get(0).date));
                mWeatherConditionText.setText(mWeatherForecast.getList().get(0).getWeather().get(0).descrition);
                mMaxTemperatureLabel.setText(WeatherUtil.formatTempratureWithUnit(mWeatherForecast.list.get(0).main.maxTemp + "", mTemperatureUnit));
                mMinTemperatureLabel.setText(WeatherUtil.formatTempratureWithUnit(mWeatherForecast.list.get(0).main.minTemp + "", mTemperatureUnit));
                WeatherUtil.mapWeatherConditionToImage(mWeatherForecast.list.get(0).weather.get(0).descrition, mWeatherConditionImage);
                mWeatherAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.d("response not received", "--->>>>");

            }
        });

    }

    private void checkExtra() {

        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey(RequestCodeConstants.EXTRA_LOCATION) && bundle.get(RequestCodeConstants.EXTRA_LOCATION) != null) {
            mPlaces = bundle.getParcelable(RequestCodeConstants.EXTRA_LOCATION);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mTemperatureUnit.equalsIgnoreCase(TemperatureUnitOfMeasurement.TEMPERATURE_UNIT_FARENHEIT)) {
            mTemperatureUnit = TemperatureUnitOfMeasurement.TEMPERATURE_UNIT_CELCIUS;
        } else {
            mTemperatureUnit = TemperatureUnitOfMeasurement.TEMPERATURE_UNIT_FARENHEIT;
        }
        dispatchrequest();
        return true;

    }

    private void requestLocationPermission(Activity activity, int requestId, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestId);
    }

    private void initializeGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLocationChanged(Location location) {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            //TODO handle the caase when the user hasnt given permission
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    findViewById(R.id.SearchEditText).setVisibility(View.VISIBLE);
                    mCityName.setVisibility(View.GONE);
                    findViewById(R.id.SearchEditText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initializeFragment();
                        }
                    });
                } else if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    findLocationAndDispatchRequest();
                }
            }
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        findLocationAndDispatchRequest();
    }

    private void findLocationAndDispatchRequest() {
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            double latitude = mLocation.getLatitude();
            double longitude = mLocation.getLongitude();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                mUserCity = addresses.get(0).getLocality();
                dispatchrequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startLocationUpdates() {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(6000)
                .setFastestInterval(1000);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission(this, 101, Manifest.permission.ACCESS_FINE_LOCATION);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    public void onWeatherClicked(WeatherForecast weatherForecast) {
        super.onStart();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
