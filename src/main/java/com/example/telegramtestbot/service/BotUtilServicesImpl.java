package com.example.telegramtestbot.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class BotUtilServicesImpl implements BotUtilServices{
    @Autowired
    private Environment env;
    public String getVideoLink(String query, int resultsCount) {
        try{
            query = query.replace(" ", "+");
            String key = env.getProperty("youtube.key");

            //URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults="+resultsCount+"&order=viewCount&q="+query+"key="+key);
            URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults="+resultsCount+"&order=viewCount&q="+query+"&type=video&key="+key);
            System.out.println(url);
            URLConnection connection = url.openConnection();

            String line = "";
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())))
            {
                String read = in.readLine();
                while (read != null) {
                    line = line + read;
                    read = in.readLine();
                }
            }

            JSONObject jsonObject = (JSONObject) new JSONTokener(line).nextValue();
            JSONArray videosDataArray = jsonObject.getJSONArray("items");

            String result = "";

            for(int i = 0;i<videosDataArray.length();i++)
            {
                JSONObject item = videosDataArray.getJSONObject(i);
                String videoId = item.getJSONObject("id").get("videoId").toString();
                String title = item.getJSONObject("snippet").get("title").toString().replaceAll("&quot;","'");
                System.out.println(item);
                String channel = item.getJSONObject("snippet").get("channelTitle").toString().replaceAll("&quot;","'");
                result = result+(i+1)+". "+"Канал: " + channel + "\n" +
                        "Название: " + title + "\n" +
                        "https://www.youtube.com/watch?v="+videoId+" \n";
            }
            return result;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Error while finding your video";
        }
    }

    @Override
    public String getWeather(double lon, double lat) {
        try{
            if(lon==0 && lat == 0)
                return "Не указаны координаты. Отправьте свою геолокацию или любую другую точку на карте";
            String key = env.getProperty("weather.key");
            //URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults="+resultsCount+"&order=viewCount&q="+query+"key="+key);
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&lang=ru&units=metric&appid="+key);
            URLConnection connection = url.openConnection();

            String line = "";
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())))
            {
                String read = in.readLine();
                while (read != null) {
                    line = line + read;
                    read = in.readLine();
                }
            }

            JSONObject jsonObject = (JSONObject) new JSONTokener(line).nextValue();
            System.out.println(jsonObject);
            String temp = jsonObject.getJSONObject("main").get("temp").toString();
            String wind = jsonObject.getJSONObject("wind").get("speed").toString();
            String humidity = jsonObject.getJSONObject("main").get("humidity").toString();
            String pressure = jsonObject.getJSONObject("main").get("pressure").toString();
            String result = "\tТемпература: " + temp +" ℃\n"+
                            "\tВлажность: " + humidity +"%\n"+
                            "\tДавление: " + pressure +" мм. рт. ст.\n"+
                            "\tСкорость ветра: " + wind +" м/c\n";

            return result;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Error while parsing your location";
        }
    }
}
