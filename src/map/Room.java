package map;
import items.Key;
import teacher.Teacher;
import items.Food;
import items.Material;
import java.util.List;
public class Room {
    private RoomType type;
    private List<Teacher> teachersInside;
    private boolean hasTest;
    private List<Food> food;
    private List<Material> materials;
    private Boolean hasKey;

    public Room(RoomType type) {
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }
    public void addFood(Food food) {
        this.food.add(food);
    }
    public void addTeacher(Teacher teacher) {
        teachersInside.add(teacher);
    }
    public void addKey(Key key) {
        hasKey = true;
    }
}