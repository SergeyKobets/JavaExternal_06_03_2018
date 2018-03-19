package lessons.lesson4.factories;

import lessons.lesson4.model.*;

import java.util.Random;

public class UnrealVehicleFactory extends AbstractVehicleFactory {
    private int price, year, speed, passengers ,x, y;
    private Random rand = new Random(System.currentTimeMillis());

    @Override
    public Vehicle next() {
        switch (rand.nextInt(5)) {
            default:
            case 0:
                price = nextNum(1000);
                year = nextNum(2018);
                speed = nextNum(2000);
                x = nextNum(100);
                y = nextNum(100);
                return new Batmobile(price, year, speed, x, y);
            case 1:
                price = nextNum(1000);
                year = nextNum(2018);
                speed = nextNum(2000);
                x = nextNum(100);
                y = nextNum(100);
                return new Car(price, year, speed, x, y);
            case 2:
                price = nextNum(1000);
                year = nextNum(2018);
                speed = nextNum(2000);
                x = nextNum(100);
                y = nextNum(100);
                return new CarAmphibian(price, year, speed, x, y);
            case 3:
                price = nextNum(1000);
                year = nextNum(2018);
                speed = nextNum(2000);
                x = nextNum(100);
                y = nextNum(100);
                passengers = nextNum(350);
                return new Plane(price, year, speed, x, y, 8000 ,passengers);
            case 4:
                price = nextNum(1000);
                year = nextNum(2018);
                speed = nextNum(2000);
                x = nextNum(100);
                y = nextNum(100);
                passengers = nextNum(1000);
                return new Ship(price, year, speed, x, y, "Barсelona port",passengers);
        }
    }

    public int nextNum(int bound) {
        return rand.nextInt(bound);
    }
}
