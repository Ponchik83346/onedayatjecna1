package model;

import map.Door;
import map.Room;

public abstract class GameCharacter {
    protected Door currentDoor;
    protected Room currentRoom;
    protected boolean insideRoom = false;

    public void moveLeft() {
        if (currentDoor != null && currentDoor.getLeft() != null) {
            currentDoor = currentDoor.getLeft();
        }
    }

    public void moveRight() {
        if (currentDoor != null && currentDoor.getRight() != null) {
            currentDoor = currentDoor.getRight();
        }
    }

    public void enterRoom() {
        if (!insideRoom) {
            insideRoom = true;
            currentRoom = currentDoor.getConnectedRoom();
            setCurrentDoor(null);
        }
    }

    public void exitRoom() {
        if (insideRoom) {
            insideRoom = false;
            setCurrentDoor(currentRoom.getDoor());
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

    public Room getCurrentRoom() {
        if (insideRoom) return currentRoom;
        return currentDoor != null ? currentDoor.getConnectedRoom() : null;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setInsideRoom(boolean insideRoom) {
        this.insideRoom = insideRoom;
    }
}