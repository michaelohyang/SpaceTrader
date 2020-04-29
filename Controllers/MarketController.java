package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import classes.*;

//import javax.xml.soap.Text;

public class MarketController {
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private Text welcome;
    @FXML
    private ListView box;
    @FXML
    private Label item;
    @FXML
    private Label price;
    @FXML
    private Button back;
    @FXML
    private Button buy;
    @FXML
    private Button sell;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;
    @FXML
    private CheckBox check4;
    @FXML
    private CheckBox check5;
    @FXML
    private Text marketItem1;
    @FXML
    private Text marketItem2;
    @FXML
    private Text marketItem3;
    @FXML
    private Text marketItem4;
    @FXML
    private Text marketItem5;
    @FXML
    private Text marketItem6;
    @FXML
    private Text buyPrice1;
    @FXML
    private Text buyPrice2;
    @FXML
    private Text buyPrice3;
    @FXML
    private Text buyPrice4;
    @FXML
    private Text buyPrice5;
    @FXML
    private Text buyPrice6;
    @FXML
    private Text sellPrice1;
    @FXML
    private Text sellPrice2;
    @FXML
    private Text sellPrice3;
    @FXML
    private Text sellPrice4;
    @FXML
    private Text sellPrice5;
    @FXML
    private Label gold;
    @FXML
    private int money;
    @FXML
    private Spinner<Integer> spinner1;
    @FXML
    private Spinner<Integer> spinner2;
    @FXML
    private Spinner<Integer> spinner3;
    @FXML
    private Spinner<Integer> spinner4;
    @FXML
    private Spinner<Integer> spinner5;
    @FXML
    private Text amount1;
    @FXML
    private Text amount2;
    @FXML
    private Text amount3;
    @FXML
    private Text amount4;
    @FXML
    private Text amount5;
    @FXML
    private static Stage stage;
    @FXML
    private static Stage newStage;
    @FXML
    private Region region;
    @FXML
    private Label tooMuch;
    @FXML
    private Label notEnough;
    @FXML
    private Label notEnoughCargo;
    @FXML
    private Label cargo;
    @FXML
    private Button backBtn;
    @FXML
    private Button winBtn;
    @FXML
    private int availableCargo;
    @FXML
    private int totalCargo;
    @FXML
    private int remainingCargo = 25;

    private static ArrayList<MarketController> items = new ArrayList<>();

    private HashMap<String, Integer> market = new HashMap<>();
    private int[] buyPrice = new int[]{0, 0, 0, 0, 0};
    private int[] sellPrice = new int[]{0, 0, 0, 0, 0};
    private String[] markets = new String[]{"Food", "Iron", "Oil", "Circuit", "Dark Matter"};
    private int[] bigArr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int charisma;


    @FXML
    private void openGuiStage() {
        //GameGuiController.hideMarket();
        backBtn.getScene().getWindow().hide();
        SetupSummaryController.showGuiStage();
    }

