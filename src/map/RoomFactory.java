package map;

import items.FoodType;
import items.ItemFactory;
import items.MaterialType;

public class RoomFactory {
    public static Room createRoom(RoomType type) {

        Room r = new Room(type);
        return r;
    }
}