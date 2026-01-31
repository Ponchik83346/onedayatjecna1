package teacher;
import java.util.ArrayList;
import java.util.List;
public class QuestionSet {
    private List<Question> questions = new ArrayList<Question>();
    public QuestionSet() {

    }
    public List<Question> getQuestions() {
        return questions;
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }
}
