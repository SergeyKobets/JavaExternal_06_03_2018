package lessons.lesson4.model;

import lessons.lesson4.Abilities.IFly;
import lessons.lesson4.Abilities.ISwim;

public class Batmobile extends Car implements IFly, ISwim {
    public Batmobile(int price, int year, int speed, int x, int y) {
        super("BetMobile", price, year, speed, x, y);
    }

    @Override
    public void fly() {
        System.out.println("BATMOBILE fly");
    }

    @Override
    public void move() {
        System.out.println("BATMOBILE move");
    }

    @Override
    public void swim() {
        System.out.println("BATMOBILE swim");
    }
}
