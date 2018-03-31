package lessons.lesson2.task1;

import lessons.lesson2.task1.serialize.SerializeController;
import lessons.lesson2.task1.serialize.Serializator;
import lessons.lesson2.task2.Polygon;

import java.io.IOException;
import java.io.InvalidObjectException;

public class Main {
    private static String path = "/Users/admin/Desktop/figures.txt";

    public static void main(String[] args) {
        Serializator cz = new Serializator(path);
        SerializeController controller = new SerializeController(cz);


        try {
            controller.executeSerialize();
            controller.executeDeserialize();

            controller.serializePolygons();
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

    }


}
