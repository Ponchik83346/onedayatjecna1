package gameData;
/**
 * Třída pro načtení učitele z jsonu.
 */
public class TeacherData {
    private String name;
    private int aiLevel;
    private String questionSet;
    private int timeLimit;
    private String startDoorId;

    public String getName() {
        return name; }
    public int getAiLevel() {
        return aiLevel; }
    public String getQuestionSet() {
        return questionSet; }
    public int getTimeLimit() {
        return timeLimit; }
    public String getStartDoorId() {
        return startDoorId;
    }
}