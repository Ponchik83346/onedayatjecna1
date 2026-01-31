package teacher;

import gameData.JsonLoader;
import gameData.QuestionSetsData;

import java.util.ArrayList;
import java.util.List;

public class QuestionSetFactory {

    public static List<QuestionSetsData> createQuestionSets() {
        List<QuestionSetsData> sets = new ArrayList<>();
        String[] files = JsonLoader.load("/questions/" + "index.json", String[].class);

        for (String fileName : files) {
            QuestionSetsData data = JsonLoader.load("/questions/" + fileName, QuestionSetsData.class);
            sets.add(data);
        }

        return sets;
    }
}