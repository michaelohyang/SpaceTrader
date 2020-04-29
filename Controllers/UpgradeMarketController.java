package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UpgradeMarketController {

    @FXML
    private Label showStrength;
    @FXML
    private Label showAgility;
    @FXML
    private Label showCharisma;
    @FXML
    private Label showIntelligence;
    @FXML
    private Label showEngineering;
    @FXML
    private Label showGold;
    @FXML
    private Button goldenRepeater;
    @FXML
    private Button singingLongrifle;
    @FXML
    private Button solitudesCarbine;
    @FXML
    private Button courierOfMisery;
    @FXML
    private Button suppressorOfAshes;
    @FXML
    private Button theHandsomeVow;
    @FXML
    private Button theDevotedTear;
    @FXML
    private Button theSereneTrinket;
    @FXML
    private Button theLustrousStar;
    @FXML
    private Button theMysteryBag;

    @FXML
    private static boolean isTime;
    private static Stage newStage;
    private String itemName;
    private String urlName;
    private String description;
    private int str;
    private int agi;
    private int cha;
    private int intel;
    private int eng;
    private static int gold;
    private static ArrayList<UpgradeMarketController> items = new ArrayList<>();
    private int cost;

    public void setShowStrength(String str) {
        showStrength.setText(str);
    }


    public void initialize(String str, String agi, String intell,
                           String cha, String eng, String gold) {
        this.showStrength.setText(str);
        this.showAgility.setText(agi);
        this.showIntelligence.setText(intell);
        this.showCharisma.setText(cha);
        this.showEngineering.setText(eng);
        this.showGold.setText(gold);
    }

    public void updateGold(int str, int agi, int intel, int cha, int eng, int cost) {
        gold = Integer.parseInt(showGold.getText()) - cost;
    }

    public static void addToArray(UpgradeMarketController item) {

        items.add(item);
    }

    public void setItemName(String name) {
        itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setUrlName(String name) {
        urlName = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStr(int str) {
        this.str = str;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }
    public void setIntel(int intel) {
        this.intel = intel;
    }
    public void setEng(int eng) {
        this.eng = eng;
    }
    public void setCha(int cha) {
        this.cha = cha;
    }

    @FXML
    private void showGuiStage() {
        SetupSummaryController.showGuiStage();
        GameGuiController.closeUpgrade();
    }
    public static Stage getPurchaseStage() {
        return newStage;
    }

    public int getStr() {
        return str;
    }

    public int getAgi() {
        return agi;
    }

    public int getIntel() {
        return intel;
    }

    public int getEng() {
        return eng;
    }

    public int getCha() {
        return cha;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getCost() {
        return cost;
    }

    @FXML
    private void proceedToPurchaseGold() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("/pictures/goldone.png", "Golden Repeater",
                    "An ancient rifle", 1, 5, 4, 3, 2, 100);
            purchaseController.saveData(showStrength.getText(), showAgility.getText(),
                    showIntelligence.getText(),
                    showCharisma.getText(),
                    showEngineering.getText(), showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void proceedToPurchaseSing() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/gold2.png", "Singing Longrifle",
                    "An ancient rifle", 8, 4, 4, 3, 2, 80);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/gold2.png", "Singing Longrifle",
                    "An ancient rifle", 0, 4,
                    4, 3, 2, 800);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseSol() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/rare3.png", "Solitude's Carbine",
                    "An ancient rifle", 7, 2, 3, 4, 2, 75);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/rare3.png", "Solitude's Carbine",
                    "An ancient rifle", 7, 2,
                    3, 4, 2, 750);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(),
                    showEngineering.getText(), showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void proceedToPurchaseCor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/normal2.png", "Courier Of Misery",
                    "An ancient weapon", 500, 200, 200, 200, 200, 500);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/normal2.png", "Courier Of Misery",
                    "An ancient weapon", 5, 2, 2,
                    2, 2, 5);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseSupp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/normal.png", "Suppressor Of Ashes",
                    "An ancient weapon", 5, 2, 2, 2, 2, 50);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/normal.png", "Suppressor Of Ashes",
                    "An ancient weapon", 5, 2, 2,
                    2, 2, 5);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseVow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/ring.png", "The Handsome Vow",
                    "An ancient ring", 1, 10, 10, 5, 1, 150);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/ring.png", "The Handsome Vow",
                    "An ancient ring", 1, 10, 10,
                    5, 10, 150);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseTear() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/ring2.png", "The Devoted Tear",
                    "An ancient ring", 200, 200, 200, 100, 100, 1500);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/ring2.png", "The Devoted Tear",
                    "An ancient ring", 2, 2, 2,
                    1, 1, 1500);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseTri() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/necklace.png", "The Serene Trinket",
                    "An ancient necklace", 200, 1000, 1000, 300, 200, 1500);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/necklace.png", "The Serene Trinket",
                    "An ancient necklace", 2, 10, 10,
                    3, 2, 1500);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseStar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/necklace2.png", "The Lustrous Star",
                    "An ancient necklace", 500, 500, 500, 500, 500, 1500);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/necklace2.png", "The Lustrous Star",
                    "An ancient necklace", 5, 5, 5, 5,
                    5, 1500);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPurchaseBag() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseDetails.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/bag.png", "The Mystery Bag",
                    "? ? ? ? ?", 10, 10, 10, 10, 10, 20);
            loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            gameUI = (Parent) loader.load();
            purchaseController = loader.getController();
            purchaseController.initialize("/pictures/bag.png", "The Mystery Bag",
                    "? ? ? ? ?", 10, 10, 10, 10,
                    10, 2000);
            purchaseController.saveData(showStrength.getText(),
                    showAgility.getText(), showIntelligence.getText(),
                    showCharisma.getText(), showEngineering.getText(),
                    showGold.getText());
            //some oldstage.hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closePurchase() {
        newStage.close();
    }
    public static void openePurchase() {
        newStage.show();
    }
    public static void showUpgrade() {
        GameGuiController.showUpgrade();
    }

    public static void closeCharUp() {
        newStage.close();
    }

    public static void showCharUp() {

        newStage.show();
    }

}



