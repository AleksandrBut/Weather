package weather.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkController {
    private String jsonStr;

    public void sendRequest(String city) {

        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&lang=ru&APPID=6fd8b27b8ac6d588eb0dfb6fd559b239";
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                jsonStr = sb.toString();
                in.close();
            } else {
                System.out.println("NOT CONNECTED");
            }

        } catch (Exception e) {
            System.out.println("Error. Try again");
        }
    }

    public String getJsonStr(String city) {
        sendRequest(city);
        return jsonStr;
    }
}
