package items;

public class Food extends Item {
    private int stamina;
    private int chanceClass;
    private int chanceBuffet;
    private int chanceCafeteria;

    public Food(int stamina, String name) {
        super(name);
        this.stamina = stamina;
        this.chanceClass = chanceClass;
        this.chanceBuffet = chanceBuffet;
        this.chanceCafeteria = chanceCafeteria;
    }

    public int getStaminaAdded() {
        return stamina;
    }
}