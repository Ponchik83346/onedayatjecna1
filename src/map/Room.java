package map;
import items.*;
import teacher.Teacher;

import java.util.ArrayList;
import java.util.List;
public class Room {
    private String id;
    private RoomType type;
    private Door door;
    private int capacity;
    private ArrayList<Teacher> teachersInside;
    private boolean hasTest;
    private List<Food> food;
    private List<Material> materials;
    private Hammer hammer;
    private boolean hasKey;
    private int maxItemCountPerRoom;
    private ArrayList<Item> items;

    public Room(String id, RoomType type) {
        this.id = id;
        this.type = type;
        this.food = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.teachersInside = new ArrayList<>();
        if (type == RoomType.LAB || type == RoomType.LUNCHROOM) {
            this.capacity = 2;
            this.maxItemCountPerRoom = 3;
        } else if(type == RoomType.BUFET){
            this.maxItemCountPerRoom = 2;
            this.capacity = 1;
        } else {
            this.capacity = 1;
            this.maxItemCountPerRoom = 5;
        }
        items = new ArrayList<>();
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
    public void removeTeacher(Teacher teacher) {
        teachersInside.remove(teacher);
    }

    public ArrayList<Teacher> getTeachersInside() {
        return teachersInside;
    }

    public void setTeachersInside(ArrayList<Teacher> teachersInside) {
        this.teachersInside = teachersInside;
    }

    public void addKey(Key key) {
        hasKey = true;
    }
    public void addTest() {
        hasTest = true;
    }

    public RoomType getType() {
        return type;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public int getMaxItemCountPerRoom() {
        return maxItemCountPerRoom;
    }

    public void setMaxItemCountPerRoom(int maxItemCountPerRoom) {
        this.maxItemCountPerRoom = maxItemCountPerRoom;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void printItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i).getName());
        }
    }
}