package items;

import map.Door;
import model.Player;

public class Hammer extends Item {
    private int hp = 2;

    public Hammer(int hp, String name, int chanceToSpawn) {
        super(name, chanceToSpawn, ItemType.HAMMER);
        this.hp = hp;
    }

    public void use(Player player, Material material, Door door) {
        if (material == null || door == null){
            return;
        }
        door.setLocked(true);
        hp--;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}