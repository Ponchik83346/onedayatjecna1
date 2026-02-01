import gameData.GameData;
import gameData.QuestionSetsData;
import map.Map;
import map.MapFactory;
import model.Game;
import teacher.*;
import ui.GameUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        gameUI.gameLoop();
    }
}