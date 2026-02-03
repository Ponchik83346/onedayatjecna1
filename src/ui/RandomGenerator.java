package ui;

import java.util.Random;

public class RandomGenerator {
    private Random random = new Random();

    public RandomGenerator() {
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public boolean generateProbability(int chance){
        return random.nextInt(0, 100)<=chance;
    }
}
