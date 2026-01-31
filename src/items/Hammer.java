package items;
public class Hammer extends Item {
    private int hp = 2;

    public Hammer(int hp, String name, int chanceToSpawn) {
        super(name, chanceToSpawn);
        this.hp = hp;
    }

    public void hit() {
    }
}