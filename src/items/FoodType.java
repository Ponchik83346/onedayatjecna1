package items;

public enum FoodType {
    BAGETA(20),
    CORNFLAKES(25),
    KEBAB(30),
    PANINI(15),
    HOTDOG(10),
    SVICKOVA(50),
    SUNKOFLEKY(75),
    PIZZA(100);

    public final int stamina;

    FoodType(int stamina) {
        this.stamina = stamina;
    }
}