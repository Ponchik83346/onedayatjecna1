package map;

import java.util.List;

public class Floor {
    private final int level;
    private final List<Door> doors;

    public Floor(int level, List<Door> doors) {
        this.level = level;
        this.doors = doors;
    }

    public int getLevel() {
        return level; }
    public List<Door> getDoors() {
        return doors; }
}