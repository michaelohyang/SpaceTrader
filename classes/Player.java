package classes;

import java.util.ArrayList;

public class Player extends Map {

    private String name;
    private Region spawningRegion;
    private double[] location;
    private static ArrayList<Ship> ship = new ArrayList<>();
    private int strength;
    private int agility;
    private int intelligence;
    private int charisma;
    private int engineering;

    public Player(String name, int strength, int agility,
                  int intelligence, int charisma, int engineering) {
        super();
        this.name = name;
        this.spawningRegion = getMapRegions()[(int) (java.lang.Math
                .random() * getMapRegions().length)];
        this.location = spawningRegion.getRegionCoordinates();
        for (int i = 0; i < Ship.getShipNames().length; i++) {
            ship.add(new Ship(Ship.getShipNames()[i]));
        }
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.engineering = engineering;
    }

    public Player(String name) {
        super();
        this.name = name;
        this.spawningRegion = getMapRegions()[(int) (java.lang.Math
                .random() * getMapRegions().length)];
        this.location = spawningRegion.getRegionCoordinates();
        for (int i = 0; i < Ship.getShipNames().length; i++) {
            ship.set(i, new Ship(Ship.getShipNames()[i]));
        }
    }

    public String getPlayerName() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public double[] getLocation() {
        return location;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getEngineering() {
        return engineering;
    }

    public Region getSpawningRegion() {
        return this.spawningRegion;
    }

    public Region[] getMap() {
        return super.getMapRegions();
    }

    public double[] getPlayerLocation() {
        return this.location;
    }

    public static ArrayList<Ship> getShip() {
        return ship;
    }

}

