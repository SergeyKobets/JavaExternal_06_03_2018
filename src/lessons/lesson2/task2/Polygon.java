package lessons.lesson2.task2;

import lessons.lesson2.task1.RandomShapeGenerator;
import lessons.lesson2.task1.Shape;

public class Polygon {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();
    Shape[] figures;

    public Polygon(int len) {
        this.figures = new Shape[len];
    }


    public void drawShapes() {
        for (int i = 0; i < figures.length; i++) {
            figures[i] = gen.next();
        }

        for (Shape shp : figures) {
            shp.draw();
        }
    }
}
