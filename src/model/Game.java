package model;

import teacher.Teacher;
import map.Map;
import command.Command;

import java.util.List;

public class Game {

    private GameState state = GameState.INTRO;

    private int globalTime = 0;
    private int lastTeacherUpdate = 0;

    private Player player;
    private Map map;
    private List<Teacher> teachers;

    public void startGame() {
    }

    public void onPlayerAction() {
    }

    public void execute(Command command) {
    }

    public boolean isRunning() {
        return state == GameState.PLAYING;
    }
    public void updateTeachers() {

    }
}