    @FXML
    private void toWinScreen() throws Exception {
        if (Ship.getGold() > 10) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "../fxml/EndGameScreen.fxml"));
            Parent gameUI = (Parent) loader.load();
            backBtn.getScene().getWindow().hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            newStage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "../fxml/buyPrivError.fxml"));
            Parent aGameUI = (Parent) loader.load();
            newStage = new Stage();
            newStage.setScene(new Scene(aGameUI));
            newStage.show();
        }
    }


    public void reshowMarket() {
        ((Stage) backBtn.getScene().getWindow()).show();
    }

    /**
     * @param map the hashmap with the items
     * @return the hashmap
     */
    public HashMap<String, Integer> initHashMap(HashMap<String, Integer> map) {
        map.put("Food", 10);
        map.put("Iron", 20);
        map.put("Oil", 50);
        map.put("Circuit", 100);
        map.put("Dark Matter", 300);
        return map;
    }

    /**
     * Will initialize the stage
     * @param goldNum takes in the credit value
     * @param region  current region
     * @param charisma merchant skill
     */
    public void initialize(int goldNum, Region region, int charisma) {
        this.money = goldNum;
        gold.setText(Integer.toString(Ship.getGold()));
        cargo.setText(Integer.toString(Ship.getCargoCapacity()));
        this.region = region;
        int[] prices = priceCalculator(charisma, initHashMap(market));
        this.charisma = charisma;
        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> valueFactory3 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> valueFactory4 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> valueFactory5 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner1.setValueFactory(valueFactory1);
        spinner2.setValueFactory(valueFactory2);
        spinner3.setValueFactory(valueFactory3);
        spinner4.setValueFactory(valueFactory4);
        spinner5.setValueFactory(valueFactory5);
        amount1.setText(String.valueOf(Ship.inventory.get("Food")));
        amount2.setText(String.valueOf(Ship.inventory.get("Iron")));
        amount3.setText(String.valueOf(Ship.inventory.get("Oil")));
        amount4.setText(String.valueOf(Ship.inventory.get("Circuit")));
        amount5.setText(String.valueOf(Ship.inventory.get("Dark Matter")));
        marketItem6.setText("Space Trader Privilege");
        buyPrice6.setText("10");
        marketItem6.setVisible(false);
        buyPrice6.setVisible(false);
        winBtn.setVisible(false);
        if (region.getRegionTechLevel() == "Agricultural Era") {
            marketItem1.setText("Food");
            marketItem2.setText("N/A");
            marketItem2.setVisible(false);
            marketItem3.setText("N/A");
            marketItem3.setVisible(false);
            marketItem4.setText("N/A");
            marketItem4.setVisible(false);
            marketItem5.setText("N/A");
            marketItem5.setVisible(false);
            buyPrice1.setText(Integer.toString(prices[0]));
            buyPrice2.setText("N/A");
            buyPrice2.setVisible(false);
            buyPrice3.setText("N/A");
            buyPrice3.setVisible(false);
            buyPrice4.setText("N/A");
            buyPrice4.setVisible(false);
            buyPrice5.setText("N/A");
            buyPrice5.setVisible(false);
            sellPrice1.setText(Integer.toString(prices[5]));
            sellPrice2.setText("0");
            sellPrice2.setVisible(false);
            sellPrice3.setText("0");
            sellPrice3.setVisible(false);
            sellPrice4.setText("0");
            sellPrice4.setVisible(false);
            sellPrice5.setText("0");
            sellPrice5.setVisible(false);
            amount2.setVisible(false);
            amount3.setVisible(false);
            amount4.setVisible(false);
            amount5.setVisible(false);
            check2.setVisible(false);
            check3.setVisible(false);
            check4.setVisible(false);
            check5.setVisible(false);
            spinner2.setVisible(false);
            spinner3.setVisible(false);
            spinner4.setVisible(false);
            spinner5.setVisible(false);
        } else if (region.getRegionTechLevel() == "Medieval Era") {
            marketItem1.setText("Food");
            marketItem2.setText("Iron");
            marketItem3.setText("N/A");
            marketItem3.setVisible(false);
            marketItem4.setText("N/A");
            marketItem4.setVisible(false);
            marketItem5.setText("N/A");
            marketItem5.setVisible(false);
            buyPrice1.setText(Integer.toString(prices[0]));
            buyPrice2.setText(Integer.toString(prices[1]));
            buyPrice3.setText("N/A");
            buyPrice3.setVisible(false);
            buyPrice4.setText("N/A");
            buyPrice4.setVisible(false);
            buyPrice5.setText("N/A");
            buyPrice5.setVisible(false);
            sellPrice1.setText(Integer.toString(prices[5]));
            sellPrice2.setText(Integer.toString(prices[6]));
            sellPrice3.setText("0");
            sellPrice3.setVisible(false);
            sellPrice4.setText("0");
            sellPrice4.setVisible(false);
            sellPrice5.setText("0");
            sellPrice5.setVisible(false);
            amount3.setVisible(false);
            amount4.setVisible(false);
            amount5.setVisible(false);
            check3.setVisible(false);
            check4.setVisible(false);
            check5.setVisible(false);
            spinner3.setVisible(false);
            spinner4.setVisible(false);
            spinner5.setVisible(false);
        } else if (region.getRegionTechLevel() == "Industrial Era") {
            marketItem1.setText("Food");
            marketItem2.setText("Iron");
            marketItem3.setText("Oil");
            marketItem4.setText("N/A");
            marketItem4.setVisible(false);
            marketItem5.setText("N/A");
            marketItem5.setVisible(false);
            buyPrice1.setText(Integer.toString(prices[0]));
            buyPrice2.setText(Integer.toString(prices[1]));
            buyPrice3.setText(Integer.toString(prices[2]));
            buyPrice4.setText("N/A");
            buyPrice4.setVisible(false);
            buyPrice5.setText("N/A");
            buyPrice5.setVisible(false);
            sellPrice1.setText(Integer.toString(prices[5]));
            sellPrice2.setText(Integer.toString(prices[6]));
            sellPrice3.setText(Integer.toString(prices[7]));
            sellPrice4.setText("0");
            sellPrice4.setVisible(false);
            sellPrice5.setText("0");
            sellPrice5.setVisible(false);
            amount4.setVisible(false);
            amount5.setVisible(false);
            check4.setVisible(false);
            check5.setVisible(false);
            spinner4.setVisible(false);
            spinner5.setVisible(false);
        } else if (region.getRegionTechLevel() == "Modern Era") {
            marketItem1.setText("Food");
            marketItem2.setText("Iron");
            marketItem3.setText("Oil");
            marketItem4.setText("Circuits");
            marketItem5.setText("N/A");
            marketItem5.setVisible(false);
            buyPrice1.setText(Integer.toString(prices[0]));
            buyPrice2.setText(Integer.toString(prices[1]));
            buyPrice3.setText(Integer.toString(prices[2]));
            buyPrice4.setText(Integer.toString(prices[3]));
            buyPrice5.setText("N/A");
            buyPrice5.setVisible(false);
            sellPrice1.setText(Integer.toString(prices[5]));
            sellPrice2.setText(Integer.toString(prices[6]));
            sellPrice3.setText(Integer.toString(prices[7]));
            sellPrice4.setText(Integer.toString(prices[8]));
            sellPrice5.setText("0");
            sellPrice5.setVisible(false);
            check5.setVisible(false);
            spinner5.setVisible(false);
            amount5.setVisible(false);
        } else if (region.getRegionTechLevel() == "Galactic Era") {
            marketItem1.setText("Food");
            marketItem2.setText("Iron");
            marketItem3.setText("Oil");
            marketItem4.setText("Circuits");
            marketItem5.setText("Dark Matter");
            buyPrice1.setText(Integer.toString(prices[0]));
            buyPrice2.setText(Integer.toString(prices[1]));
            buyPrice3.setText(Integer.toString(prices[2]));
            buyPrice4.setText(Integer.toString(prices[3]));
            buyPrice5.setText(Integer.toString(prices[4]));
            sellPrice1.setText(Integer.toString(prices[5]));
            sellPrice2.setText(Integer.toString(prices[6]));
            sellPrice3.setText(Integer.toString(prices[7]));
            sellPrice4.setText(Integer.toString(prices[8]));
            sellPrice5.setText(Integer.toString(prices[9]));
        }
        if (region.getWinning()) {
            marketItem6.setVisible(true);
            buyPrice6.setVisible(true);
            winBtn.setVisible(true);
        }

    }

    /**
     * Price calculator with algorithm
     *
     * @param charisma the merchant skill
     * @param map      the hashmap of the items
     * @return returns the array of the calculated prices
     */
    public int[] priceCalculator(int charisma, HashMap<String, Integer> map) {
        Random rand = new Random();
        for (int j = 0; j < buyPrice.length; j++) {
            int baseprice = map.get(markets[j]);
            double base = Math.pow(baseprice, 0.95 + (Math.random() / 10));
            double div = 1 + ((charisma + 1) / 26);
            int item = (int) (base / div);
            buyPrice[j] = item;
        }

        for (int i = 0; i < 5; i++) {
            sellPrice[i] = (int) (buyPrice[i] * (0.90 + Math.random() / 2));
        }
        for (int i = 0; i < 5; i++) {
            bigArr[i] = buyPrice[i];
        }
        for (int i = 5; i < 10; i++) {
            bigArr[i] = sellPrice[i - 5];
        }

        return bigArr;
    }

    @FXML
    private void buy() {
        availableCargo = Integer.parseInt(cargo.getText());
        int total = 0;
        totalCargo = 0;
        if (spinner1.getValue() > 0) {
            total = total + Integer.parseInt(buyPrice1.getText()) * spinner1.getValue();
            totalCargo += spinner1.getValue();
        }
        if (spinner2.getValue() > 0) {
            total = total + Integer.parseInt(buyPrice2.getText()) * spinner2.getValue();
            totalCargo += spinner2.getValue();
        }
        if (spinner3.getValue() > 0) {
            total = total + Integer.parseInt(buyPrice3.getText()) * spinner3.getValue();
            totalCargo += spinner3.getValue();
        }
        if (spinner4.getValue() > 0) {
            total = total + Integer.parseInt(buyPrice4.getText()) * spinner4.getValue();
            totalCargo += spinner4.getValue();
        }
        if (spinner5.getValue() > 0) {
            total = total + Integer.parseInt(buyPrice5.getText()) * spinner5.getValue();
            totalCargo += spinner5.getValue();
        }
        if (Ship.getGold() >= total && totalCargo <= availableCargo) {
            notEnoughCargo.setVisible(false);
            notEnough.setVisible(false);
            Ship.setGold(Ship.getGold() - total);
            Ship.setCargoCap(availableCargo - totalCargo);
            gold.setText("" + Ship.getGold());
            if (spinner1.getValue() > 0) {
                int quantity = spinner1.getValue();
                String name = marketItem1.getText();
                Ship.inventory.put(name, quantity);
                int newAmount = Integer.parseInt(amount1.getText()) + quantity;
                amount1.setText("" + newAmount);
            }
            if (spinner2.getValue() > 0) {
                int quantity = spinner2.getValue();
                String name = marketItem2.getText();
                Ship.inventory.put(name, quantity);
                int newAmount = Integer.parseInt(amount2.getText()) + quantity;
                amount2.setText("" + newAmount);
            }
            if (spinner3.getValue() > 0) {
                int quantity = spinner3.getValue();
                String name = marketItem3.getText();
                Ship.inventory.put(name, quantity);
                int newAmount = Integer.parseInt(amount3.getText()) + quantity;
                amount3.setText("" + newAmount);
            }
            if (spinner4.getValue() > 0) {
                int quantity = spinner4.getValue();
                String name = marketItem4.getText();
                Ship.inventory.put(name, quantity);
                int newAmount = Integer.parseInt(amount4.getText()) + quantity;
                amount4.setText("" + newAmount);
            }
            if (spinner5.getValue() > 0) {
                int quantity = spinner5.getValue();
                String name = marketItem5.getText();
                Ship.inventory.put(name, quantity);
                int newAmount = Integer.parseInt(amount5.getText()) + quantity;
                amount5.setText("" + newAmount);
            }
            cargo.setText("" + (availableCargo - totalCargo));
        } else if (totalCargo > availableCargo) {
            notEnoughCargo.setVisible(true);
        } else if (Ship.getGold() < total) {
            notEnough.setVisible(true);
        }
        gold.setText("" + Ship.getGold());
    }

    @FXML
    private void sell() {
        notEnough.setVisible(false);
        notEnoughCargo.setVisible(false);
        availableCargo = Integer.parseInt(cargo.getText());
        totalCargo = 0;
        boolean yes1 = false;
        boolean yes2 = false;
        boolean yes3 = false;
        boolean yes4 = false;
        boolean yes5 = false;
        int total = 0;
        if (spinner1.getValue() <= Integer.parseInt(amount1.getText())) {
            yes1 = true;
            int price = Integer.parseInt(sellPrice1.getText());
            int quantity = spinner1.getValue();
            String name = marketItem1.getText();
            total += price * quantity;
            totalCargo += spinner1.getValue();
        }

        if (spinner2.getValue() <= Integer.parseInt(amount2.
                getText()) && sellPrice2.getText() != "N/A") {
            yes2 = true;
            int price = Integer.parseInt(sellPrice2.getText());
            int quantity = spinner2.getValue();
            String name = marketItem2.getText();
            total += price * quantity;
            totalCargo += spinner2.getValue();
        }
        if (spinner3.getValue() <= Integer.parseInt(amount3.
                getText()) && sellPrice3.getText() != "N/A") {
            yes3 = true;
            int price = Integer.parseInt(sellPrice3.getText());
            int quantity = spinner3.getValue();
            String name = marketItem3.getText();
            total += price * quantity;
            totalCargo += spinner3.getValue();
        }
        if (spinner4.getValue() <= Integer.parseInt(amount4
                .getText()) && sellPrice4.getText() != "N/A") {
            yes4 = true;
            int price = Integer.parseInt(sellPrice4.getText());
            int quantity = spinner4.getValue();
            String name = marketItem4.getText();
            total += price * quantity;
            totalCargo += spinner4.getValue();
        }
        if (spinner5.getValue() <= Integer.parseInt(amount5
                .getText()) && sellPrice5.getText() != "N/A") {
            yes5 = true;
            int price = Integer.parseInt(sellPrice5.getText());
            int quantity = spinner5.getValue();
            String name = marketItem5.getText();
            total += price * quantity;
            totalCargo += spinner5.getValue();
        }
        if (yes1 && yes2 && yes3 && yes4 && yes5) {
            tooMuch.setVisible(false);
            int newAmount1 = Integer.parseInt(amount1.getText()) - spinner1.getValue();
            amount1.setText("" + newAmount1);
            Ship.inventory.put("Food", Integer.valueOf(amount1.getText()));
            int newAmount2 = Integer.parseInt(amount2.getText()) - spinner2.getValue();
            amount2.setText("" + newAmount2);
            Ship.inventory.put("Iron", Integer.valueOf(amount2.getText()));
            int newAmount3 = Integer.parseInt(amount3.getText()) - spinner3.getValue();
            amount3.setText("" + newAmount3);
            Ship.inventory.put("Oil", Integer.valueOf(amount3.getText()));
            int newAmount4 = Integer.parseInt(amount4.getText()) - spinner4.getValue();
            amount4.setText("" + newAmount4);
            Ship.inventory.put("Circuit", Integer.valueOf(amount4.getText()));
            int newAmount5 = Integer.parseInt(amount5.getText()) - spinner5.getValue();
            amount5.setText("" + newAmount5);
            Ship.inventory.put("Dark Matter", Integer.valueOf(amount5.getText()));
            Ship.setGold(Ship.getGold() + total);


            gold.setText("" + Ship.getGold());
            spinner1.getValueFactory().setValue(0);
            spinner2.getValueFactory().setValue(0);
            spinner3.getValueFactory().setValue(0);
            spinner4.getValueFactory().setValue(0);
            spinner5.getValueFactory().setValue(0);


        } else {
            String error = "";
            if (!yes1) {
                error += marketItem1.getText() + ", ";
            }
            if (!yes2) {
                error += marketItem2.getText() + ", ";
            }
            if (!yes3) {
                error += marketItem3.getText() + ", ";
            }
            if (!yes4) {
                error += marketItem4.getText() + ", ";
            }
            if (!yes5) {
                error += " and " + marketItem5.getText();
            }
            error = error.substring(0, error.length() - 2);
            tooMuch.setText("You don't have enough " + error + " to sell");
            tooMuch.setVisible(true);
        }
        Ship.setCargoCap(availableCargo + totalCargo);
        cargo.setText("" + (availableCargo + totalCargo));
    }

}