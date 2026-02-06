package model;
import gameData.GameData;
import map.Door;
import map.Map;
import teacher.Teacher;
import command.Command;
import ui.InputHandler;
import ui.RandomGenerator;
import java.util.List;

public class Game {
    private GameData gameData;
    private GameState state;
    private double globalTime = 0;
    private long lastUpdateNano = 0;
    private double lastTeacherUpdate = 0;
    private RandomGenerator randomGenerator;
    public Game() {
    }

    public void initialize() {
        this.gameData = GameData.load();
        this.randomGenerator = new RandomGenerator();
        this.gameData.getPlayer().setCurrentFloor(gameData.getFloorByLevel(3));
        this.gameData.getPlayer().setCurrentDoor(gameData.getDoorByLevel(3, 2));
        this.state = GameState.PLAYING;
        this.globalTime = 0;
        this.lastTeacherUpdate = 0;
        this.lastUpdateNano = System.nanoTime();
    }
    public void update() {
        updateTeachers();
    }
    public void updateTeachers() {
        long now = System.nanoTime();
        double delta = (now - lastUpdateNano) / 1_000_000_000.0;
        lastUpdateNano = now;
        globalTime += delta;
        if (globalTime - lastTeacherUpdate >= 3.0) {
            for (Teacher t : getTeachers()) {
                t.moveAI(randomGenerator.getRandom());
            }
            lastTeacherUpdate = globalTime;
        }
    }

    public boolean isRunning() {
        return state == GameState.PLAYING;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
    public double getGlobalTime() {
        return globalTime;
    }

    public void setGlobalTime(int globalTime) {
        this.globalTime = globalTime;
    }

    public double getLastTeacherUpdate() {
        return lastTeacherUpdate;
    }

    public void setLastTeacherUpdate(int lastTeacherUpdate) {
        this.lastTeacherUpdate = lastTeacherUpdate;
    }
    public Map getMap() {
        return GameData.getMap();
    }
    public Player getPlayer() {
        return gameData.getPlayer();
    }
    public List<Teacher> getTeachers() {
        return gameData.getTeachers();
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void setGlobalTime(double globalTime) {
        this.globalTime = globalTime;
    }

    public long getLastUpdateNano() {
        return lastUpdateNano;
    }

    public void setLastUpdateNano(long lastUpdateNano) {
        this.lastUpdateNano = lastUpdateNano;
    }

    public void setLastTeacherUpdate(double lastTeacherUpdate) {
        this.lastTeacherUpdate = lastTeacherUpdate;
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}

