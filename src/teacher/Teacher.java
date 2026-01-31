package teacher;
import map.Door;
import model.GameCharacter;

public class Teacher extends GameCharacter {
    private String name;
    private int aiLevel;
    private QuestionSet questionSet;
    private Door startDoor;
    private int timeLimit;

    public void move() {
    }

    public void askQuestion() {
    }

    public void updateAiLevel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(int aiLevel) {
        this.aiLevel = aiLevel;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public void setQuestions(QuestionSet questions) {
        this.questionSet = questions;
    }

    public Door getStartDoor() {
        return startDoor;
    }

    public void setStartDoor(Door startDoor) {
        this.startDoor = startDoor;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", aiLevel=" + aiLevel +
                ", questionSet=" + questionSet +
                ", startDoor=" + startDoor +
                ", timeLimit=" + timeLimit +
                '}';
    }
}