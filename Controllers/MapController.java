package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.List;
import classes.*;

public class MapController {
    @FXML
    private TextField location1;
    @FXML
    private TextField location2;
    @FXML
    private TextField location3;
    @FXML
    private TextField location4;
    @FXML
    private TextField location5;
    @FXML
    private TextField location6;
    @FXML
    private TextField location7;
    @FXML
    private TextField location8;
    @FXML
    private TextField location9;
    @FXML
    private TextField location10;
    @FXML
    private TextField region1;
    @FXML
    private TextField region2;
    @FXML
    private TextField region3;
    @FXML
    private TextField region4;
    @FXML
    private TextField region5;
    @FXML
    private TextField region6;
    @FXML
    private TextField region7;
    @FXML
    private TextField region8;
    @FXML
    private TextField region9;
    @FXML

    private TextField region10;
    private List<Region> regions;
    private String name;
    @FXML
    private TextField[] nameArr;
    private TextField[] locaArr;
    private Stage stage; // dont use static

    @FXML
    private Button backbtn;

    private GameGuiController gameScr;

    public GameGuiController getGameScr() {
        return gameScr;
    }

    /**
     * takes you back to the gameUI screen
     */
    public void backToGameUI() {
        backbtn.getScene().getWindow().hide();
        //gameScr.focusScr();
    }

    /**
     * shows the map again after you close it, rather than making a new Stage of map
     */
    public void reShowMap() {
        ((Stage) backbtn.getScene().getWindow()).show();
    }

    /**
     * gets the data passed in from the RegionList
     * @param passedRegions  arrayList passed in from the RegionList
     */
    public void regions(List<Region> passedRegions) {
        regions = passedRegions;

    }

    public void updateRegion() {
        nameArr = new TextField[] {region1, region2, region3, region4,
            region5, region6, region7, region8, region9, region10};
        locaArr = new TextField[] {location1, location2, location3, location4,
            location5, location6, location7, location8, location9, location10};
        for (int i = 0; i < 10; i++) {
            if (i < regions.size() && regions.get(i) != null) {
                nameArr[i].setText(regions.get(i).getRegionName());
                locaArr[i].setText("(" + regions.get(i).getRegionCoordinates()[0]
                    + ", " + regions.get(i).getRegionCoordinates()[1] + ")");
            }
        }
    }
}

