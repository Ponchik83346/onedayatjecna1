package items;

import map.Door;
import model.Player;

public class Material extends Item {
    private int hp;

    public Material(int hp, String name, int chanceToSpawn) {
        super(name, chanceToSpawn, ItemType.MATERIAL);
        this.hp = hp;
    }

}