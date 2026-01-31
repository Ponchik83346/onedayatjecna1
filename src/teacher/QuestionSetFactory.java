package teacher;

import gameData.JsonLoader;
import gameData.QuestionSetsData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import teacher.TeacherType;

public class QuestionSetFactory {
    public static HashMap<String, QuestionSet> createQuestionSets() {
        HashMap<String, QuestionSet> sets = new HashMap<>();
        for (TeacherType teacher : TeacherType.values()) {
            String fileName = "/questions/" + teacher.name() + ".json";
            Question[] questions = JsonLoader.load(fileName, Question[].class);

            QuestionSet qs = new QuestionSet();
            qs.getQuestions().addAll(Arrays.asList(questions));

            sets.put(teacher.name(), qs);
        }

        return sets;
    }
}