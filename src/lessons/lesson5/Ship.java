package lessons.lesson5;

import lessons.lesson4.ConsoleHelper;
import lessons.lesson4.abilities.ISwim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Ship extends Vehicle implements ISwim {
    private String portDestination;
    private int numberOfPassengers;

    public Ship(int price, int year, int speed, int x, int y, String portDestination, int numberOfPassengers) {
        super("Ship", price, year, speed, x, y, "GH33", "steam");
        this.portDestination = portDestination;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static class LifeBoat {

        public LifeBoat() {
            ConsoleHelper.writeMessage("Boat ready");
        }

        public void swim() {
            ConsoleHelper.writeMessage("Boat is swimming");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numberOfPassengers=" + numberOfPassengers +
                ", portDestination: " + portDestination;
    }

    @Override
    public String swim() {
        return getType() + " плывет со скоростью: " + getSpeed();
    }

}
