package model;
import map.Door;

public abstract class GameCharacter {

    protected Door currentDoor;
    protected boolean insideRoom = false;

    public void moveLeft() {
    }

    public void moveRight() {
    }

    public void enterRoom() {
    }

    public void exitRoom() {
    }

    public Door getCurrentDoor() {
        return currentDoor;
    }

    public boolean isInsideRoom() {
        return insideRoom;
    }
}
