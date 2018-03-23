package lessons.lesson4.model;

import lessons.lesson4.ConsoleHelper;

public abstract class Vehicle implements Comparable <Vehicle> {
    private String type;
    private int price;
    private int year;
    private int speed;
    private Coordinat coordinat;
    private Engine engine;

    public Vehicle(String type, int price, int year, int speed, int xCoordinat, int yCoordinat, String engineModel, String engineType) {
        this.type = type;
        this.price = price;
        this.year = year;
        this.speed = speed;
        this.coordinat = new Coordinat(xCoordinat, yCoordinat);
        this.engine = new Engine(engineModel, engineType);
    }

    public int[] getCoordinat() {
        int x = coordinat.getX();
        int y = coordinat.getY();
        return new int[]{x, y};
    }

    public void setCoordinat(int x, int y) {
        coordinat.checkCoordinat(x, y);
    }


    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Vehicle o) {
            return this.speed - o.speed;
    }

    public class Engine {
        private String model;
        private String  type;

        public Engine(String model, String type) {
            this.model = model;
            this.type = type;
        }

        public void launch() {
            ConsoleHelper.writeMessage("Launch engine");
        }

        @Override
        public String toString() {
            return "Engine{" +
                    "model='" + model + '\'' +
                    ", type=" + type +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Type vehicle: " + type +
                ", price=" + price +
                ", speed=" + speed +
                ", year=" + year +
                ", coordinat x=" + coordinat.getX() +
                ", coordinat y=" + coordinat.getY();
    }
}
