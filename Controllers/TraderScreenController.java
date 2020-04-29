package controllers;


import classes.Item;
import classes.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.text.Text;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.HashMap;
import java.util.Random;
import classes.*;


public class TraderScreenController {
    @FXML
    private Text wordID;
    @FXML
    private Label creditLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Button buyID;
    @FXML
    private Button robID;
    @FXML
    private Button negotiateID;
    @FXML
    private Text robDescription;
    @FXML
    private Text buyDescription;
    @FXML
    private Text negotiationDescription;
    @FXML
    private Button leaveButton;
    @FXML
    private Stage newStage;

    //non-FXML fields
    private HashMap<String, Integer> tradeMap;
    int randNum = getRandomNumberInRange(0, 4);
    private TraderScreenController trader;
    private String[] tradingArr;
    private int tradingItemNum;
    private int tradingItemCost;
    private String tradingItem;
    private int currentGold = Ship.getGold();
    private int currentCargo = Ship.getCargoCapacity();
    private static GameGuiController gameGuiController;
    private int averageCost;
    private Stage popStage = new Stage();
    private Scene temp2;
    private static boolean isNegotiated = true;

    /**
     * initialize the hashmap of items
     * @param map the array we are taking in
     * @return the hashmap of the items
     */
    public HashMap<String, Integer> initTradeMap(HashMap<String, Integer> map) {
        map.put("Food", 9);
        map.put("Iron", 18);
        map.put("Oil", 46);
        map.put("Circuit", 94);
        map.put("Dark Matter", 292);
        return map;
    }
    /**
     * sets the dialogue of the trader, displays the main speech text in the TraderScreen
     * @param itemArr the array of items
     * @param map the map of the original array
     * @param num the number of items
     */
    public void setTraderDialogue(HashMap<String, Integer> map, int num, String[] itemArr) {
        tradeMap = map;
        tradingItemNum = num;
        tradingArr = itemArr;
        tradeMap = initTradeMap(tradeMap);
        tradingItem = tradingArr[randNum];
        tradingItemCost = tradeMap.get(tradingItem);
        // tradingItem Price calculation
        double base = Math.pow(tradingItemCost, 0.95 + (Math.random() / 10));
        double div =  2 + ((Integer.parseInt(Ship.getCharisma()) / 26));
        averageCost = (int) (base / div);
        averageCost *= tradingItemNum;
        this.wordID.setText("The sketchy trader wants to trade " + tradingItemNum
                + " " + tradingItem + " with you for " + averageCost + " gold coins");
        this.creditLabel.setText("" + currentGold);
        this.healthLabel.setText("" + Ship.getHealth());
        buyDescription.setVisible(false);
        robDescription.setVisible(false);
        negotiationDescription.setVisible(false);
    }

    /**
     * buy items from the trader
     * checks for cargo space and current gold
     */
    @FXML
    public void buyItems() {
        buyDescription.setVisible(true);
        robDescription.setVisible(false);
        negotiationDescription.setVisible(false);
        if (currentGold > averageCost && (currentCargo - tradingItemNum) >= 0) {
            Ship.setGold(currentGold - averageCost);
            currentGold = Ship.getGold();
            Ship.inventory.put(tradingItem, Ship.inventory.get(tradingItem) + tradingItemNum);
            Ship.setCargoCap(currentCargo - tradingItemNum);
            currentCargo = Ship.getCargoCapacity();
            this.buyDescription.setText("You successfully bought " + tradingItemNum
                    + " " + tradingItem + " for " + averageCost + " gold coins");

        } else {
            this.buyDescription.setText("You don't have enough money or cargo space!!!");
        }
        this.creditLabel.setText("" + currentGold + "");
    }

