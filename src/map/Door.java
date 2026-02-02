package map;

import items.Material;

public class Door {
    private final String id;
    private Door left;
    private Door right;
    private Door downDoor;
    private Door upDoor;
    private final Room connectedRoom;
    private boolean isLocked;
    private Material material;

    public Door(String id, Room connectedRoom) {
        this.id = id;
        this.connectedRoom = connectedRoom;
        connectedRoom.setDoor(this);
        this.isLocked = false;
    }

    public void setLeft(Door left) {
        this.left = left;
    }

    public void setRight(Door right) {
        this.right = right;
    }

    public Door getLeft() {
        return left; }
    public Door getRight() {
        return right; }
    public Room getConnectedRoom() {
        return connectedRoom; }
    public String getId() {
        return id; }

    public Door getDownDoor() {
        return downDoor;
    }

    public void setDownDoor(Door downDoor) {
        this.downDoor = downDoor;
    }

    public Door getUpDoor() {
        return upDoor;
    }

    public void setUpDoor(Door upDoor) {
        this.upDoor = upDoor;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}