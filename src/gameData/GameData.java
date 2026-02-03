package gameData;
import items.*;
import map.*;
import model.Player;
import teacher.*;
import ui.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameData {
    private static ArrayList<Food> food;
    private static ArrayList<Material> materials;
    private static ArrayList<Teacher> teachers;
    private static ArrayList<Key> keys;
    private static ArrayList<Item> items;
    private static Player player;
    private static Map map;

    public GameData() {
        teachers = new ArrayList<>();
        materials = new ArrayList<>();
        food = new ArrayList<>();
        items = new ArrayList<>();
        player = new Player();
    }

    public static GameData load() {
        GameData data = new GameData();
        map = MapFactory.load();
        data.createTeachers();
        data.createFood();
        data.createMaterials();
        createItems(food, materials);
        return data;
    }

    public static void createItems(ArrayList<Food> food, ArrayList<Material> materials){
        items.addAll(food);
        items.addAll(materials);
    }

    private void createTeachers() {
        HashMap<String, QuestionSet> questionSets = QuestionSetFactory.createQuestionSets();
        List<TeacherData> teacherDataList = JsonLoader.load("/teachers.json", TeachersData.class).getTeachers();
        HashMap<String, Door> doorsById = new HashMap<>();
        for (Floor floor : map.getFloors()) {
            for (Door door : floor.getDoors()) {
                doorsById.put(door.getId(), door);
            }
        }
        for (TeacherData data : teacherDataList) {
            Door startDoor = doorsById.get(data.getStartDoorId());
            if (startDoor == null) {
                throw new IllegalStateException("Start door not found for teacher: " + data.getName());
            }
            Teacher t = new Teacher(
                    data.getName(),
                    data.getAiLevel(),
                    questionSets.get(data.getName()),
                    startDoor,
                    data.getTimeLimit()
            );
            teachers.add(t);
        }
    }

    private void createFood() {
        ArrayList<FoodData> foodDataList = JsonLoader.load("/food.json", FoodsData.class).getFood();
        for (FoodData data : foodDataList) {
            Food f = new Food(data.getStamina(), data.getName(), data.getChanceClass(), data.getChanceBuffet(), data.getChanceCafeteria());
            food.add(f);
        }
    }

    private void createMaterials() {
        ArrayList<MaterialData> materialsData = JsonLoader.load("/materials.json", MaterialsData.class).getMaterials();
        for (MaterialData data : materialsData) {
            Material m = new Material(data.getHp(), data.getName(), data.getChanceToSpawn());
            materials.add(m);
        }
    }


    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Food> getFood() {
        return food;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public Player getPlayer() {
        return player;
    }

    public Floor getFloorByLevel(int Level) {
        return map.getFloor(Level);
    }

    public Door getDoorByLevel(int floor, int doorId) {
        return map.getFloor(floor).getDoors().get(doorId);
    }

    public static Map getMap() {
        return map;
    }

    public static boolean unlockAllElevators() {
        boolean foundElevator = false;
        for (Floor floor : map.getFloors()) {
            for (Door door : floor.getDoors()) {
                if (door.getConnectedRoom() != null
                        && door.getConnectedRoom().getType() == RoomType.ELEVATOR) {
                    door.setLocked(false);
                    foundElevator = true;
                }
            }
        }
        return foundElevator;
    }

    private void generateItems(RandomGenerator rnd) {
        for (Floor f : map.getFloors()) {
            for (Door d : f.getDoors()) {
                if (rnd.generateProbability(50)) {
                    putItemsIntoRoom(d, rnd, items);
                }
            }
        }
    }

    private void putItemsIntoRoom(Door d, RandomGenerator rnd, List<Item> items) {
        int itemCount = 0;
        while (itemCount != d.getConnectedRoom().getMaxItemCountPerRoom())
            for (Item item : items) {
                if(rnd.generateProbability(item.getChanceToSpawn())){
                    if(item.getType()==ItemType.FOOD){
                        d.getConnectedRoom().getFood().add(item);
                    }
                }
            }
    }

    private void putKeysIntoCabinets(){

    }
    private void putHammerIntoRooms(Door d, RandomGenerator rnd){

    }
    private void putTestsIntoRooms(){

    }
}
