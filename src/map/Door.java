package map;

public class Door {
    private final String id;
    private Door left;
    private Door right;
    private Door downDoor;
    private Door upDoor;
    private final Room connectedRoom;

    public Door(String id, Room connectedRoom) {
        this.id = id;
        this.connectedRoom = connectedRoom;
        connectedRoom.setDoor(this);
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

}