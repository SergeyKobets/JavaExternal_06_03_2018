import exception.WrongOperationException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                controller.execute(operation);
            } catch (WrongOperationException e) {
                ConsoleHelper.writeMessage("Введена неизвестная операция");
            }

        } while (operation != Operation.EXIT);
    }


    public static Operation askOperation() {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - отобрать камни для ожерелья", Operation.SELECT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - сосчитать общий вес (в каратах) и стоимость", Operation.COUNT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - сортировать камни ожерелья на основе ценности.", Operation.SORT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - найти камни в ожерелье," +
                " соответствующие заданному диапазону параметров прозрачности.", Operation.FIND.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть имеющиеся камни.", Operation.SHOW_ALL.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - посмотреть имеющиеся камни отобранные для ожерелья.", Operation.SHOW.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход и запись в файл", Operation.EXIT.ordinal()));

        int tmp = ConsoleHelper.readInt();
        if (tmp > Operation.values().length) return Operation.OTHER;
        return Operation.values()[tmp];
    }
}
