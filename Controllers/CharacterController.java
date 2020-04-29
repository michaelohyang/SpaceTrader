package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import classes.*;

public class CharacterController {
    @FXML
    private Label nameLabe;
    @FXML
    private Label strength;
    @FXML
    private Label agility;
    @FXML
    private Label intelligence;
    @FXML
    private Label charisma;
    @FXML
    private Label engineering;
    @FXML
    private Label credit;
    @FXML
    private static Stage guiStage;

    // non-FXML fields
    private int credits;
    private Player player;


    /**
     *  initialize character with stats
     * @param name character name
     * @param strength character strength
     * @param agility character agility
     * @param intelligence character intelligence
     * @param charisma character charisma
     * @param engineering character engineering
     * @param credits character credits
     */
    public void initData(String name, String strength, String agility, String intelligence,
                         String charisma, String engineering, int credits) {
        nameLabe.setText(name);
        this.strength.setText(strength);
        this.agility.setText(agility);
        this.intelligence.setText(intelligence);
        this.charisma.setText(charisma);
        this.engineering.setText(engineering);
        this.credits = credits;
        credit.setText(String.valueOf(credits));
    }

    /**
     *
     */
    @FXML
    private void selectAgain() {
        PlayerController.closeNewStage();
    }

    /**
     * proceed to the main game screen with character stats
     */
    @FXML
    private void proceedToGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Gui.fxml"));
            Parent gameUI = (Parent) loader.load();
            GameGuiController uiController = loader.getController();
            player = new Player(nameLabe.getText(), Integer.parseInt(strength.getText()),
                    Integer.parseInt(agility.getText()),
                    Integer.parseInt(intelligence.getText()),
                    Integer.parseInt(charisma.getText()),
                    Integer.parseInt(engineering.getText()));
            Region spawn = player.getSpawningRegion();
            uiController.initializeData(spawn.getRegionName(),
                    spawn.getRegionTechLevel(), spawn.
                    getRegionCoordinates(), spawn, player,
                    credits, Integer.parseInt(charisma.getText()));
            uiController.setGamePlayer(player);
            guiStage = new Stage();
            guiStage.setScene(new Scene(gameUI));
            PlayerController.hideNewStage();

            guiStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * show the gui stage again
     */
    public static void showGuiStage() {
        guiStage.show();
    }
}
