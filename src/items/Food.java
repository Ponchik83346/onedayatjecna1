package items;

public class Food extends Item {
    private int stamina;
    private int chanceClass;
    private int chanceBuffet;
    private int chanceCafeteria;

    public Food(int stamina, String name, int chanceClass, int chanceBuffet, int chanceCafeteria) {
        super(name);
        this.stamina = stamina;
        this.chanceClass = chanceClass;
        this.chanceBuffet = chanceBuffet;
        this.chanceCafeteria = chanceCafeteria;
    }

    public int getStaminaAdded() {
        return stamina;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getChanceBuffet() {
        return chanceBuffet;
    }

    public void setChanceBuffet(int chanceBuffet) {
        this.chanceBuffet = chanceBuffet;
    }

    public int getChanceClass() {
        return chanceClass;
    }

    public void setChanceClass(int chanceClass) {
        this.chanceClass = chanceClass;
    }

    public int getChanceCafeteria() {
        return chanceCafeteria;
    }

    public void setChanceCafeteria(int chanceCafeteria) {
        this.chanceCafeteria = chanceCafeteria;
    }
}