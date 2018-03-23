package lessons.lesson4.model;

public abstract class Vehicle implements Comparable <Vehicle> {
    private String type;
    private String abilities;
    private int price;
    private int year;
    private int speed;
    private Coordinat coordinat;

    public Vehicle(String type, int price, int year, int speed, int xCoordinat, int yCoordinat) {
        this.type = type;
        this.price = price;
        this.year = year;
        this.speed = speed;
        this.coordinat = new Coordinat(xCoordinat, yCoordinat);
    }

    public int[] getCoordinat() {
        int x = coordinat.getX();
        int y = coordinat.getY();
        return new int[]{x, y};
    }

    public void setCoordinat(int x, int y) {
        coordinat.checkCoordinat(x, y);
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
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
