package lessons.lesson4.model;

import lessons.lesson4.Abilities.ISwim;

public class Ship extends Vehicle implements ISwim {
    private String portDestination;
    private int numberOfPassengers;

    public Ship(int price, int year, int speed, int x, int y, String portDestination, int numberOfPassengers) {
        super("Ship", price, year, speed, x, y);
        this.portDestination = portDestination;
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numberOfPassengers=" + numberOfPassengers +
                ", portDestination=" + portDestination;
    }

    @Override
    public void swim() {
        System.out.println("SHIP swim");
    }
}
