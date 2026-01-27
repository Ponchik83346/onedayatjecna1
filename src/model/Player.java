package model;
import items.Inventory;
import items.Item;

public class Player extends GameCharacter {

    private int stamina = 100;
    private Inventory inventory = new Inventory();
    private int testsCollected = 0;

    public void useItem(Item item) {
    }

    public void goUpstairs(){

    }
    public void goDownstairs(){

    }
    public void useElevator(){

    }

    public void addTest() {
    }

    public void loseStamina(int amount) {
    }

    public void addStamina(int amount) {
    }

    public int getStamina() {
        return stamina;
    }

    public void openInventory(Inventory inventory) {

    }
}