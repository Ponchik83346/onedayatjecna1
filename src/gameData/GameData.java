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
        data.food = JsonLoader.load("/food.json", FoodData.class).getFood();
        data.materials = JsonLoader.load("/materials.json", MaterialsData.class).getMaterials();

        return data;
    }
    public void createTeachers() {
        HashMap<String, QuestionSet> questionSets = QuestionSetFactory.createQuestionSets();
        List<TeacherData> teacherDataList = JsonLoader.load("/teachers.json", TeachersData.class).getTeachers();

        for (TeacherData data : teacherDataList) {
            Teacher t = new Teacher();
            t.setName(data.getName());
            t.setAiLevel(data.getAiLevel());
            t.setTimeLimit(data.getTimeLimit());
            t.setQuestions(questionSets.get(data.getName()));

            teachers.add(t);
        }
    }
    public void createFood(){

    }


    public List<Teacher> getTeachers() {
        return teachers; }
    public List<Food> getFood() {
        return food; }
    public List<Material> getMaterials() {
        return materials; }
}
