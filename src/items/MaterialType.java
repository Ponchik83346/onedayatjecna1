package items;

public enum MaterialType {
    WOOD(4),
    IRON(8),
    OBSIDIAN(12);

    public final int hp;

    MaterialType(int hp) {
        this.hp = hp;
    }
}