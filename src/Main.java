import gameData.GameData;
import gameData.QuestionSetsData;
import map.Map;
import map.MapFactory;
import teacher.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameData GD = GameData.load();
        Map map = MapFactory.load();
        GD.createTeachers();
        for (Teacher t : GD.getTeachers()) {
            System.out.println("Učitel: " + t.getName());
            for (Question q : t.getQuestionSet().getQuestions()) {
                System.out.println(q);
                System.out.println();
            }
        }
    }
}