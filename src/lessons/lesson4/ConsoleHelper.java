package lessons.lesson4;

import lessons.lesson4.abilities.IFly;
import lessons.lesson4.abilities.IMove;
import lessons.lesson4.abilities.ISwim;
import lessons.lesson4.model.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }


    public static int askOperation() {
        writeMessage("Допустимые операции: \n 1 - Vehicle с наибольшей ценой" +
                " \n 2 - год выпуска 2000-2005 с  скоростью выше 150 км\\ч, и наименьшей ценой" +
                "\n 3 - Из Масива Vehicle получить масив Car не старше 5 лет" +
                "\n 4 - Из Масива Vehicle получить масив Ship не старше 5 лет, с  отсортированой ценой по убыванию");
        return readInt();
    }

    public static int readInt() {

        int i;

        while (true) {

            try {
                i = Integer.parseInt(reader.readLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз. ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }


    public static void printVehicleInfo(Vehicle vehicle) {
        System.out.println(vehicle);
    }

    public static void printVehicleInfo(Vehicle[] vehicles) {
        for (Vehicle item : vehicles) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    public static void printCanSwim(ISwim[] array) {
        writeMessage("CAN SWIM:");
        for (ISwim item : array) {
            if (item != null) {
                writeMessage(item.swim());

            }
        }
    }

    public static void printCanFly(IFly[] array) {
        writeMessage("\nCAN FLY:");
        for (IFly item : array) {
            if (item != null) {
                writeMessage(item.fly());
            }
        }
    }

    public static void printCanMove(IMove[] array) {
        writeMessage("\nCAN MOVE:");
        for (IMove item : array) {
            if (item != null) {
                writeMessage(item.move());
            }
        }
    }
}
