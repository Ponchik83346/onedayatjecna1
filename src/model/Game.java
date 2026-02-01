package model;

import gameData.GameData;
import teacher.Teacher;
import map.Map;
import command.Command;

import java.util.List;

public class Game {
    private GameData gameData;
    private GameState state;
    private int globalTime;
    private int lastTeacherUpdate;

    public Game() {
    }

    public void initialize() {
        this.gameData = GameData.load();
        this.gameData.getPlayer().setCurrentFloor(gameData.getFloorByLevel(3));
        this.state = GameState.INTRO;
        this.globalTime = 0;
        this.lastTeacherUpdate = 0;
    }

    public void startGame() {
    }

    public void onPlayerAction() {
        updateTeachers();
        lastTeacherUpdate = globalTime;
    }

    public void execute(Command command) {
    }

    public boolean isRunning() {
        return state == GameState.PLAYING;
    }
    public void updateTeachers() {
        if(globalTime-lastTeacherUpdate < 3) {
            for(Teacher teacher : gameData.getTeachers()) {
                teacher.moveAI();
            }
        }
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
    public Map getMap(){
        return GameData.getMap();
    }
    public Player getPlayer(){
        return gameData.getPlayer();
    }
    public List<Teacher> getTeachers(){
        return gameData.getTeachers();
    }
}

