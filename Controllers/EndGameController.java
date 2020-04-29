package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import classes.SpaceTradermain;


public class EndGameController {
    private Stage newStage = SetupSummaryController.getGuiStage();
    private GameGuiController uiController = SetupSummaryController.getUiController();


    @FXML
    private Button restartButton;

    @FXML
    private void restartGame() throws Exception {
        SpaceTradermain.restartGame();
        uiController.clearDistanceListView();
        uiController.clearListDistance();
        uiController.clearRegionList();
        restartButton.getScene().getWindow().hide();
        newStage.close();

    }

}
