package lessons.lesson2.task1;

public class Main {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] figures = new Shape[30];


        for (int i = 0; i < figures.length; i++) {
            figures[i] = gen.next();
        }


        for (Shape shp : figures) {
            shp.draw();
        }

        Point a = new Point(2, 1);
        Point b = new Point(4, 1);
        Point c = new Point(2, 4);

        Triangle triangle = new Triangle(a, b, c);
        triangle.draw();
    }
}
