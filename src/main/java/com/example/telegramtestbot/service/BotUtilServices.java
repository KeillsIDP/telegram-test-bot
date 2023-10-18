package com.example.telegramtestbot.service;

import java.util.Date;

public interface BotUtilServices {
    String getVideoLink(String query, int resultsCount);
    String getWeather(double lon, double lat);
}
