package controllers;

import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;


import classes.*;

public class PoliceController {
    @FXML
    private static Player gamePlayer;
    @FXML
    private static Ship ship;
    @FXML
    private static String stolenItemName;
    @FXML
    private int policeFighterSkill;
    @FXML
    private int policePilotSkill;
    @FXML
    private int money;
    @FXML
    public static Stage policeStage;
    @FXML
    private AnchorPane fleePane;
    @FXML
    private AnchorPane fightPane;
    @FXML
    private AnchorPane forfeitPane;
    @FXML
    private Text fleeWin;
    @FXML
    private Text fleeLoss;
    @FXML
    private Text fightWin;
    @FXML
    private Text fightLoss;
    @FXML
    private Text forfeit;

    @FXML
    public void initializeScreen(Player player, int money) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("../fxml/Police.fxml"));
            Parent stage = (Parent) loader.load();
            this.gamePlayer = player;
            this.ship = gamePlayer.getShip().get(0);
            this.policeFighterSkill = (int) (java.lang.Math.random() * 25);
            this.policePilotSkill = (int) (java.lang.Math.random() * 25);
            this.money = money;
            policeStage = new Stage();
            policeStage.setScene(new Scene(stage));
            policeStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void flee() {
        fleePane.setVisible(true);
        if (gamePlayer.getAgility() > policePilotSkill) {
            fleeWin.setVisible(true);
            fleeLoss.setVisible(false);
            GameGuiController.cancelTravel = true;
        } else {
            fleeWin.setVisible(false);
            fleeLoss.setVisible(true);
            fleeLoss.setText("You didn't flee, Police took Food");
            ship.setHealth(ship.getHealth() - 10);
            ship.inventory.put("Food", 0);
            GameGuiController.cancelTravel = true;
        }
    }

    @FXML
    public void forfeit() {
        forfeitPane.setVisible(true);
        ship.inventory.put("Food", 0);
        forfeit.setText("Police took Food");
        GameGuiController.cancelTravel = false;
    }

    @FXML
    public void fight() {
        fightPane.setVisible(true);
        if (gamePlayer.getStrength() > policeFighterSkill) {
            fightWin.setVisible(true);
            fightLoss.setVisible(false);
            GameGuiController.cancelTravel = false;
        } else {
            fightWin.setVisible(false);
            fightLoss.setVisible(true);
            ship.setHealth(ship.getHealth() - 10);
            fightLoss.setText("You Lost, Police took Food");
            ship.inventory.put("Food", 0);
            GameGuiController.cancelTravel = true;
        }
    }

    @FXML
    public void close() {
        policeStage.close();
    }

}
