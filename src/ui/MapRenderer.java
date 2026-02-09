package ui;
import map.*;
import model.Game;
import model.Player;
import teacher.Teacher;

import java.util.List;

public class MapRenderer {
    private Map map;

    public MapRenderer(Map map) {
        this.map = map;
    }

    public void render(Game game) {
        Map map = game.getMap();
        Player player = game.getPlayer();
        List<Teacher> teachers = game.getTeachers();
        Floor currentFloor = game.getPlayer().getCurrentFloor();
        if(player.isInsideRoom()){
            player.getCurrentRoom().printItems();
        }
        System.out.println("\n=== MAPA ===");
        for (Door door : currentFloor.getDoors()) {
            char symbol = '.';
            if (door == player.getCurrentDoor()) {
                symbol = 'H';
            }
            else {
                for (Teacher t : teachers) {
                    if (door == t.getCurrentDoor()) {
                        symbol = 'U';
                    }
                }
            }
            if (symbol == '.') {
                switch (door.getConnectedRoom().getType()) {
                    case STAIRS -> symbol = 'S';
                    case ELEVATOR -> symbol = 'V';
                    case LUNCHROOM -> symbol = 'J';
                    case LAB -> symbol = 'D';
                    case PRINCIPALSOFFICE -> symbol = 'O';
                    case CABINET -> symbol = 'C';
                }
            }
            System.out.print("[" + symbol + "]");
        }
        System.out.println("\n============");
    }
}