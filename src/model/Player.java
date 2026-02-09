package model;
import gameData.GameData;
import items.*;
import map.Door;
import map.Floor;
import map.Map;
import map.RoomType;
import ui.InputHandler;

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
        this.currentRoom = null;
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
        if (currentDoor.getConnectedRoom().getType() != RoomType.STAIRS) {
            System.out.println("Nejste na schodech!");
            return;
        }
        Map map = GameData.getMap();
        int targetLevel = currentFloor.getLevel() + 1;
        for (Floor floor : map.getFloors()) {
            if (floor.getLevel() == targetLevel) {
                for (Door door : floor.getDoors()) {

                    if (door.getConnectedRoom().getType() == RoomType.STAIRS) {
                        currentDoor = door;
                        currentFloor = floor;

                        System.out.println("Šli jste nahoru do patra " + targetLevel);
                        return;
                    }
                }
            }
        }
        System.out.println("Výše už žádné patro není!");
    }


    public void goDownstairs() {
        if (currentDoor.getConnectedRoom().getType() != RoomType.STAIRS) {
            System.out.println("Nejste na schodech!");
            return;
        }
        Map map = GameData.getMap();
        int targetLevel = currentFloor.getLevel() - 1;
        for (Floor floor : map.getFloors()) {
            if (floor.getLevel() == targetLevel) {
                for (Door door : floor.getDoors()) {

                    if (door.getConnectedRoom().getType() == RoomType.STAIRS) {
                        currentDoor = door;
                        currentFloor = floor;

                        System.out.println("Šli jste dolů do patra " + targetLevel);
                        return;
                    }
                }
            }
        }
        System.out.println("Níže už žádné patro není!");
    }

    @Override
    public void moveRight() {
        if (currentDoor == null) {
            System.out.println("Jsi v místnosti!");
            return;
        }
        Door right = currentDoor.getRight();
        if (right == null) {
            System.out.println("Nelze jít doprava!");
            return;
        }
        currentDoor = right;
    }
    @Override
    public void moveLeft() {
        if (currentDoor == null) {
            System.out.println("Jsi v místnosti!");
            return;
        }
        Door left = currentDoor.getLeft();
        if (left == null) {
            System.out.println("Nelze jít doLeva!");
            return;
        }
        currentDoor = left;
    }
    @Override
    public void enterRoom() {
        if(currentDoor.getConnectedRoom().getType() == RoomType.STAIRS || currentDoor.getConnectedRoom().getType() == RoomType.ELEVATOR) {
            System.out.println("Po schodech a výtahem se může chodit pouze nahodu a dolů!");
            return;
        }
        if (!insideRoom) {
            insideRoom = true;
            currentRoom = currentDoor.getConnectedRoom();
            setCurrentDoor(null);
        } else {
            System.out.println("Jste v místnosti!");
        }
    }

    @Override
    public void exitRoom() {
        if (insideRoom) {
            insideRoom = false;
            setCurrentDoor(currentRoom.getDoor());
        } else {
            System.out.println("Už jste na chodbě!");
        }
    }

    public void useElevator(InputHandler inputHandler) {
        if (currentDoor.getConnectedRoom().getType() != RoomType.ELEVATOR) {
            System.out.println("Nejste ve výtahu!");
            return;
        }
        if (currentDoor.isLocked()) {
            System.out.println("Výtah je zamčený!");
            return;
        }
        Map map = GameData.getMap();
        System.out.println("Do jakého patra chcete jet?");
        if (!inputHandler.getScanner().hasNextInt()) {
            System.out.println("Musíš zadat číslo patra!");
            inputHandler.getScanner().next();
            return;
        }
        int targetLevel = inputHandler.getScanner().nextInt();
        for (Floor floor : map.getFloors()) {
            if (floor.getLevel() == targetLevel) {
                for (Door door : floor.getDoors()) {

                    if (door.getConnectedRoom().getType() == RoomType.ELEVATOR) {
                        currentDoor = door;
                        currentFloor = floor;

                        System.out.println("Jeli jste výtahem do patra " + targetLevel);
                        return;
                    }
                }
            }
        }
        System.out.println("Takové patro neexistuje!");
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