package items;
public class Key extends Item {
    private boolean used = false;

    public Key(String name) {
        super(name);
        used = false;
    }

    public void use() {
    }
}