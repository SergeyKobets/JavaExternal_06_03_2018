package lessons.lesson2.task1;

import java.util.Random;

public class RandomShapeGenerator {
    private static Random rand = new Random(System.currentTimeMillis());

    public Shape next() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Point(nextNumber(), nextNumber());
            case 1:
                return new Line(new Point(nextNumber(), nextNumber()), new Point(nextNumber(), nextNumber()));
            case 2:
                Point p1 = new Point(nextNumber(), nextNumber());
                Point p2 = new Point(nextNumber(), nextNumber());
                Point p3 = new Point(nextNumber(), nextNumber());
                return new Triangle(p1, p2, p3);
        }
    }

    private int nextNumber() {
        return (int) (Math.random() * 10);
    }
}

