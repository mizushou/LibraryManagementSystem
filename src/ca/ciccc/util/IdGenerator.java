package ca.ciccc.util;

public class IdGenerator {

    private RandomGenerator rg;

    public IdGenerator() {
        this.rg = new RandomGenerator();
    }

    public String generateId() {

        char c1 = rg.generateRandomAlphabet();
        char c2 = rg.generateRandomAlphabet();
        int n = rg.generateRandomInt(900) + 100;

        return String.valueOf(c1).toUpperCase() + String.valueOf(c2).toUpperCase() + n;

    }
}


