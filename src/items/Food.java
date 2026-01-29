package items;

public class Food extends Item {
    private int stamina;

    public Food(int stamina, String name, int chanceToSpawn) {
        super(name, chanceToSpawn);
        this.stamina = stamina;
    }

    public int getStaminaAdded() {
        return stamina;
    }
}