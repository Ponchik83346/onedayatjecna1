package teacher;
import map.Door;
import model.GameCharacter;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ui.InputHandler;

public class Teacher extends GameCharacter {

    private String name;
    private int aiLevel;
    private QuestionSet questions;
    private int timeLimit;
    private Door startDoor;

    public Teacher(String name, int aiLevel, QuestionSet questions, Door startDoor, int timeLimit) {
        this.name = name;
        this.aiLevel = aiLevel;
        this.questions = questions;
        this.timeLimit = timeLimit;
        this.currentDoor = startDoor;
        this.startDoor = startDoor;
        this.currentRoom = null;
    }

    public String getName() {
        return name;
    }

    @Override
    public void enterRoom() {
        if (!insideRoom) {
            if(currentDoor.getConnectedRoom().addTeacher(this)){
                insideRoom = true;
                currentRoom = currentDoor.getConnectedRoom();
                setCurrentDoor(null);
            }
        }
    }
    @Override
    public void exitRoom() {
        if (insideRoom) {
            insideRoom = false;
            currentRoom.removeTeacher(this);
            setCurrentDoor(currentRoom.getDoor());
        }
    }

    public void moveAI(Random rand) {
        if (isInsideRoom()) {
            if (Math.random() > 0.5) exitRoom();
            return;
        }
        if (currentDoor == null){
            return;
        }
        double r = Math.random();
        if (r < 0.4) moveLeft();
        else if (r < 0.8) moveRight();
        else enterRoom();
    }

    public boolean askQuestion(Random rand, Scanner input) {
        Question q = questions.getQuestions().get(rand.nextInt(questions.getQuestions().size()));
        System.out.println(q);
        long start = System.currentTimeMillis();
        String answer = input.nextLine().toUpperCase();
        long end = System.currentTimeMillis();
        long seconds = (end - start) / 1000;
        if (seconds > timeLimit) {
            System.out.println("Čas vypršel!");
            return false;
        }
        if (q.isCorrect(answer)) {
            System.out.println("Správně!");
            currentDoor = startDoor;
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

    public Door getStartDoor() {
        return startDoor;
    }

    public void setStartDoor(Door startDoor) {
        this.startDoor = startDoor;
    }
}