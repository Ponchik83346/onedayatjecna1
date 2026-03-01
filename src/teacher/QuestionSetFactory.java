package teacher;

import gameData.JsonLoader;
import gameData.QuestionSetsData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import teacher.TeacherType;

/**
 * Factory pro sadu otázek učitele.
 */
public class QuestionSetFactory {
    /**
     * Vytvoří mapu všech sad otázek pro jednotlivé typy učitelů.
     * Pro každého učitele načte odpovídající JSON soubor
     * ze složky /questions, vytvoří QuestionSet a naplní ho
     * načtenými otázkami.
     * @return mapa otázek podle učitele
     */
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