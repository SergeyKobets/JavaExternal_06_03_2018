package lessons.lesson4.model;

import lessons.lesson4.Abilities.IMove;
import lessons.lesson4.Abilities.ISwim;

public class CarAmphibian extends Car implements ISwim, IMove {
    public CarAmphibian(int price, int year, int speed, int x, int y) {
        super("Amphibian Car", price, year, speed, x, y);
    }

    @Override
    public void swim() {
        System.out.println("CAR AMPHIBIAN swim");
    }

    @Override
    public void move() {
        System.out.println("CAR AMPHIBIAN move");
    }
}
