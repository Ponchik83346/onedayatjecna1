package gameData;
import items.Food;
import items.Item;
import items.Material;
import map.Room;
import model.GameCharacter;
import teacher.Question;
import teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    public ArrayList<Food> food;
    public ArrayList<Material> materials;
    public ArrayList<Teacher> teachers;
    public ArrayList<Room> rooms;
    public ArrayList<Question> questions;

    private GameData() {

    }

    public static GameData load() {
        GameData data = new GameData();

        data.teachers = JsonLoader.load("/teachers.json", TeachersData.class).getTeachers();
        data.rooms    = JsonLoader.load("/rooms.json", RoomsData.class).getRooms();
        data.food     = JsonLoader.load("/food.json", FoodData.class).getFood();
        data.materials = JsonLoader.load("/materials.json", MaterialsData.class).getMaterials();

        return data;
    }


    public List<Teacher> getTeachers() { return teachers; }
    public List<Room> getRooms() { return rooms; }
    public List<Food> getFood() { return food; }
    public List<Material> getMaterials() { return materials; }
}
