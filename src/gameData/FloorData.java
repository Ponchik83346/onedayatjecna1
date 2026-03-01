package gameData;

import java.util.List;

/**
 * Třída pro načtení patra z jsonu.
 */
public class FloorData {
    private int floor;
    private List<RoomData> rooms;

    public int getFloor() {
        return floor; }
    public List<RoomData> getRooms() {
        return rooms; }
}