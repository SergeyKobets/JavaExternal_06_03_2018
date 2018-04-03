package factory;

import model.Gemstone;
import model.SemiPreciousStone;
import model.Stone;

import java.util.Random;

public class RandomStoneFactory {
    private Random rand = new Random(System.currentTimeMillis());

    public Stone next() {
        switch (rand.nextInt(2)) {
            default:
            case 0:
                return new Gemstone(takeName(), rand.nextInt(40), rand.nextInt(3000), rand.nextInt(5));
            case 1:
                return new SemiPreciousStone(takeName(), 10d, 20000f, 3.12f);
        }
    }


    public String takeName() {
        String[] names = {"Agate", "Amethyst", "Aquamarine", "Aventurine", "Emerald"};
        return names[rand.nextInt(names.length)];
    }
}
