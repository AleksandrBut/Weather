package weather.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

public class WeatherInfo {
    private String date;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String iconSrc;

    public WeatherInfo(JsonObject jsonObject) {
        date = "Погода сейчас";
        temperature = jsonObject.getAsJsonObject("main").get("temp").toString();
        pressure = jsonObject.getAsJsonObject("main").get("pressure").toString();
        humidity = jsonObject.getAsJsonObject("main").get("humidity").toString();
        windSpeed = jsonObject.getAsJsonObject("wind").get("speed").toString();

        JsonArray jsonArray = jsonObject.getAsJsonArray("weather");
        Iterator <JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject object = iterator.next().getAsJsonObject();
            iconSrc = object.get("icon").toString();
            iconSrc = iconSrc.substring(1, iconSrc.length() - 1);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    @Override
    public String toString() {
        return ("\tDay: " + date + "\n - t: " + temperature + " *C\n - wind speed: " + windSpeed +
                " m/s\n - pressure: " + pressure +" mm rt st\n - humidity: " + humidity +" %");
    }
}
