package lessons.lesson3.model;

public class Coordinat {
    private int x;
    private int y;

    public Coordinat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void checkCoordinat(int x, int y) {
        if (this.x != x && this.y != y) {
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinat coordinat = (Coordinat) o;

        if (x != coordinat.x) return false;
        return y == coordinat.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
