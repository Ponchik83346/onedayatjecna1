package items;

public class Material extends Item {
    private int hp;

    public Material(int hp, String name) {
        super(name);
        this.hp = hp;
    }
}