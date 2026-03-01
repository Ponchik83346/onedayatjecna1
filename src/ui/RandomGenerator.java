package ui;

import java.util.Random;

/**
 * ma v sobe random. Metoda pro generování šanci spawnu předmetů
 */
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

    /**
     * generace šanci na spawn u předmětů
     * @param chance šance na spawn předmětu
     * @return jestli se spawnul nebo ne
     */
    public boolean generateProbability(int chance){
        return random.nextInt(0, 100)<=chance;
    }
}
