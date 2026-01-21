package items;
public class Hammer extends Item {
    private int hp = 2;

    public Hammer(int hp, String name) {
        super(name);
        this.hp = hp;
    }

    public void hit() {
    }
}