package com.example.faizanwar.weatherguru.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faizanwar.weatherguru.Activity.Util.WeatherUtil;
import com.example.faizanwar.weatherguru.R;

import Data.ForeCastList;
import Data.WeatherForecast;

public class WeatherForecastListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private WeatherForecast weatherForecast;
    private Context context;
    private WeatherListClickListener clickListener;
    private String tempUnit;

    public WeatherForecastListAdapter(WeatherForecast weatherForecast, Context context, String tempUnit) {
        this.weatherForecast = weatherForecast;
        this.context = context;
        this.tempUnit = tempUnit;
    }

    public interface WeatherListClickListener {
        void onWeatherClicked(WeatherForecast weatherForecast);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.content_weather_forecast_list, viewGroup, false);
        return new WeatherForecastListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        WeatherForecastListHolder weatherForecastListHolder = (WeatherForecastListHolder) viewHolder;
        final ForeCastList weatherForecastList = weatherForecast.list.get(position);
        weatherForecastListHolder.mDateListLabel.setText(WeatherUtil.getDayFromDate(weatherForecastList.date.substring(0, 10)));
        weatherForecastListHolder.mWeatherConditionTextLabel.setText(weatherForecastList.getWeather().get(0).descrition);
        weatherForecastListHolder.mMaxTemperatureListLabel.setText(WeatherUtil.formatTempratureWithUnit(weatherForecastList.main.maxTemp + "", tempUnit));
        weatherForecastListHolder.mMinTemperatureListLabel.setText(WeatherUtil.formatTempratureWithUnit(weatherForecastList.main.minTemp + "", tempUnit));
        WeatherUtil.mapWeatherConditionToImage(weatherForecastList.weather.get(0).descrition, weatherForecastListHolder.mWeatherForecastConditionImage);

    }

    @Override
    public int getItemCount() {
        return weatherForecast.list.size();
    }

    class WeatherForecastListHolder extends RecyclerView.ViewHolder {

        private ImageView mWeatherForecastConditionImage;
        private TextView mDateListLabel;
        private TextView mWeatherConditionTextLabel;
        private TextView mMaxTemperatureListLabel;
        private TextView mMinTemperatureListLabel;

        public WeatherForecastListHolder(@NonNull View itemView) {
            super(itemView);
            mWeatherForecastConditionImage = itemView.findViewById(R.id.WeatherConditionListImage);
            mDateListLabel = itemView.findViewById(R.id.DateListLabel);
            mWeatherConditionTextLabel = itemView.findViewById(R.id.WeatherConditionListText);
            mMaxTemperatureListLabel = itemView.findViewById(R.id.MaxTempListLabel);
            mMinTemperatureListLabel = itemView.findViewById(R.id.MinTempListLabel);

        }
    }
}
