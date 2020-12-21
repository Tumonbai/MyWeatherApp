package com.example.myweatherapp.data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public static String parseTime(Integer i){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:MM", Locale.getDefault());
        Date date=new Date();
        date.setTime(i.longValue()*1000);
        return simpleDateFormat.format(date.getTime()) ;
    }

    public static String parseDate(Integer i) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",Locale.getDefault()); // modify format
        String date;
        date = formatter.format(new Date(i.longValue()*1000));
        return date;
    }
}
