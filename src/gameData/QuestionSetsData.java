package gameData;

import teacher.QuestionSet;

import java.util.ArrayList;
/**
 * Třída pro načtení sad otázek učitelů z jsonu.
 */
public class QuestionSetsData {
    private QuestionSet questionSet;

    public QuestionSet getQuestionSets() {
        return questionSet;
    }

    public void setQuestionSets(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }
}
