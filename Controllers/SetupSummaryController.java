package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import classes.*;

public class SetupSummaryController {
    @FXML
    private Label nameLabel;
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
    private int credits;
    @FXML
    private static Stage guiStage;

    private Player player;
    private static GameGuiController uiController;

    public static GameGuiController getUiController() {
        return uiController;
    }

    public void initData(String name, String strength, String agility, String intelligence,
                         String charisma, String engineering, int credits) {
        nameLabel.setText(name);
        this.strength.setText(strength);
        this.agility.setText(agility);
        this.intelligence.setText(intelligence);
        this.charisma.setText(charisma);
        this.engineering.setText(engineering);
        this.credits = credits;
        credit.setText(String.valueOf(credits));
    }

    @FXML
    private void selectAgain() {
        PlayerController.closeNewStage();
    }

    @FXML
    private void proceedToGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Gui.fxml"));
            Parent gameUI = (Parent) loader.load();
            uiController = loader.getController();
            BanditController.getGameGuiController(uiController);
            TraderScreenController.getGameGuiController(uiController);
            player = new Player(nameLabel.getText(), Integer.parseInt(strength.getText()),
                    Integer.parseInt(agility.getText()), Integer.parseInt(intelligence.getText()),
                    Integer.parseInt(charisma.getText()), Integer.parseInt(engineering.getText()));
            Region spawn = player.getSpawningRegion();
            uiController.initializeData(spawn.getRegionName(),
                    spawn.getRegionTechLevel(), spawn.getRegionCoordinates(),
                    spawn, player, credits,
                    Integer.parseInt(charisma.getText()));
            uiController.setGamePlayer(player);
            Ship.setGold(Integer.parseInt(credit.getText()));
            Ship.setStrength(strength.getText());
            Ship.setAgility(agility.getText());
            Ship.setIntelligence(intelligence.getText());
            Ship.setEngineering(engineering.getText());
            Ship.setCharisma(charisma.getText());

            guiStage = new Stage();
            guiStage.setScene(new Scene(gameUI));
            PlayerController.hideNewStage();

            guiStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * new added
     * @return
     */
    public static void hideGuiStage() {
        guiStage.hide();
    }
    public static void showGuiStage() {
        guiStage.show();
    }


    /**
     * get the current gui Stage
     * @return a stage
     */
    public static Stage getGuiStage() {
        return guiStage;
    }

}
