package model;

import map.Door;

public abstract class GameCharacter {
    protected Door currentDoor;
    protected boolean insideRoom = false;

    public void moveLeft() {
        if (currentDoor.getLeft() != null) {
            currentDoor = currentDoor.getLeft();
        }
    }

    public void moveRight() {
        if (currentDoor.getRight() != null) {
            currentDoor = currentDoor.getRight();
        }
    }

    public void enterRoom() {
        if (!insideRoom) {
            insideRoom = true;
        }
    }

    public void exitRoom() {
        if (insideRoom) {
            insideRoom = false;
        }
    }

    public Door getCurrentDoor() {
        return currentDoor;
    }

    public boolean isInsideRoom() {
        return insideRoom;
    }

    public void setCurrentDoor(Door door) {
        this.currentDoor = door;
    }
}