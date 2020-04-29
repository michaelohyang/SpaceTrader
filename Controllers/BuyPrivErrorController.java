package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BuyPrivErrorController {
    private Stage aStage;

    @FXML
    private Button aButton;
    public void closeScreen() {
        aButton.getScene().getWindow().hide();
    }

}
