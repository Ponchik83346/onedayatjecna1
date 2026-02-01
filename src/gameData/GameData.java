package gameData;
import items.Food;
import items.FoodType;
import items.Item;
import items.Material;
import map.Room;
import model.GameCharacter;
import teacher.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameData {
    public ArrayList<Food> food;
    public ArrayList<Material> materials;
    public ArrayList<Teacher> teachers;

    private GameData() {
        teachers = new ArrayList<>();
        materials = new ArrayList<>();
        food = new ArrayList<>();
    }

    public static GameData load() {
        GameData data = new GameData();
        data.createTeachers();
        data.createFood();
        data.createMaterials();

        return data;
    }
    public void createTeachers() {
        HashMap<String, QuestionSet> questionSets = QuestionSetFactory.createQuestionSets();
        List<TeacherData> teacherDataList = JsonLoader.load("/teachers.json", TeachersData.class).getTeachers();

        for (TeacherData data : teacherDataList) {
            Teacher t = new Teacher(data.getName(), data.getAiLevel(), questionSets.get(data.getName()), data.getStartDoorId(), data.getTimeLimit());
            teachers.add(t);
        }
    }
    public void createFood(){
        ArrayList<FoodData> foodDataList = JsonLoader.load("/food.json", FoodsData.class).getFood();
        for(FoodData data : foodDataList){
            Food f = new Food(data.getStamina(), data.getName());
            food.add(f);
        }
    }
    public void createMaterials(){
        ArrayList<MaterialData> materialsData = JsonLoader.load("/materials.json", MaterialsData.class).getMaterials();
        for(MaterialData data : materialsData){
            Material m = new Material(data.getHp(), data.getName(), data.getChanceToSpawn());
            materials.add(m);
        }
    }


    public List<Teacher> getTeachers() {
        return teachers; }
    public List<Food> getFood() {
        return food; }
    public List<Material> getMaterials() {
        return materials; }
}
