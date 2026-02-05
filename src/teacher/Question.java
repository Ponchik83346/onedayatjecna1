package teacher;

public class Question {
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String correct;
    private String timeLimit;

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getCorrect() {
        return correct;
    }
    public String getTimeLimit() {
        return timeLimit;
    }
    public boolean isCorrect(String input){
        return input.equals(correct);
    }

    @Override
    public String toString() {
        return getQuestion() + "\n" +"moznosti: " +"\n" + getA()+"\n"+getB()+"\n"+getC()+"\n"+getD()+"\n"+ "správná: "+ getCorrect();
    }
}
