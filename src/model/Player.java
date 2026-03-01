package model;
import gameData.GameData;
import items.*;
import map.*;
import ui.InputHandler;

import java.util.List;
import java.util.Scanner;

/**
 * Třída pro hráče. Hráč má inventory, počet testů a narozdíl od učitelů i currentFloor.
 */
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

    /**
     * Podmínka pro výhru.
     * @return Jestli hráč vyhrál.
     */
    public boolean hasEnoughTests(){
        return this.testsCollected == 30;
    }

    /**
     * Použít předmět.
     * @param item předmět k použití
     * @param inputHandler input hráče.
     */
    public void useItem(Item item, InputHandler inputHandler) {
        if (!inventory.contains(item)) {
            System.out.println("Nemáte tento item v inventáři!");
            return;
        }
        switch (item.getType()) {
            case FOOD -> {
                Food food = (Food) item;
                setStamina(stamina + food.getStamina());
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
                Door door = getCurrentDoor();
                if (door == null) {
                    System.out.println("Nestojíte u dveří!");
                    return;
                }
                List<Material> materials = inventory.getMaterials();
                if (materials.isEmpty()) {
                    System.out.println("Nemáte žádné materiály!");
                    return;
                }
                System.out.println("Vyberte materiál:");
                for (int i = 0; i < materials.size(); i++) {
                    System.out.println(i + ": " + materials.get(i).getName());
                }
                System.out.print("Zadejte číslo: ");
                String input = inputHandler.getScanner().nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Neplatné číslo!");
                    return;
                }
                if (choice < 0 || choice >= materials.size()) {
                    System.out.println("Neplatná volba!");
                    return;
                }
                Material chosenMat = materials.get(choice);
                Hammer hammer = (Hammer) item;
                hammer.use(chosenMat, door);
                inventory.removeItem(chosenMat);
                if (hammer.getHp() <= 0) {
                    inventory.removeItem(hammer);
                    System.out.println("Kladivo se rozbilo.");
                }
            }
            case MATERIAL -> {
                System.out.println("Nelze využít materiál bez kladiva! Využijte kladivo a až poté vyberte materiál.");
            }
        }
    }

    public void setCurrentDoor(Door currentDoor) {
        this.currentDoor = currentDoor;
    }

    /**
     * Jít po schodech nahorů.
     */
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
                        stamina = stamina-20;
                        return;
                    }
                }
            }
        }
        System.out.println("Výše už žádné patro není!");
    }

    /**
     * Jít po schodech dolů.
     */
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
                        stamina = stamina-20;
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
        Door before = currentDoor;
        super.moveRight();
        if (before == currentDoor) {
            System.out.println("Nelze jít doprava!");
        } else {
            stamina -= 5;
        }
    }

    @Override
    public void moveLeft() {
        if (currentDoor == null) {
            System.out.println("Jsi v místnosti!");
            return;
        }
        Door before = currentDoor;
        super.moveLeft();
        if (before == currentDoor) {
            System.out.println("Nelze jít doleva!");
        } else {
            stamina -= 5;
        }
    }
    @Override
    public void enterRoom() {
        if (currentDoor == null) {
            System.out.println("Jsi již v místnosti!");
            return;
        }
        Room targetRoom = currentDoor.getConnectedRoom();
        if (targetRoom.getType() == RoomType.STAIRS ||
                targetRoom.getType() == RoomType.ELEVATOR) {
            System.out.println("Po schodech a výtahem se může chodit pouze nahoru a dolů!");
            return;
        }
        if (currentDoor.isLocked()) {
            System.out.println("Nelze jít do místnosti, dveře jsou zamčené/zabarikádované!");
            return;
        }
        super.enterRoom();
    }

    @Override
    public void exitRoom() {
        if (!insideRoom) {
            System.out.println("Už jste na chodbě!");
            return;
        }
        super.exitRoom();
    }

    /**
     * Využítí výtahu.
     * @param inputHandler input hráče
     */
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

    public int getStamina() {
        return stamina;
    }

    public void openInventory() {
        this.inventory.printContents();
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getTestsCollected() {
        return testsCollected;
    }

    public void setTestsCollected(int testsCollected) {
        this.testsCollected = testsCollected;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}