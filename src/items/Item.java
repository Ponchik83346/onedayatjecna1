package items;

public abstract class Item {
    protected String name;
    protected int chanceToSpawn;

    public Item(String name, int chanceToSpawn) {
        this.name = name;
        this.chanceToSpawn = chanceToSpawn;
    }

    public String getName() {
        return name;
    }
}