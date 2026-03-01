package gameData;

import java.util.List;
/**
 * Třída pro načtení mapy z jsonu.
 */
public class MapData {
    private List<FloorData> floors;

    public List<FloorData> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorData> floors) {
        this.floors = floors;
    }
}