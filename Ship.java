package classes;

import java.util.HashMap;

public class Ship {

    private static String[] shipNames = {"Falcon", "Flamingo", "Eagle", "Vulture"};
    private static String name;
    private static int cargoCapacity = 25;
    private static int fuelCapacity;
    private static int health;
    private int maxhealth;
    public static HashMap<String, Integer> inventory = new HashMap<>();
    private static int gold;
    private static String strength;
    private static String agility;
    private static String intelligence;
    private static String charisma;
    private static String engineering;

    public Ship(String name, int cargoCap,
                 int fuelCapacity, int maxhealth) {
        this.name = name;
        cargoCapacity = cargoCap;
        this.fuelCapacity = fuelCapacity;
        this.maxhealth = maxhealth;
        this.health = maxhealth;
        inventory.put("Food", 0);
        inventory.put("Iron", 0);
        inventory.put("Oil", 0);
        inventory.put("Circuit", 0);
        inventory.put("Dark Matter", 0);
        inventory.put("Space Trader Privilege", 0);
    }

    public Ship(String name) {
        this.name = name;
        this.fuelCapacity = 100;
        this.maxhealth = 100;
        this.health = 100;
        inventory.put("Food", 0);
        inventory.put("Iron", 0);
        inventory.put("Oil", 0);
        inventory.put("Circuit", 0);
        inventory.put("Dark Matter", 0);
        inventory.put("Space Trader Privilege", 0);
    }


    public String getName() {
        return this.name;
    }

    public static void setHealth(int health1) {
        health = health1;
    }

    public static void setFuelCapacity(int fuelCapacity) {
        Ship.fuelCapacity = fuelCapacity;
    }

    public static int getFuelCapacity() {
        return fuelCapacity;
    }

    public static int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return this.maxhealth;
    }

    public static String getStrength() {
        return strength;
    }

    public static void setStrength(String strength) {
        Ship.strength = strength;
    }

    public static String getAgility() {
        return agility;
    }

    public static void setAgility(String agility) {
        Ship.agility = agility;
    }

    public static String getIntelligence() {
        return intelligence;
    }

    public static void setIntelligence(String intelligence) {
        Ship.intelligence = intelligence;
    }

    public static String getCharisma() {
        return charisma;
    }

    public static void setCharisma(String charisma) {
        Ship.charisma = charisma;
    }

    public static String getEngineering() {
        return engineering;
    }

    public static void setEngineering(String engineering) {
        Ship.engineering = engineering;
    }

    public static int getCargoCapacity() {
        return cargoCapacity;
    }

    public static void setCargoCap(int cargoCap) {
        cargoCapacity = cargoCap;
    }

    public static void setGold(int gol) {
        gold = gol;
    }

    public static int getGold() {
        return gold;
    }

    public static String[] getShipNames() {
        return shipNames;
    }

    public static boolean inventoryIsEmpty() {
        if (Ship.inventory.get("Food") == 0
                && Ship.inventory.get("Iron") == 0
                && Ship.inventory.get("Oil") == 0
                && Ship.inventory.get("Circuit") == 0
                && Ship.inventory.get("Dark Matter") == 0) {
            return true;
        }
        return false;
    }

    public static void clearInventory() {
        Ship.inventory.put("Food", 0);
        Ship.inventory.put("Iron", 0);
        Ship.inventory.put("Oil", 0);
        Ship.inventory.put("Circuit", 0);
        Ship.inventory.put("Dark Matter", 0);
    }


}
