package controllers;

import javafx.fxml.FXML;
import classes.Ship;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class PurchaseController {

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label cost;
    @FXML
    private TextArea description;
    @FXML
    private Label str;
    @FXML
    private Label agi;
    @FXML
    private Label intell;
    @FXML
    private Label cha;
    @FXML
    private Label eng;

    private static boolean enoughMoney = false;
    private UpgradeMarketController newItem = new UpgradeMarketController();
    private String strength;
    private String agil;
    private String chara;
    private String intel;
    private String engi;
    private String gold;
    private Stage newstage;
    private Stage purchaseStage;
    private static Stage errorStage;


    public void initialize(String url, String name, String description, int str,
                           int agi, int intell, int cha, int eng, int cost) {

        image.setImage(new Image(url));
        this.name.setText(name);
        this.description.setText(description);
        this.cost.setText("- $" + cost);
        this.str.setText("+ " + str);
        this.agi.setText("+ " + agi);
        this.intell.setText("+ " + intell);
        this.cha.setText("+ " + cha);
        this.eng.setText("+ " + eng);
        if (Ship.getGold() >= cost) {
            newItem.setItemName(name);
            newItem.setAgi(agi);
            newItem.setDescription(description);
            newItem.setEng(eng);
            newItem.setIntel(intell);
            newItem.setStr(str);
            newItem.setUrlName(url);
            newItem.setCha(cha);
            newItem.setCost(cost);
            enoughMoney = true;
        } else {
            enoughMoney = false;
        }
    }

    @FXML
    private void closePurchase() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/UpgradeMarket.fxml"));
        Parent gameUI = (Parent) loader.load();

        UpgradeMarketController charUpgrade = loader.getController();

        charUpgrade.initialize(Ship.getStrength(), Ship.getAgility(), Ship.getIntelligence(),
                Ship.getCharisma(), Ship.getEngineering(), ("" + Ship.getGold()));
        //purchaseStage = new Stage();
        GameGuiController.getStage().setScene(new Scene(gameUI));
        SetupSummaryController.hideGuiStage();
        GameGuiController.showUpgrade();
        UpgradeMarketController.closePurchase();
        UpgradeMarketController.closeCharUp();
    }
    @FXML
    private void confirmPurchase() throws Exception {
        if (enoughMoney) {
            UpgradeMarketController.addToArray(newItem);

            Ship.setStrength("" + (Integer.parseInt(Ship.getStrength()) + newItem.getStr()));
            Ship.setCharisma("" + (Integer.parseInt(Ship.getCharisma()) + newItem.getCha()));
            Ship.setEngineering("" + (Integer.parseInt(Ship.getEngineering()) + newItem.getEng()));
            Ship.setIntelligence("" + (Integer.parseInt(Ship.getIntelligence()) + newItem
                    .getIntel()));
            Ship.setAgility("" + (Integer.parseInt(Ship.getAgility()) + newItem.getAgi()));
            Ship.setGold(Ship.getGold() - newItem.getCost());
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradeMarket.fxml"));
            Parent gameUI = (Parent) loader.load();
            UpgradeMarketController charUpgrade = loader.getController();
            charUpgrade.initialize(Ship.getStrength(), Ship.getAgility(), Ship.getIntelligence(),
                    Ship.getCharisma(), Ship.getEngineering(), ("" + Ship.getGold()));
            GameGuiController.getStage().setScene(new Scene(gameUI));
            SetupSummaryController.hideGuiStage();
            GameGuiController.showUpgrade();
            UpgradeMarketController.closePurchase();
            UpgradeMarketController.closeCharUp();

        } else {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradePurchaseError.fxml"));
            Parent gameUI = (Parent) loader.load();
            errorStage = new Stage();
            errorStage.setScene(new Scene(gameUI));
            image.getScene().getWindow().hide();
            errorStage.show();

        }

    }

    @FXML
    private void backToPurchase() {
        errorStage.close();
        UpgradeMarketController.showCharUp();

    }

    public void saveData(String str, String agi, String intell,
                         String cha, String eng, String gold) {
        strength = str;
        agil = agi;
        intel = intell;
        chara = cha;
        engi = eng;
        this.gold = gold.substring(1);
    }
}
