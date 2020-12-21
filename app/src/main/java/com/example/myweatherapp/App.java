package com.example.myweatherapp;
import android.app.Application;
import com.example.myweatherapp.data.WeatherService;

public class App extends Application {
    public static WeatherService service;
    @Override
    public void onCreate() {
        super.onCreate();
        service=new WeatherService();
    }
}
