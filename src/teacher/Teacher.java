package teacher;
import map.Door;
import model.Character;
import java.util.Random;
public class Teacher extends Character {
    private String name;
    private int aiLevel;
    private QuestionSet questions;
    private Door startDoor;
    private int timeLimit;

    public Teacher(String name, int aiLevel, QuestionSet questions, Door startDoor, int timeLimit) {
        this.name = name;
        this.aiLevel = aiLevel;
        this.questions = questions;
        this.startDoor = startDoor;
        this.timeLimit = timeLimit;
    }

    public void move() {
    }
    public void askQuestion() {

    }
    public void updateAiLevel() {

    }
}