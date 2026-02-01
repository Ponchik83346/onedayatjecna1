package map;
import java.util.List;
public class Map {
    private List<Floor> floors;

    public Map(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Floor getFloor(int level) {
        for (Floor f : floors) {
            if (f.getLevel() == level) return f;
        }
        return null;
    }
}