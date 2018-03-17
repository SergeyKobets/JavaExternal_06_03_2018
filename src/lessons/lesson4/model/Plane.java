package lessons.lesson4.model;

import lessons.lesson4.Abilities.IFly;

public class Plane extends Vehicle implements IFly {
    private int height;
    private int numberOfPassengers;

    public Plane(int price, int year, int speed, int x, int y, int height, int numberOfPassengers) {
        super("Plane", price, year, speed, x, y);
        this.height = height;
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", height=" + height +
                ", numberOfPassengers=" + numberOfPassengers;
    }

    @Override
    public void fly() {
        System.out.println("PLANE fly");
    }
}
