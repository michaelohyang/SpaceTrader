package classes;

import javafx.fxml.FXML;
import java.util.Random;

public class Region {

    private double[] coordinates;

    //These three are not visible to the player until he visits the region for the first time
    private String name;
    private int techLevel;
    private String description;
    private String[] techName = {"Agricultural Era", "Medieval Era",
        "Industrial Era", "Modern Era", "Galactic Era"};
    private Random r = new Random();
    private boolean winningRegion;

    public Region(String name, String description, boolean winningRegion) {
        this.name = name;
        this.techLevel = (int) (java.lang.Math.random() * 5);
        this.description = description;
        this.coordinates = new double[]{java.lang.Math.random(),
                java.lang.Math.random(), java.lang.Math.random()};
        this.winningRegion = winningRegion;
    }

    public Region(String name, boolean winningRegion) {
        this.name = name;
        this.techLevel = (int) (java.lang.Math.random() * 5);
        this.coordinates = new double[]
                {(int) (java.lang.Math.random() * 1000),
            (int) (java.lang.Math.random() * 1000),
            (int) (java.lang.Math.random() * 10)
        };
        this.description = "This is the " + this.name
            + " region located at (" + this.coordinates[0] + ", "
            + this.coordinates[1] + ") and has a tech level of " + this.techLevel + ".";
        this.winningRegion = winningRegion;
    }


    @FXML
    public String getRegionName() {
        return this.name;
    }

    public String getRegionTechLevel() {
        return techName[techLevel];
    }

    public int getTechLevel() {
        return techLevel;
    }

    public String getRegionDescription() {
        return this.description;
    }

    public double[] getRegionCoordinates() {
        return this.coordinates;
    }

    public boolean getWinning() { return winningRegion;}
}

