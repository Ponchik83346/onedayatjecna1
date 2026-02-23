package items;

import map.Door;
import model.Player;

public class Hammer extends Item {
    private int hp;

    public Hammer(String name, int chanceToSpawn) {
        super(name, chanceToSpawn, ItemType.HAMMER);
        this.hp = 2;
    }

    public void use(Player player, Material material, Door door) {
        if (door.isLocked()) {
            System.out.println("Dveře už jsou zabarikádované!");
            return;
        }
        door.setLocked(true);
        door.setMaterial(material);
        hp--;
        System.out.println("Zabarikádovali jste dveře pomocí " + material.getName());
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}