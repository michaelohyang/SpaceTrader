package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import classes.Player;
import classes.Ship;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import classes.*;

public class GameGuiController {
    @FXML
    private Text regionName;
    @FXML
    private Text regionTechLevel;
    @FXML
    private Text regionLocation;
    @FXML
    private Text regionDescription;
    @FXML
    private classes.Player gamePlayer;
    @FXML
    private ListView regionlist;
    @FXML
    private ListView distancelist;
    @FXML
    private Text ship;
    @FXML
    private Label shipHealth;
    @FXML
    private static Stage newstage;
    @FXML
    private int credit;
    @FXML
    public static boolean cancelTravel = false;
    @FXML
    private Button upgrade;


    //non-FXML fields
    private static int odds = 1;
    private ObservableList list = FXCollections.observableArrayList();
    private ObservableList listDistance = FXCollections.observableArrayList();
    private static int diff;
    private static Stage popStage;
    private static ArrayList<Region> regions;
    private static ArrayList<Region> regionsDistance = new ArrayList<>();
    private static Stage stage;
    private int current = 0;
    private boolean isClicked = false;
    private MapController mapController;
    private ArrayList<Region> map = new ArrayList<>();
    private boolean marketClicked = false;
    private MarketController market;
    private Stage newStage;
    private static Stage currentStage;
    private double probabilityNum = Math.random();
    private int fCost = 0;


    public static Stage getStage() {
        return currentStage;
    }

    public void clearListDistance() {
        listDistance.clear();
        list.clear();
    }

    public void clearDistanceListView() {
        distancelist.getItems().clear();
        regionlist.getItems().clear();
    }

    public void clearRegionList() {
        regions.clear();
        regionsDistance.clear();
    }


