package weather.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import weather.model.WeatherInfo;
import javafx.scene.control.Button;

public class WindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tempLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private ImageView nowForecastImg;

    @FXML
    private TextField cityTextBox;

    @FXML
    private Button searchButton;


    @FXML
    void initialize() {
        updateLabels();
        searchButton.setOnAction(event -> updateLabels());
    }

    public void handleEnter(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            updateLabels();
        }
    }

    private void updateLabels() {
        Gson gson = new Gson();
        JsonObject jsonObject;
        jsonObject = gson.fromJson(new NetworkController().getJsonStr(cityTextBox.getText()), JsonObject.class);
        WeatherInfo weatherInfo = new WeatherInfo(jsonObject);

        tempLabel.setText(weatherInfo.getTemperature() + " *C");
        humidityLabel.setText(weatherInfo.getHumidity() + " %");
        pressureLabel.setText(weatherInfo.getPressure() + " мм рт. ст.");
        windSpeedLabel.setText(weatherInfo.getWindSpeed() + " м/с");
        nowForecastImg.setImage(new Image("http://openweathermap.org/img/w/" + weatherInfo.getIconSrc() + ".png"));
    }
}
