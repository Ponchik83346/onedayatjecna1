package items;

public class ItemFactory {

    public static Item createFood(FoodType type) {
        return new Food(type.stamina, type.name());
    }

    public static Item createKey() {
        return new Key("key");
    }

    public static Item createMaterial(MaterialType type) {
        return new Material(type.hp, type.name());
    }

    public static Item createHammer(){
        return new Hammer(2, "Hammer");
    }
}
