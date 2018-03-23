package lessons.lesson4.model;

import lessons.lesson4.abilities.IMove;
import lessons.lesson4.abilities.ISwim;

public class CarAmphibian extends Car implements ISwim, IMove {
    public CarAmphibian(int price, int year, int speed, int x, int y) {
        super("Amphibian Car", price, year, speed, x, y, "AADD", "steam");
    }

    @Override
    public String swim() {
        return getType() + " плывет со скоростью: " + getSpeed() * 2;
    }

    @Override
    public String move() {
        return getType() + " едет со скоростью: " + getSpeed() * 2;
    }
}
