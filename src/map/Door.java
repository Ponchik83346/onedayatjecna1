package map;

public class Door {

    private Door left;
    private Door right;
    private Room connectedRoom;

    public Door getLeft() {
        return left;
    }

    public Door getRight() {
        return right;
    }

    public Room getConnectedRoom() {
        return connectedRoom;
    }
}