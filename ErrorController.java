package com.example.MAIN;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ErrorController {
    @FXML
    private Button back = new Button();

    @FXML
    private void setBack() {
        back.setOnAction(event -> {
            try {
                // close the error window
                Stage errorStage = (Stage) back.getScene().getWindow();
                errorStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
