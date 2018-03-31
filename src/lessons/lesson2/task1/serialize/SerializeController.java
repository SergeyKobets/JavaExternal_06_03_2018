package lessons.lesson2.task1.serialize;

import lessons.lesson2.task1.Line;
import lessons.lesson2.task1.Point;
import lessons.lesson2.task1.Shape;
import lessons.lesson2.task1.Triangle;
import lessons.lesson2.task2.Polygon;

import java.io.IOException;
import java.io.InvalidObjectException;

public class SerializeController {
    private Serializator cz;

    public SerializeController(Serializator cz) {
        this.cz = cz;
    }

    public void executeSerialize() {
        Point point1 = new Point(1, 7);
        Point point2 = new Point(3, 1);
        Point point3 = new Point(8, 12);

        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point1, point3);

        Triangle triangle = new Triangle(point1, point2, point3);

        Shape[] shapes = {point1, point2, point3, line1, line2, line3, triangle};

        cz.executeSerialize(shapes);
    }

    public void executeDeserialize() throws InvalidObjectException {
        Triangle triangle = cz.executeDeserialize(cz.getFileName());
        System.out.println(triangle);
    }


    public void serializePolygons() throws InvalidObjectException {
        //Polygon1
        Polygon polygon = new Polygon(3);
        cz.serializePolygon(polygon, "/Users/admin/Desktop/polygon1.txt");
        Polygon shapesFromPolygon1 = cz.deserializePolygon("/Users/admin/Desktop/polygon1.txt");
        printInfo(shapesFromPolygon1);

        //Polygon2
        Polygon polygon1 = new Polygon(4);
        cz.serializePolygon(polygon1, "/Users/admin/Desktop/polygon2.txt");
        Polygon shapesFromPolygon2 = cz.deserializePolygon("/Users/admin/Desktop/polygon2.txt");
        printInfo(shapesFromPolygon2);

        //Polygon3
        Polygon polygon2 = new Polygon(1);
        cz.serializePolygon(polygon2, "/Users/admin/Desktop/polygon3.txt");
        Polygon shapesFromPolygon3 = cz.deserializePolygon("/Users/admin/Desktop/polygon3.txt");
        printInfo(shapesFromPolygon3);
    }


    public void printInfo(Polygon polygon) {
        System.out.println(polygon);
    }
}
