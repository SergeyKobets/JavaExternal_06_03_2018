package lessons.lesson4.model;

import lessons.lesson4.abilities.IMove;

public class Car extends Vehicle implements IMove {

    public Car(int price, int year, int speed, int x, int y) {
        super("Car", price, year, speed, x, y, "TD5", "diesel");
    }

    public Car(String type, int price, int year, int speed, int x, int y, String engineModel, String engineType) {
        super(type, price, year, speed, x, y, engineModel, engineType);
    }

    @Override
    public String move() {
        return getType() + " едет со скоростью: " + getSpeed();
    }
}
