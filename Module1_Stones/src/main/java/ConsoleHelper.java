import model.Stone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static int readInt() {

        int i;

        while (true) {

            try {
                i = Integer.parseInt(reader.readLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static float readFloat() {

        float f;

        while (true) {

            try {
                f = Float.parseFloat(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз. ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }


    public static void printInfo(Stone stone) {

    }

    public static void printInfo(Stone[] stones) {
        for (Stone stone : stones) {
            if (stone != null) {
                System.out.println(stone);
            }
        }
    }
}
