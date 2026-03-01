package gameData;

import map.RoomType;
/**
 * Třída pro načtení místnosti z jsonu.
 */
public class RoomData {
    private String id;
    private RoomType type;
    private String downStairsId;
    private String upStairsId;

    public String getId() {
        return id;
    }
    public RoomType getType() {
        return type;
    }

    public String getDownStairsId() {
        return downStairsId;
    }

    public String getUpStairsId() {
        return upStairsId;
    }
}