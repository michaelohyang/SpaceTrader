package controllers;

import classes.Player;
import classes.Ship;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class BanditController {
    @FXML
    private Label demand;
    @FXML
    private Button pay;
    @FXML
    private Button flee;
    @FXML
    private Button fight;
    @FXML
    private Label payAmount;
    @FXML
    private AnchorPane background;
    @FXML
    private Label credit;
    @FXML
    private AnchorPane showShipdestory;
    @FXML
    private AnchorPane fleeSuccessful;
    @FXML
    private Label fleeLabe;
    @FXML
    private AnchorPane fleeDamaged;
    @FXML
    private Label dmg;
    @FXML
    private AnchorPane showWin;
    @FXML
    private Label crd;
    @FXML
    private Label dmgLabel;
    @FXML
    private AnchorPane lostGold;
    @FXML
    private Label lostAmount;
    @FXML
    private AnchorPane lostCargo;
    @FXML
    private static Stage newStage;

    // non-FXML fields
    private Stage popStage = new Stage();
    private Scene temp2;
    private static String diff;
    private static GameGuiController gameGuiController;

    @FXML
    private void toBandit() {
        background.setVisible(false);
        demand.setText("Pay off " + diff +  " then I'll let you live.");
        payAmount.setText("-$" + diff);
        pay.setText("pay(-" + diff + ")");
        credit.setText("Credit: " + Ship.getGold());
        credit.setVisible(true);
    }
    @FXML
    private void pay() throws IOException {
        int demand = Integer.parseInt(diff);
        if (demand > Ship.getGold() && !Ship.inventoryIsEmpty()) {
            Ship.clearInventory();
            if (Player.getShip().size() > 0) {
                Player.getShip().get(0).setCargoCap(25);
            }
            lostCargo.setVisible(true);
        } else if (demand > Ship.getGold() && Ship.inventoryIsEmpty()) {
            if (Player.getShip().size() == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/gameEnd.fxml"));
                Parent finished = (Parent) loader.load();
                Stage newstage = new Stage();
                newstage.setScene(new Scene(finished));
                newstage.initModality(Modality.APPLICATION_MODAL);
                newstage.initOwner(credit.getScene().getWindow());
                newstage.show();
            } else if (Player.getShip().get(0).getHealth() <= 0) {
                showShipdestory.setVisible(true);
                Player.getShip().remove(0);
            }
            int health = Player.getShip().get(0).getHealth();
            Player.getShip().get(0).setHealth((health - Integer.parseInt(diff)) * 3 / 4);
            fleeDamaged.setVisible(true);
        } else {
            Ship.setGold(Ship.getGold() - demand);
            if (Ship.getGold() < 0) {
                Ship.setGold(0);
            }
            lostGold.setVisible(true);
            lostAmount.setText("You've paid " + demand + " to the Bandit");
        }
        gameGuiController.updateHealth();
    }

    @FXML
    private void flee() throws IOException {
        int fleeBonus = Integer.parseInt(Ship.getStrength())
                + Integer.parseInt(Ship.getAgility())
                + Integer.parseInt(Ship.getEngineering()) * 2
                + Integer.parseInt(Ship.getIntelligence()) * 2
                + Integer.parseInt(Ship.getCharisma());
        int chance = (int) (Math.random() * 200);
        if (fleeBonus + chance > 100) {
            Player.getShip().get(0).setFuelCapacity(Player.getShip().get(0).getFuelCapacity()
                    - Integer.parseInt(diff) * 3 / 9);
            fleeSuccessful.setVisible(true);

        } else {
            Ship.setGold(0);
            Player.getShip().get(0).setHealth(Player.getShip().get(0).getHealth()
                    - Integer.parseInt(diff) * 3 / 4);
            if (Player.getShip().get(0).getHealth() <= 0) {
                if (Player.getShip().size() == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("../fxml/gameEnd.fxml"));
                    Parent finished = (Parent) loader.load();
                    Stage newstage = new Stage();
                    newstage.setScene(new Scene(finished));
                    newstage.initModality(Modality.APPLICATION_MODAL);
                    newstage.initOwner(credit.getScene().getWindow());
                    newstage.show();
                } else {
                    Player.getShip().remove(0);

                    showShipdestory.setVisible(true);

                    gameGuiController.updateHealth();
                }
            } else {
                fleeDamaged.setVisible(true);

                dmg.setText("Damage taken：" + (Integer.parseInt(diff) * 3 / 4));
            }
        }
        gameGuiController.updateHealth();
    }


    @FXML
    private void fight() throws IOException {
        int fightBonus = Integer.parseInt(Ship.getStrength()) * 3
                + Integer.parseInt(Ship.getAgility())
                + Integer.parseInt(Ship.getEngineering())
                + Integer.parseInt(Ship.getIntelligence())
                + Integer.parseInt(Ship.getCharisma()) * 3;
        int chance = (int) (Math.random() * 200);
        if (fightBonus + chance > 130) {

            showWin.setVisible(true);
            crd.setText("+" + Integer.parseInt(diff) * 3 / 2 + " credits");
            Ship.setGold(Integer.parseInt(diff) * 3 / 2);

        } else {
            Ship.setGold(0);
            if (Player.getShip().size() > 0) {
                Player.getShip().get(0).setHealth(Player.getShip().get(0).getHealth()
                        - Integer.parseInt(diff) * 3 / 4);
            }
            if (Player.getShip().get(0).getHealth() <= 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "../fxml/EndGameScreen.fxml"));
                Parent gameUI = (Parent) loader.load();
                pay.getScene().getWindow().hide();
                newStage = new Stage();
                newStage.setScene(new Scene(gameUI));
                newStage.show();
            } else {
                fleeDamaged.setVisible(true);
                fleeLabe.setVisible(false);
                dmgLabel.setVisible(true);
                dmg.setText("Damage taken：" + (Integer.parseInt(diff) * 3 / 4));
            }
        }
        gameGuiController.updateHealth();
    }

    @FXML
    private void close() {
        showShipdestory.setVisible(false);
        fleeSuccessful.setVisible(false);
        fleeDamaged.setVisible(false);
        showWin.setVisible(false);
        lostGold.setVisible(false);
        lostCargo.setVisible(false);
        credit.getScene().getWindow().hide();
    }

    @FXML
    private void endGame() {
        System.exit(0);
    }


    public static void getGameGuiController(GameGuiController newController) {
        gameGuiController = newController;
    }
    public static GameGuiController getGui() {
        return gameGuiController;
    }
    public void initLabels(String temp) {
        diff = temp;
    }
    public void getScene(Scene scene) {
        temp2 = scene;
    }
    public void setPopBandit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../fxml/Bandit.fxml"));

        Parent popUp = (Parent) loader.load();
        popStage = new Stage();
        popStage.setScene(new Scene(popUp));
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.initOwner(temp2.getWindow());
        popStage.show();

    }
}
