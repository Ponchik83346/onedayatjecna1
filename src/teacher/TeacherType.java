package teacher;

public enum TeacherType {
    NEUGEBAUEROVA(10, 20),
    SEDOVA(12, 20),
    STUDENKOVA(12, 12),
    PROCHAZKA(10, 15),
    KUCHARIK(15, 30),
    ZILINCAR(18, 60),
    PAPULA(12, 20),
    BODNAROVA(12, 20),
    MANDIK(20, 15);

    public final int baseAI;
    public final int timeLimit;

    TeacherType(int ai, int timeLimit) {
        this.baseAI = ai;
        this.timeLimit = timeLimit;
    }
}