package items;
public class Hammer extends Item {
    private int hp = 2;
    private int chanceToSpawn;

    public Hammer(int hp, String name, int chanceToSpawn) {
        super(name);
        this.hp = hp;
    }

    public void hit() {
    }
}