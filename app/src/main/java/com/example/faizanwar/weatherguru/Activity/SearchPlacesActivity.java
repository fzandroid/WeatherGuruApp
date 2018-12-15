package com.example.faizanwar.weatherguru.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.faizanwar.weatherguru.R;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import Data.LocatorPlaces;

public class SearchPlacesActivity extends AppCompatActivity {


    private TextView mToolbarTitle;
    private EditText mSearchLocationText;
    private ArrayList<LocatorPlaces> mResponseList;
    private GeoDataClient mGeoDataClient;
    private RecyclerView mSearchRersultRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_places);
        mGeoDataClient = Places.getGeoDataClient(this, null);
        initializeViews();
        setListeners();
    }

    private void initializeViews() {

        mToolbarTitle = findViewById(R.id.toolbar_title);
        mSearchLocationText = findViewById(R.id.LocationSearchLabel);
        mSearchRersultRecyclerView = findViewById(R.id.SearchResultRecyclerView);
    }

    private void setListeners() {

        mSearchLocationText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s)) {
                    Task<AutocompletePredictionBufferResponse> results = mGeoDataClient.getAutocompletePredictions(s.toString(), null, null);
                    results.addOnCompleteListener(new OnCompleteListener<AutocompletePredictionBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<AutocompletePredictionBufferResponse> task) {
                            mResponseList.clear();
                            if (task.isSuccessful()) {
                                AutocompletePredictionBufferResponse response = task.getResult();
                                for (int i = 0; i < response.getCount(); i++) {
                                    AutocompletePrediction place = response.get(i);
                                    mResponseList.add(new LocatorPlaces(place.getPlaceId(), place.getFullText(null).toString()));

                                }
                                response.release();
                            }
                        }
                    });
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}

