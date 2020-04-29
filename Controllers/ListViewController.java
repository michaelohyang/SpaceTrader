package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.List;
import classes.*;

public class ListViewController {
    @FXML
    private TextField locate1;
    @FXML
    private TextField locate2;
    @FXML
    private TextField locate3;
    @FXML
    private TextField locate4;
    @FXML
    private TextField locate5;
    @FXML
    private TextField locate6;
    @FXML
    private TextField locate7;
    @FXML
    private TextField locate8;
    @FXML
    private TextField locate9;
    @FXML
    private TextField locate10;
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
    @FXML
    private Button backbtn;

    // non-FXML fields
    private List<Region> regions;
    private TextField[] nameArr;
    private TextField[] locaArr;

    /**
     * takes you back to the gameGUI screen
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

     /**
     * updates the region names and locations
     */
    public void updateRegion() {
        nameArr = new TextField[] {region1, region2, region3, region4,
            region5, region6, region7, region8, region9, region10};
        locaArr = new TextField[] {locate1, locate2, locate3, locate4,
            locate5, locate6, locate7, locate8, locate9, locate10};
        for (int i = 0; i < 10; i++) {
            if (i < regions.size() && regions.get(i) != null) {
                nameArr[i].setText(regions.get(i).getRegionName());
                locaArr[i].setText("(" + regions.get(i).getRegionCoordinates()[0]
                    + ", " + regions.get(i).getRegionCoordinates()[1] + ")");
            }
        }
    }
}

