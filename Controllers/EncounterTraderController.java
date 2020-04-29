package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class EncounterTraderController {
    @FXML
    private Button aButton;
    @FXML
    private Button backBtn;

    // non-FXML fields
    private boolean condition = true;
    private static Stage newstage;
    private Stage popStage = new Stage();
    private Scene temp2;
    private TraderScreenController tradeController = new TraderScreenController();
    private HashMap<String, Integer> tradeMap = new HashMap<>();
    private String[] tradingArr = new String[]{"Food", "Iron", "Oil", "Circuit", "Dark Matter"};
    private int tradingItemNum = getRandomNumberInRange(1, 4);


    /**
     * switches the current screen to the trader screen (Encounter)
     * @throws IOException if not there
     */
    @FXML
    public void toTraderScreen() throws IOException {
        if (condition) {
            condition = false;
            this.aButton.setText("The Trader Left");
            tradeController.getScene(aButton.getScene());
            tradeController.openTraderPopUp(tradeMap, tradingItemNum, tradingArr);
        }
    }

    /**
     * takes the player back to the Game GUI main screen
     */
    @FXML
    public void toGUIScreen() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }


    /**
     *  get the current scene
     * @param scene the scene we are using
     */
    public void getScene(Scene scene) {
        temp2 = scene;
    }


    /**
     *  encounter trader screen pop-up
     * @throws IOException some input errors
     */
    public void setPopTrader() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../fxml/EncounterTrader.fxml"));
        Parent popUp = (Parent) loader.load();
        popStage = new Stage();
        popStage.setScene(new Scene(popUp));
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.initOwner(temp2.getWindow());
        popStage.show();
    }

    /**
     * get random number within a given range
     * @param min lower bound
     * @param max upper bound
     * @return an int value of the random num
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
