package teacher;
import map.Door;
import model.GameCharacter;

import java.util.Random;

public class Teacher extends GameCharacter {

    private String name;
    private int aiLevel;
    private QuestionSet questions;
    private int timeLimit;

    public Teacher(String name, int aiLevel, QuestionSet questions, Door startDoor, int timeLimit) {
        this.name = name;
        this.aiLevel = aiLevel;
        this.questions = questions;
        this.timeLimit = timeLimit;
        this.currentDoor = startDoor;
    }

    public String getName() {
        return name;
    }

    public void moveAI() {
        Random rand = new Random();
        if(rand.nextInt() < aiLevel) {
            if (Math.random() < 0.5) {
                moveLeft();
            } else {
                moveRight();
            }
        }
    }

    public void askQuestion() {

    }

    public QuestionSet getQuestions() {
        return questions;
    }

    public int getAiLevel() {
        return aiLevel;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}