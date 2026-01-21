package items;

public class Food extends Item {
    private int staminaAdded;

    public Food(int staminaAdded, String name) {
        super(name);
        this.staminaAdded = staminaAdded;
    }

    public int getStaminaAdded() {
        return staminaAdded;
    }
}