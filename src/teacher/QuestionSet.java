package teacher;
import java.util.ArrayList;
import java.util.List;
public class QuestionSet {
    private List<Question> questions = new ArrayList<>();
    public QuestionSet() {

    }
    public List<Question> getQuestions() {
        return questions;
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }
}
