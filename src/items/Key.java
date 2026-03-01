package items;

import gameData.GameData;
import map.Door;
import map.Floor;
import map.Map;
import map.RoomType;
import model.Player;

/**
 * Třída pro klíč.
 */
public class Key extends Item {
    public Key(String name, int chanceToSpawn) {
        super(name, chanceToSpawn, ItemType.KEY);
    }

    /**
     * Využít klič na výtah.
     * @param player Player pro přístup k poloze a inventáři.
     */
    public void use(Player player) {
        if(player.getCurrentDoor().getConnectedRoom().getType()==RoomType.ELEVATOR){
            boolean unlock = GameData.unlockAllElevators();
            if (unlock) {
                player.getInventory().removeItem(this);
                System.out.println("Všechny výtahové dveře odemčeny!");
            } else {
                System.out.println("Žádný výtah k odemčení!");
            }
        } else {
            System.out.println("Nejste u výtahu!");
        }
    }
}