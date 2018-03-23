package lessons.lesson4.model;

import lessons.lesson4.abilities.IFly;
import lessons.lesson4.abilities.ISwim;

public class Batmobile extends Car implements IFly, ISwim {
    public Batmobile(int price, int year, int speed, int x, int y) {
        super("BetMobile", price, year, speed, x, y, "500391aa", "TURBO");

    }

    @Override
    public String fly() {
        return getType() + " летит со скоростью: " + getSpeed() * 5;
    }

    @Override
    public String move() {
        return getType() + " едет со скоростью: " + getSpeed();
    }

    @Override
    public String swim() {
        return getType() + " плывет со скоростью: " + getSpeed() * 2;
    }
}
