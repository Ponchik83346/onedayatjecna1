package items;

import gameData.GameData;
import map.Door;
import map.Floor;
import map.Map;
import map.RoomType;
import model.Player;

public class Key extends Item {

    public Key(String name) {
        super(name);
    }

    public void use(Player player) {
        boolean unlock = GameData.unlockAllElevators();
        if (unlock) {
            player.getInventory().removeItem(this);
            System.out.println("Všechny výtahové dveře odemčeny!");
        } else {
            System.out.println("Žádný výtah k odemčení!");
        }
    }
}