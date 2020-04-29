package classes;

public class Map {
    //array of names of the regions
    private static String[] regionNames = {"Alpha", "Beta", "Charlie", "Delta", "Echo",
        "Foxtrot", "Golf", "Hotel", "India", "Juliett"};


    private Region[] regions = new Region[regionNames.length];
    private int winningRegion = (int) Math.random() * 10;

    public Map() {
        for (int i = 0; i < regionNames.length; i++) {
            if (i == winningRegion) {
                this.regions[i] = new Region(regionNames[i], true);
            } else {
                this.regions[i] = new Region(regionNames[i], false);
            }
        }

    }

    public Region[] getMapRegions() {
        return this.regions;
    }
}
