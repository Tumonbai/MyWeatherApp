package com.example.myweatherapp.data;

import com.example.myweatherapp.data.models.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherService {
    public static final String API_KEY = "faa8d5fd894430d4ef4c40cf9be66868";
    String units="metric";
    String lang="ru";

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/")
            .build();
    WeatherApi service=retrofit.create(WeatherApi.class);

    public  void getWeatherByName( String name,final WeatherCallback callback){
        Call<WeatherModel> call=service.getWeatherByCityName(name,API_KEY,units);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
              if(response.isSuccessful()&response.body()!=null){
                  callback.onSuccess(response.body());
              }else{
                  callback.onFailure(new Exception());
              }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                t.printStackTrace();
                  callback.onFailure(( Exception)t);
            }
        });

    }



    public interface WeatherApi{
        @GET("data/2.5/weather?")
        Call<WeatherModel> getWeatherByCityName(
                @Query("q") String name,
                @Query("appid") String apiKey,
               // @Query("lang") String lang,
                @Query("units") String units
        );
    }
    public interface WeatherCallback{
        void onSuccess(WeatherModel body);
        void onFailure(Exception exception);
    }
}
