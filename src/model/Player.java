package model;
import items.Inventory;
import items.Item;
import map.Door;
import map.Floor;

public class Player extends GameCharacter {

    private int stamina;
    private Inventory inventory;
    private int testsCollected;
    private Floor currentFloor;

    public Player() {
        super();
        this.testsCollected = 0;
        this.inventory = new Inventory();
        this.stamina = 100;
    }

    public void setCurrentDoor(Door currentDoor) {
        this.currentDoor = currentDoor;
    }

    public void useItem(Item item) {
    }

    public void goUpstairs(){

    }
    public void goDownstairs(){

    }
    public void useElevator(){

    }

    public void addTest() {
        testsCollected++;
    }

    public void loseStamina(int amount) {
        this.stamina -= amount;
    }

    public void addStamina(int amount) {
        this.stamina += amount;
    }

    public int getStamina() {
        return stamina;
    }

    public void openInventory(Inventory inventory) {

    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getTestsCollected() {
        return testsCollected;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setTestsCollected(int testsCollected) {
        this.testsCollected = testsCollected;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}