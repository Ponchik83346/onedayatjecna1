package map;
import java.util.List;
public class Map {
    private List<Floor> floors;
    public Floor getFloor(int level) {
        return floors.get(level);
    }
}