package org.example.lab_6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class Controller {
    @FXML
    protected void onCircleButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Кнопка Circle");
        alert.setHeaderText(null);
        alert.setContentText("Нажата кнопка Circle!");
        alert.showAndWait();
    }

    @FXML
    protected void onRectangleButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Кнопка Rectangle");
        alert.setHeaderText(null);
        alert.setContentText("Нажата кнопка Rectangle!");
        alert.showAndWait();
    }
}