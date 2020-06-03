package com.example.version.utils;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class Alerts {

    private Alert alert;

    public void AlertInformation(String context) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.setContentText(context);
        alert.showAndWait();
    }

    public void AlertError(String context) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(context);
        alert.showAndWait();
    }
}
