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

public class CharUpgradeController {
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
    private Button singingLongRifle;
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
    private static ArrayList<CharUpgradeController> items = new ArrayList<>();


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

    public static void addToArray(CharUpgradeController item) {
        items.add(item);
    }

    public void setItemName(String name) {
        itemName = name;
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
        CharacterController.showGuiStage();
        GameGuiController.closeUpgrade();
    }
    public static Stage getPurchaseStage() {
        return newStage;
    }
    @FXML
    private void proceedToPurchaseGold() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("/pictures/goldone.png", "Golden Repeater",
                    "An ancient rifle", 1000, 500, 400, 300, 200, 1000);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/gold2.png", "Singing Longrifle",
                    "An ancient rifle", 800, 400, 400, 300, 200, 800);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/rare3.png", "Solitude's Carbine",
                    "An ancient rifle", 700, 200, 300, 400, 200, 750);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/normal2.png", "Courier Of Misery",
                    "An ancient weapon", 500, 200, 200, 200, 200, 500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/normal.png", "Suppressor Of Ashes",
                    "An ancient weapon", 500, 200, 200, 200, 200, 500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/ring.png", "The Handsome Vow",
                    "An ancient ring", 100, 1000, 1000, 500, 100, 1500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/ring2.png", "The Devoted Tear",
                    "An ancient ring", 200, 200, 200, 100, 100, 1500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/necklace.png", "The Serene Trinket",
                    "An ancient necklace", 200, 1000, 1000, 300, 200, 1500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/necklace2.png", "The Lustrous Star",
                    "An ancient necklace", 500, 500, 500, 500, 500, 1500);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase.fxml"));
            Parent gameUI = (Parent) loader.load();
            PurchaseController purchaseController = loader.getController();
            purchaseController.initialize("sample/bag.png", "The Mystery Bag",
                    "? ? ? ? ?", 1000, 1000, 1000, 1000, 1000, 2000);
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            GameGuiController.closeUpgrade();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

