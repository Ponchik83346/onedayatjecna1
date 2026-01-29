package map;
import items.Hammer;
import items.Key;
import teacher.Teacher;
import items.Food;
import items.Material;

import java.util.ArrayList;
import java.util.List;
public class Room {
    private RoomType type;
    private int capacity;
    private ArrayList<Teacher> teachersInside;
    private boolean hasTest;
    private List<Food> food;
    private List<Material> materials;
    private Hammer hammer;
    private Boolean hasKey;

    public Room(RoomType type) {
        this.teachersInside = new ArrayList<>();
        if (type == RoomType.LAB || type == RoomType.LUNCHROOM) {
            this.capacity = 2;
        } else {
            this.capacity = 1;
        }
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }
    public void addFood(Food food) {
        this.food.add(food);
    }
    public void addHammer(Hammer hammer) {
        this.hammer = hammer;
    }
    public boolean addTeacher(Teacher teacher) {
        if (teachersInside.size() < capacity) {
            teachersInside.add(teacher);
            return true;
        }
        return false;
    }
    public void addKey(Key key) {
        hasKey = true;
    }
    public void addTest() {
        hasTest = true;
    }
}