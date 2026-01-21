package teacher;

public enum TeacherType {
    NEUGEBAUEROVA(10, new QuestionSet(), 20),
    SEDOVA(12, new QuestionSet(), 20),
    STUDENKOVA(12, new QuestionSet(), 12),
    PROCHAZKA(10, new QuestionSet(), 15),
    KUCHARIK(15, new QuestionSet(), 30),
    ZILINCAR(18, new QuestionSet(), 60),
    PAPULA(12, new QuestionSet(), 20),
    BODNAROVA(12, new QuestionSet(), 20),
    MANDIK(20, new QuestionSet(), 15);

    public final int baseAI;
    public final QuestionSet questionSet;
    public final int timeLimit;

    TeacherType(int ai, QuestionSet questionSet, int timeLimit) {
        this.baseAI = ai;
        this.questionSet = questionSet;
        this.timeLimit = timeLimit;
    }
}