    /**
     * attempts to rob the trader
     * success dependent on strength points
     * if failed, ship health damaged (updateHealth)
     * @throws IOException if its not found
     */
    @FXML
    public void robItems() throws IOException {
        buyDescription.setVisible(false);
        negotiationDescription.setVisible(false);
        robDescription.setVisible(true);
        Item aItem = new Item(tradingItem, tradingItemNum + randNum, tradingItemCost);
        int traderStrength = (int) (Math.random() * 20) + 5;
        int playerStrength = Integer.parseInt(Ship.getStrength());
        if (playerStrength > traderStrength) {
            Ship.inventory.put(tradingItem, Ship.inventory.get(tradingItem) + tradingItemNum);
            Ship.setCargoCap(currentCargo - (tradingItemNum + randNum));
            currentCargo = Ship.getCargoCapacity();
            this.robDescription.setText("You've successfully robbed the Trader! You've gained "
                    + (tradingItemNum + randNum) + " " + tradingItem);
        } else {
            Ship.setHealth(Ship.getHealth() - 20);
            gameGuiController.updateHealth();
            this.robDescription
                    .setText("You've failed to rob the Trader! You've taken 20 POINT DAMAGE!!!");
            this.healthLabel.setText("" + Ship.getHealth());
            if (Ship.getHealth() <= 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/gameEnd.fxml"));
                Parent finished = (Parent) loader.load();
                Stage newstage = new Stage();
                newstage.setScene(new Scene(finished));
                newstage.initModality(Modality.APPLICATION_MODAL);
                newstage.initOwner(creditLabel.getScene().getWindow());
                newstage.show();
            }
        }
        if (Ship.getHealth() <= 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "../fxml/EndGameScreen.fxml"));
            Parent gameUI = (Parent) loader.load();
            leaveButton.getScene().getWindow().hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            newStage.show();
        }
    }


    /**
     * negotiates the price with the trader
     * if successful, item price drops
     * if not successful, item price increases
     */
    @FXML
    public void negotiateItems() {
        buyDescription.setVisible(false);
        robDescription.setVisible(false);
        negotiationDescription.setVisible(true);
        int charisma = Integer.parseInt(Ship.getCharisma());
        int baseNum = 10;
        double base = Math.pow(baseNum, 0.9 + (Math.random() / 10));
        // higher charisma makes the number smaller
        double calculation = base / (charisma);
        this.negotiationDescription
                .setText("You cannot negotiate with the trader anymore!!!");
        if (isNegotiated) {
            if (calculation < 0.7) {
                averageCost = (averageCost / 2);
                this.negotiationDescription
                        .setText("You succeeded in the negotiation!!! Price dropped!");
            } else {
                averageCost = averageCost * 2;
                this.negotiationDescription
                        .setText("You failed in the negotiation!!! Price raised!");
            }
            this.wordID.setText("The sketchy trader wants to trade " + tradingItemNum
                    + " " + tradingItem + " with you for " + averageCost + " gold coins");

        }
        isNegotiated = false;
        this.negotiateID.setText("Cannot Negotiate");
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

    /**
     *  trader screen pop-up
     * @throws IOException some input errors
     * @param arr the array of items
     * @param map the map of the original array
     * @param num the number of items
     */
    public void openTraderPopUp(HashMap<String, Integer> map,
                                int num, String[] arr) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../fxml/TraderScreen.fxml"));
        Parent popUp = (Parent) loader.load();
        trader = loader.getController();
        trader.setTraderDialogue(map, num, arr);
        popStage = new Stage();
        popStage.setScene(new Scene(popUp));
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.initOwner(temp2.getWindow());
        popStage.show();
    }






    /**
     *  get the current scene
     * @param scene the scene we want
     */
    public void getScene(Scene scene) {
        temp2 = scene;
    }
    /**
     * back to the game GUI main Screen
     */
    public void backToEncounter() {
        Stage stage = (Stage) leaveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get GameGUI controller reference to update health
     * @param newController the new controller for gameGui
     */
    public static void getGameGuiController(GameGuiController newController) {
        gameGuiController = newController;
    }

}
