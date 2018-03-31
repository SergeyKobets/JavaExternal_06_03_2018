package lessons.lesson2.task2;

import lessons.lesson2.task1.*;

import java.io.Serializable;
import java.util.Arrays;

public class Polygon implements Serializable {
    private static int ID = 0;
    private static RandomShapeGenerator gen = new RandomShapeGenerator();
    private Shape[] figures;

    public Polygon(int len) {
        ++ID;
        this.figures = new Shape[len];
        initShapes();
    }


    public Shape[] getFigures() {
        return figures;
    }

    public void setFigures(Shape[] figures) {
        this.figures = figures;
    }

    private void initShapes() {
        for (int i = 0; i < figures.length; i++) {
            figures[i] = gen.next();
        }
    }

    public void drawShapes() {
        for (Shape shp : figures) {
            shp.draw();
        }
    }

    @Override
    public String toString() {
        return "Polygon " + ID +
                " {" +
                "figures=" + Arrays.toString(figures) +
                '}';
    }
}
