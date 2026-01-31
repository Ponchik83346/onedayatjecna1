package gameData;

public class TeacherData {
    private String name;
    private int aiLevel;
    private String questionSet; // název souboru s otázkami
    private int timeLimit;

    public String getName() { return name; }
    public int getAiLevel() { return aiLevel; }
    public String getQuestionSet() { return questionSet; }
    public int getTimeLimit() { return timeLimit; }
}