    @FXML
    private void toCharUpgrade(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/UpgradeMarket.fxml"));
            Parent gameUI = (Parent) loader.load();
            UpgradeMarketController charUpgrade = loader.getController();
            charUpgrade.initialize(Ship.getStrength(), Ship.getAgility(), Ship.getIntelligence(),
                    Ship.getCharisma(), Ship.getEngineering(), ("" + Ship.getGold()));
            newstage = new Stage();
            newstage.setScene(new Scene(gameUI));
            SetupSummaryController.hideGuiStage();
            newstage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toMarketList() {
        if (!marketClicked) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().
                        getResource("../fxml/Market.fxml"));
                Parent gameUI = (Parent) loader.load();
                market = loader.getController();
                market.initialize(credit, regions.get(current), gamePlayer.getCharisma());
                newstage = new Stage();
                newstage.setScene(new Scene(gameUI));
                SetupSummaryController.hideGuiStage();
                newstage.show();
                //marketClicked = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            market.reshowMarket();
            SetupSummaryController.hideGuiStage();
        }
    }


    public static void closeUpgrade() {
        newstage.close();
        newstage.hide();
    }

    public static void showUpgrade() {
        newstage.show();
    }

    private void updateFuel() {
        fCost = 15 - gamePlayer.getAgility() * 2;
        Player.getShip().get(0).setFuelCapacity(Player.getShip().get(0).getFuelCapacity() - fCost);
        this.ship.setText("You are using the " + Player.getShip().get(0).getName()
                + " ship that has currently a fuel capacity of "
                + Player.getShip().get(0).getFuelCapacity()
                + "/100.\n" + "Health : " + Player.getShip().get(0).getHealth()
                + "/" + Player.getShip().get(0).getMaxHealth());
        if (Ship.getFuelCapacity() < 0) {
            this.ship.setText("You are using the " + Player.getShip().get(0).getName()
                    + " ship that has currently a fuel capacity of "
                    + "0/100.\n" + "Health : " + Player.getShip().get(0).getHealth()
                    + "/" + Player.getShip().get(0).getMaxHealth());
        }
    }

    public void updateHealth() {
        this.ship.setText("You are using the " + Player.getShip().get(0).getName()
                + " ship that has currently a fuel capacity of "
                + Player.getShip().get(0).getFuelCapacity()
                + "/100.\n" + "Health : " + Player.getShip().get(0).getHealth()
                + "/" + Player.getShip().get(0).getMaxHealth());
    }

    public void initializeData(String name, String techLevel,
                               double[] location, Region region,
                               Player player, int credit, int charisma) {
        this.credit = credit;
        this.regionName.setText(name);
        this.regionTechLevel.setText("Tech level is : " + techLevel);
        this.regionLocation.setText("Location is ("
                + location[0] + ", " + location[1] + ")");
        this.regionDescription.setText("This is the "
                + name + " located at (" + location[0]
                + "," + location[1] + "). Tech level is "
                + techLevel + ".");
        this.ship.setText("You are using the " + player.getShip().get(0).getName()
                + " ship that has currently a fuel capacity of "
                + player.getShip().get(0).getFuelCapacity()
                + "/100\n" + "Health : " + player.getShip().get(0).getHealth()
                + "/" + player.getShip().get(0).getMaxHealth());
        gamePlayer = player;
        Region[] temporaryMap = gamePlayer.getMap();
        for (int i = 0; i < gamePlayer.getMap().length; i++) {
            map.add(temporaryMap[i]);
        }
        regions = new ArrayList<Region>();
        regionsDistance.add(region);
        showVDistance(region);
        showVisited(region);
        map.remove(region);
        regions.add(region);
    }

    public static void getDiff(int difficulty) {
        diff = difficulty;
    }

    public void nextRegion() throws Exception {
        setEvents();
        if (!cancelTravel && Ship.getFuelCapacity() > 0) {
            EncounterTraderController trader = new EncounterTraderController();
            Region nextRegion;
            if (map.size() > 0) {
                nextRegion = map.get((int) (java.lang.Math.random() * map.size()));
                map.remove(nextRegion);
                regionsDistance.add(nextRegion);
                showVisited(nextRegion);
                current++;
            } else {
                if (current < regions.size() - 1) {
                    current++;
                    nextRegion = regions.get(current);

                } else {
                    current = 0;
                    nextRegion = regions.get(current);
                }
            }
            showVDistance(nextRegion);
            regions.add(nextRegion);
            this.regionName.setText(nextRegion.getRegionName());
            this.regionTechLevel.setText("Tech level is : " + nextRegion.getRegionTechLevel());
            this.regionLocation.setText("Location is ("
                    + nextRegion.getRegionCoordinates()[0]
                    + ", " + nextRegion.getRegionCoordinates()[1] + ")");
            this.regionDescription.setText("This is the " + nextRegion.getRegionName()
                    + " region located at (" + nextRegion.getRegionCoordinates()[0] + ", "
                    + nextRegion.getRegionCoordinates()[1]
                    + "). Tech level is " + nextRegion.getRegionTechLevel() + ".");
            updateFuel();
        }
        cancelTravel = false;
        if (Ship.getFuelCapacity() < 0) {
            this.ship.setText("You are using the " + Player.getShip().get(0).getName()
                    + " ship that has currently a fuel capacity of "
                    + "0/100.\n" + "Health : " + Player.getShip().get(0).getHealth()
                    + "/" + Player.getShip().get(0).getMaxHealth());
        }
    }

    public void previousRegion() throws Exception {
        setEvents();
        if (!cancelTravel && Ship.getFuelCapacity() >= 15) {
            EncounterTraderController trader = new EncounterTraderController();
            if (current == 0) {
                current = regions.size();
            }

            Region curr = regions.get(current - 1);
            showVDistance(curr);
            regionName.setText(curr.getRegionName());
            regionTechLevel.setText("Tech level is : " + curr.getRegionTechLevel());
            regionLocation.setText("Location is (" + curr.getRegionCoordinates()[0] + ", "
                    + curr.getRegionCoordinates()[1] + ")");
            regionDescription.setText("This is the " + curr.getRegionName() + " region located at ("
                    + curr.getRegionCoordinates()[0] + ", "
                    + curr.getRegionCoordinates()[1] + "). Tech level is "
                    + curr.getRegionTechLevel() + ".");
            current--;
            updateFuel();
        }
        cancelTravel = false;
        if (Ship.getFuelCapacity() < 0) {
            this.ship.setText("You are using the " + Player.getShip().get(0).getName()
                    + " ship that has currently a fuel capacity of "
                    + "0/100.\n" + "Health : " + Player.getShip().get(0).getHealth()
                    + "/" + Player.getShip().get(0).getMaxHealth());
        }
    }

    public void showVisited(Region region) {
        list.removeAll(list);
        list.add(region.getRegionName());
        regionlist.getItems().addAll(list);
    }

    public void showVDistance(Region curr) {
        listDistance.removeAll(listDistance);
        distancelist.getItems().clear();
        for (int i = 0; i < regionsDistance.size(); i++) {
            Region target = regionsDistance.get(i);
            double x = target.getRegionCoordinates()[0];
            double y = target.getRegionCoordinates()[1];
            double currx = curr.getRegionCoordinates()[0];
            double curry = curr.getRegionCoordinates()[1];
            double distance = Math.sqrt(Math.pow(x - currx, 2) + Math.pow(y - curry, 2));
            DecimalFormat ddf = new DecimalFormat("####.##");
            distance = Double.valueOf(ddf.format(distance));
            String dis = distance + " light years";
            listDistance.add(dis);
        }
        distancelist.getItems().addAll(listDistance);
    }

    public void setGamePlayer(Player player) {
        gamePlayer = player;
    }

    public void toMapList() {
        if (!isClicked) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("../fxml/Map.fxml"));
                Parent root = (Parent) loader.load();
                mapController = loader.getController();
                mapController.regions(regions);
                SkillConfigurationController.hidStage();
                stage = new Stage();
                stage.setScene(new Scene(root));
                PlayerController.hideNewStage();
                stage.show();
                mapController.updateRegion();
            } catch (IOException x) {
                x.printStackTrace();
            }
        } else {
            mapController.reShowMap();
            mapController.updateRegion();
        }
    }

    public void setRepairStage() {
        try {
            RepairController repairController = new RepairController();

            repairController.getScene(regionName.getScene());

            repairController.setPopBandit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRefuelStage() {
        try {
            RefuelController refuelController = new RefuelController();
            refuelController.getScene(regionName.getScene());
            refuelController.setPopBandit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBanditStage() {
        try {
            BanditController banditController = new BanditController();
            banditController.initLabels(diff * 20 + "");
            banditController.getScene(regionName.getScene());
            banditController.setPopBandit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTraderStage() {
        try {
            EncounterTraderController trader = new EncounterTraderController();
            trader.getScene(regionName.getScene());
            trader.setPopTrader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPoliceStage() {
        if (!Ship.inventoryIsEmpty()) {
            if ((java.lang.Math.random() * 5) / diff < 1.0) {
                PoliceController policeController = new PoliceController();
                policeController.initializeScreen(gamePlayer, credit);
            }
        }
    }

    public void setEvents() throws Exception {
        double probabilityNum = Math.random();
        if (Ship.getHealth() <= 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "../fxml/EndGameScreen.fxml"));
            Parent gameUI = (Parent) loader.load();
            upgrade.getScene().getWindow().hide();
            newStage = new Stage();
            newStage.setScene(new Scene(gameUI));
            newStage.show();
        }
        if (diff == 1 && Ship.getFuelCapacity() > 0) {
            if (odds % 4 == 0) {
                if (probabilityNum > 0.75) {
                    setBanditStage();
                } else if (probabilityNum > 0.3) {
                    setTraderStage();
                } else {
                    setPoliceStage();
                }
            }

        } else if (diff == 2 && Ship.getFuelCapacity() > 0) {
            if (odds % 3 == 0 || odds % 4 == 0) {
                if (probabilityNum > 0.7) {
                    setBanditStage();
                } else if (probabilityNum > 0.35) {
                    setTraderStage();
                } else {
                    setPoliceStage();
                }
            }
        } else if (diff == 3 && Ship.getFuelCapacity() > 0) {
            if (odds % 3 == 0 || odds % 5 == 0) {
                if (probabilityNum > 0.65) {
                    setBanditStage();
                } else if (probabilityNum > 0.2) {
                    setTraderStage();
                } else {
                    setPoliceStage();
                }
            }
        } else if (diff == 4 && Ship.getFuelCapacity() > 0) {
            if (odds % 3 == 0 || odds % 4 == 0) {
                if (probabilityNum > 0.6) {
                    setBanditStage();
                } else if (probabilityNum < 0.2) {
                    setTraderStage();
                } else {
                    setPoliceStage();
                }
            }
        } else {
            if ((odds % 2 == 0 || odds % 5 == 0) && Ship.getFuelCapacity() > 0) {
                if (probabilityNum > 0.5) {
                    setBanditStage();
                } else if (probabilityNum > 0.2) {
                    setTraderStage();
                } else {
                    setPoliceStage();
                }
            }
        }
        odds += 1;
    }
}
