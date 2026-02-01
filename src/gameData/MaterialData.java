package gameData;

public class MaterialData {
    private String name;
    private int hp;
    private int chanceToSpawn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChanceToSpawn() {
        return chanceToSpawn;
    }

    public void setChanceToSpawn(int chanceToSpawn) {
        this.chanceToSpawn = chanceToSpawn;
    }

    public void setMaterialName(String materialName) {
        this.name = materialName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
