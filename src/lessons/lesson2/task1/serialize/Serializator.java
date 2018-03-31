package lessons.lesson2.task1.serialize;

import lessons.lesson2.task1.Line;
import lessons.lesson2.task1.Point;
import lessons.lesson2.task1.Shape;
import lessons.lesson2.task1.Triangle;
import lessons.lesson2.task2.Polygon;

import java.io.*;

public class Serializator {
    private String fileName;

    public Serializator(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void executeSerialize(Shape[] shapes) {

        try (FileOutputStream fileOutput = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput)) {

            for (Shape shape : shapes) {
                objectOutputStream.writeObject(shape);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: " + e);
        } catch (NotSerializableException e) {
            System.err.println("Класс не поддерживает сериализацию: " + e);
        } catch (IOException e) {
            System.err.println(e);
        }


    }


    public Triangle executeDeserialize(String filePath) throws InvalidObjectException {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            Point point1 = (Point) objectInputStream.readObject();
            Point point2 = (Point) objectInputStream.readObject();
            Point point3 = (Point) objectInputStream.readObject();

            Line line1 = (Line) objectInputStream.readObject();
            Line line2 = (Line) objectInputStream.readObject();
            Line line3 = (Line) objectInputStream.readObject();

            return (Triangle) objectInputStream.readObject();

        } catch (ClassNotFoundException e) {
            System.err.println("Класс не существует: " + e);
        } catch (FileNotFoundException e) {
            System.err.println("Файл для десериализации не существует: " + e);
        } catch (InvalidClassException ioe) {
            System.err.println("Несовпадение версий классов: " + ioe);
        } catch (IOException ioe) {
            System.err.println("Общая I/O ошибка: " + ioe);
        }

        throw new InvalidObjectException("объект не восстановлен");
    }


    //----------task3---------

    public void serializePolygon(Polygon polygon, String filePath) {
        try (FileOutputStream fileOutput = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput)) {

            objectOutputStream.writeObject(polygon);

        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: " + e);
        } catch (NotSerializableException e) {
            System.err.println("Класс не поддерживает сериализацию: " + e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public Polygon deserializePolygon(String filePath) throws InvalidObjectException {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            return (Polygon) objectInputStream.readObject();

        } catch (ClassNotFoundException e) {
            System.err.println("Класс не существует: " + e);
        } catch (FileNotFoundException e) {
            System.err.println("Файл для десериализации не существует: " + e);
        } catch (InvalidClassException ioe) {
            System.err.println("Несовпадение версий классов: " + ioe);
        } catch (IOException ioe) {
            System.err.println("Общая I/O ошибка: " + ioe);
        }
        throw new InvalidObjectException("объект не восстановлен");
    }


}
