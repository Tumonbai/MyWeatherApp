package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myweatherapp.data.WeatherService;
import com.example.myweatherapp.data.models.WeatherModel;
import com.example.myweatherapp.data.utils.DateParser;


public class MainActivity extends AppCompatActivity implements WeatherService.WeatherCallback {
    TextView clouds, temp, minTemp, maxTemp, sunrise, sunset, wind, humidity, pressure, visibility, city, country,date;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        App.service.getWeatherByName("Bishkek", this);
    }


    public void initViews() {
        clouds = findViewById(R.id.clouds);
        temp = findViewById(R.id.temp);
        minTemp = findViewById(R.id.temp_min);
        maxTemp = findViewById(R.id.temp_max);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        wind = findViewById(R.id.wind);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        visibility = findViewById(R.id.visibility);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        date=findViewById(R.id.date);
        imageView=findViewById(R.id.imageView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(WeatherModel body) {
        date.setText(DateParser.parseDate(body.getDt()));
        clouds.setText(body.getWeather().get(0).getDescription());
        temp.setText(body.getMain().getTemp().toString());
        minTemp.setText(body.getMain().getTempMin().toString());
        maxTemp.setText(body.getMain().getTempMax().toString());
        sunrise.setText( DateParser.parseTime(body.getSys().getSunrise()));
        sunset.setText(DateParser.parseTime(body.getSys().getSunset()));
        wind.setText(body.getWind().getSpeed().toString());
        humidity.setText(body.getMain().getHumidity().toString());
        pressure.setText(body.getMain().getPressure().toString());
        country.setText(body.getSys().getCountry());
        visibility.setText(body.getVisibility().toString());
       String url= " http://openweathermap.org/img/wn/"+body.getWeather().get(0).getIcon()+ "@2x.png";
        Glide.with(this).load(url).centerCrop().into(imageView);

        Log.e("img","Url of img"+url);
    }


    public void onFailure(Exception exception) {
           exception.printStackTrace();
    }
}