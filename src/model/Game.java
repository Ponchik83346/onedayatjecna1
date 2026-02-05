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
    private int globalTime;
    private int lastTeacherUpdate;
    private RandomGenerator randomGenerator;
    public Game() {
    }

    public void initialize() {
        this.gameData = GameData.load();
        this.randomGenerator = new RandomGenerator();
        this.gameData.getPlayer().setCurrentFloor(
                gameData.getFloorByLevel(3)
        );
        this.gameData.getPlayer().setCurrentDoor(
                gameData.getDoorByLevel(3, 2)
        );
        this.state = GameState.PLAYING;
        this.globalTime = 0;
        this.lastTeacherUpdate = 0;
    }
    public void update(InputHandler input) {
        globalTime++;
        updateTeachers();
        checkCollide(input);
    }
    public void updateTeachers() {
        if (globalTime - lastTeacherUpdate >= 3) {
            for (Teacher teacher : gameData.getTeachers()) {
                teacher.moveAI(randomGenerator.getRandom());
            }
            lastTeacherUpdate = globalTime;
        }
    }
    public boolean checkCollide(InputHandler input) {
        Door playerDoor = gameData.getPlayer().getCurrentDoor();
        for (Teacher t : gameData.getTeachers()) {
            if (t.getCurrentDoor() == playerDoor) {
                boolean correct = t.askQuestion(randomGenerator.getRandom(), input.getScanner());
                if (!correct) {
                    setState(GameState.LOSE);
                    System.out.println("GAME OVER");
                }
                return true;
            }
        }

        return false;
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
    public int getGlobalTime() {
        return globalTime;
    }

    public void setGlobalTime(int globalTime) {
        this.globalTime = globalTime;
    }

    public int getLastTeacherUpdate() {
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
}

