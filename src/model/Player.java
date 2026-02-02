package model;
import gameData.GameData;
import items.*;
import map.Door;
import map.Floor;
import map.Map;
import map.RoomType;

import java.util.List;
import java.util.Scanner;

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
    public void useItem(Item item, Scanner sc) {
        if (!inventory.contains(item)) {
            System.out.println("Nemáte tento item v inventáři!");
            return;
        }
        Door door = getCurrentDoor();
        switch (item.getType()) {
            case FOOD -> {
                Food food = (Food) item;
                food.use(this);
                inventory.removeItem(item);
                System.out.println("Snědl jste " + food.getName() + ". Stamina + " + food.getStamina());
            }
            case KEY -> {
                Key key = (Key) item;
                key.use(this);
                inventory.removeItem(item);
                System.out.println("Využili jste klíč!");
            }
            case HAMMER -> {
                List<Material> materials = inventory.getMaterials();
                if (materials.isEmpty()) {
                    System.out.println("Nemáte žádné materiály!");
                    return;
                }
                System.out.println("Vyberte si materiál:");
                for (int i = 0; i < materials.size(); i++) {
                    System.out.println(i + ": " + materials.get(i).getName());
                }
                int choice = -1;
                while (choice < 0 || choice >= materials.size()) {
                    System.out.print("Zadejte číslo materiálu: ");
                    choice = sc.nextInt();
                }
                Material chosenMat = materials.get(choice);
                Hammer hammer = (Hammer) item;
                hammer.use(this, chosenMat, door);
                inventory.removeItem(chosenMat);
                if (hammer.getHp() <= 0) {
                    inventory.removeItem(hammer);
                    System.out.println("Hammer broke.");
                }
            }
        }
    }

    public void setCurrentDoor(Door currentDoor) {
        this.currentDoor = currentDoor;
    }

    public void goUpstairs() {
        if (currentDoor.getConnectedRoom().getType() == RoomType.STAIRS) {
            Door upDoor = currentDoor.getUpDoor();
            if (upDoor != null) {
                currentDoor = upDoor;
                currentFloor = getFloorByDoor(upDoor);
                System.out.println("Šli jste po schodech nahoru do patra " + currentFloor.getLevel());
            } else {
                System.out.println("Není kam jít!");
            }
        } else {
            System.out.println("Nejste na schodech!");
        }
    }


    public void goDownstairs() {
        if (currentDoor.getConnectedRoom().getType() == RoomType.STAIRS) {
            Door downDoor = currentDoor.getDownDoor();
            if (downDoor != null) {
                currentDoor = downDoor;
                currentFloor = getFloorByDoor(downDoor);
                System.out.println("Šli jste po schodech dolu do patra " + currentFloor.getLevel());
            } else {
                System.out.println("Není kam jít!");
            }
        } else {
            System.out.println("Nejste na schodech!");
        }
    }

    public void useElevator() {
        if (currentDoor.getConnectedRoom().getType() == RoomType.ELEVATOR) {
            Map map = GameData.getMap();
            for (Floor floor : map.getFloors()) {
                for (Door door : floor.getDoors()) {
                    if (door.getConnectedRoom().getType() == RoomType.ELEVATOR
                            && door != currentDoor) {
                        currentDoor = door;
                        currentFloor = floor;
                        System.out.println("Jeli jste výtahem do patra " + currentFloor.getLevel());
                        return;
                    }
                }
            }
            System.out.println("Špatné číslo výtahu!");
        } else {
            System.out.println("Nejste ve výtahu!");
        }
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
        inventory.printContents();
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

    private Floor getFloorByDoor(Door door) {
        Map map = GameData.getMap();
        for (Floor floor : map.getFloors()) {
            if (floor.getDoors().contains(door)) {
                return floor;
            }
        }
        return null;
    }
}