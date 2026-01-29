package items;

public class Material extends Item {
    private int hp;

    public Material(int hp, String name, int chanceToSpawn) {
        super(name, chanceToSpawn);
        this.hp = hp;
    }
}