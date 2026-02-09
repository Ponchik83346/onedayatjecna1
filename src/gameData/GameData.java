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
        keys = new ArrayList<>();
        for(int i = 0; i<10; i++){
            keys.add(new Key("k"+i));
        }
        items = new ArrayList<>();
        player = new Player();
    }

    public static GameData load() {
        GameData data = new GameData();
        map = MapFactory.load();
        data.createTeachers();
        data.createFood();
        data.createMaterials();
        createItems();
        data.generateItems(new RandomGenerator());
        return data;
    }
    public static void createItems() {
        items.clear();
        items.addAll(food);
        items.addAll(materials);
        items.addAll(keys);
    }

    private void createTeachers() {
        HashMap<String, QuestionSet> questionSets =
                QuestionSetFactory.createQuestionSets();

        List<TeacherData> teacherDataList =
                JsonLoader.load("/teachers.json", TeachersData.class).getTeachers();

        HashMap<String, Door> doorsById = new HashMap<>();
        for (Floor floor : map.getFloors()) {
            for (Door door : floor.getDoors()) {
                doorsById.put(door.getId(), door);
            }
        }
        for (TeacherData data : teacherDataList) {
            Door startDoor = doorsById.get(data.getStartDoorId());
            if (startDoor == null) {
                throw new IllegalStateException(
                        "Start door not found for teacher: " + data.getName());
            }
            Teacher t = new Teacher(data.getName(), data.getAiLevel(), questionSets.get(data.getName()), startDoor, data.getTimeLimit());
            teachers.add(t);
        }
    }
    private void createFood() {
        ArrayList<FoodData> foodDataList =
                JsonLoader.load("/food.json", FoodsData.class).getFood();
        for (FoodData data : foodDataList) {
            Food f = new Food(data.getStamina(), data.getName(), data.getChanceClass(), data.getChanceBuffet(), data.getChanceCafeteria());
            food.add(f);
        }
    }
    private void createMaterials() {
        ArrayList<MaterialData> materialsData =
                JsonLoader.load("/materials.json", MaterialsData.class).getMaterials();

        for (MaterialData data : materialsData) {
            Material m = new Material(
                    data.getHp(),
                    data.getName(),
                    data.getChanceToSpawn()
            );
            materials.add(m);
        }
    }
    public List<Teacher> getTeachers() { return teachers; }

    public List<Food> getFood() { return food; }

    public List<Material> getMaterials() { return materials; }

    public Player getPlayer() { return player; }

    public Floor getFloorByLevel(int level) {
        return map.getFloor(level);
    }

    public Door getDoorByLevel(int floor, int doorId) {
        return map.getFloor(floor).getDoors().get(doorId);
    }

    public static Map getMap() { return map; }

    public static boolean unlockAllElevators() {
        boolean foundElevator = false;
        for (Floor floor : map.getFloors()) {
            for (Door door : floor.getDoors()) {
                if (door.getConnectedRoom() != null &&
                        door.getConnectedRoom().getType() == RoomType.ELEVATOR) {

                    door.setLocked(false);
                    foundElevator = true;
                }
            }
        }
        return foundElevator;
    }
    private void generateItems(RandomGenerator rnd) {
        int testCount = 30;
        for (Floor f : map.getFloors()) {
            for (Door d : f.getDoors()) {
                if (d.getConnectedRoom() == null)
                    continue;
                if (rnd.generateProbability(50)) {
                    putItemsIntoRoom(d, rnd);
                }
                while(testCount != 0) {
                    if(rnd.generateProbability(25)){
                        d.getConnectedRoom().setHasTest(true);
                        testCount--;
                    }
                }
            }
        }
    }
    private void putItemsIntoRoom(Door d, RandomGenerator rnd) {
        Room room = d.getConnectedRoom();
        if (room == null) return;
        int max = room.getMaxItemCountPerRoom();
        int count = 0;
        int tries = 0;
        while (count < max && tries < 50) {
            tries++;
            Item item = items.get(rnd.getRandom().nextInt(items.size()));
            switch (room.getType()) {
                case LUNCHROOM -> {
                    if (item instanceof Food f && f.getStamina() >= 60 && rnd.generateProbability(f.getChanceCafeteria())) {
                        room.getItems().add(new Food(f.getStamina(), f.getName(), f.getChanceClass(), f.getChanceBuffet(), f.getChanceCafeteria()));
                        count++;
                    }
                }
                case BUFET -> {
                    if (item instanceof Food f && f.getName().toLowerCase().contains("bageta") && rnd.generateProbability(f.getChanceBuffet())) {
                        room.getItems().add(new Food(f.getStamina(), f.getName(), f.getChanceClass(), f.getChanceBuffet(), f.getChanceCafeteria()));
                        count++;
                    }
                }
                case CABINET -> {
                    if (item instanceof Key k) {
                        room.getItems().add(new Key(k.getName()));
                        count++;
                    } else if(item instanceof Food f && rnd.generateProbability(f.getChanceClass())){
                        room.getItems().add(new Food(f.getStamina(), f.getName(), f.getChanceClass(), f.getChanceBuffet(), f.getChanceClass()));
                    }
                }
                case LAB -> {
                    if (item instanceof Material m && rnd.generateProbability(m.getChanceToSpawn())) {
                        room.getItems().add(new Material(m.getHp(), m.getName(), m.getChanceToSpawn()));
                        count++;
                    } else if (item instanceof Food f && rnd.generateProbability(item.getChanceToSpawn()/2)) {
                        room.getItems().add(new Food(f.getStamina(), f.getName(), f.getChanceClass(), f.getChanceBuffet(), f.getChanceCafeteria()));
                        count++;
                    }
                }
                case CLASSROOM -> {
                    if (item instanceof Food f && rnd.generateProbability(item.getChanceToSpawn())) {
                            room.getItems().add(new Food(f.getStamina(), f.getName(), f.getChanceClass(), f.getChanceBuffet(), f.getChanceCafeteria()));
                            count++;
                    }
                    else if (item instanceof Material m && rnd.generateProbability(m.getChanceToSpawn()/2)) {
                                room.getItems().add(new Material(m.getHp(), m.getName(), m.getChanceToSpawn()));
                                count++;
                    }
                }
            }
        }
    }
}
