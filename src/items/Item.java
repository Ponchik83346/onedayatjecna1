package items;

import map.Door;
import model.Player;

public abstract class Item {
    protected String name;
    protected int chanceToSpawn;
    protected ItemType type;

    public Item(String name, int chanceToSpawn, ItemType type) {
        this.name = name;
        this.chanceToSpawn = chanceToSpawn;
        this.type = type;
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChanceToSpawn() {
        return chanceToSpawn;
    }

    public void setChanceToSpawn(int chanceToSpawn) {
        this.chanceToSpawn = chanceToSpawn;
    }
}