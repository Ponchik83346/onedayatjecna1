package teacher;
import map.Door;
import model.GameCharacter;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    public void moveAI(Random rand) {
        if(rand.nextInt() < aiLevel) {
            if (Math.random() < 0.5) {
                moveLeft();
            } else {
                moveRight();
            }
        }
    }

    public boolean askQuestion(Random rand, Scanner scanner) {
        Question q = questions.getQuestions().get(rand.nextInt(questions.getQuestions().size()));
        System.out.println(q);
        long start = System.currentTimeMillis();
        String answer = scanner.nextLine().toUpperCase();
        long end = System.currentTimeMillis();
        long seconds = (end - start) / 1000;
        if (seconds > timeLimit) {
            System.out.println("Time's up!");
            return false;
        }
        if (q.isCorrect(answer)) {
            System.out.println("Správně!");
            return true;
        } else {
            System.out.println("Špatně!");
            return false;
        }
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