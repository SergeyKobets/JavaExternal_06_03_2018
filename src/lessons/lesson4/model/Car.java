package lessons.lesson4.model;

import lessons.lesson4.Abilities.IMove;

public class Car extends Vehicle implements IMove {

    public Car(int price, int year, int speed, int x, int y) {
        super("Car", price, year, speed, x, y);
    }

    public Car(String type, int price, int year, int speed, int x, int y) {
        super(type, price, year, speed, x, y);
    }

    @Override
    public void move() {
        System.out.println("CAR move");
    }
}
