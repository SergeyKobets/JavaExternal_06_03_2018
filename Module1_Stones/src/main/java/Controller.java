import exception.WrongOperationException;
import factory.RandomStoneFactory;
import model.Gemstone;
import model.Stone;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Controller {
    private static Stone[] stones;
    private static RandomStoneFactory r = new RandomStoneFactory();

    static {
        stones = new Stone[10];

        for (int i = 0; i < stones.length; ++i) {
            stones[i] = r.next();
        }
    }

    public void execute(Operation operation) throws WrongOperationException, IOException {
        switch (operation) {
            case SELECT:
                selectNecklaceStones(stones);
                break;
            case COUNT:
                countWeihtAndCost(stones);
                break;
            case SORT:
                sortStones(stones);
                break;
            case FIND:
                findStonesForTransparency(stones);
                break;
            case SHOW_ALL:
                ConsoleHelper.printInfo(stones);
                break;
            case SHOW:
                ConsoleHelper.printInfo(stones);
                break;
            case EXIT:
                executeSerialize(stones);
                return;
            default:
                throw new WrongOperationException();
        }
    }

    public void selectNecklaceStones(Stone[] stones) {
        String currentType;
        String type = Gemstone.class.getName();
        Stone[] stoneForNeck = new Stone[stones.length];


        for (int i = 0, j = 0; i < stones.length; i++) {
            currentType = stones[i].getClass().getName();

            if (type.equals(currentType)) {
                stoneForNeck[j] = stones[i];
                j++;
            } else {
                stones[i] = null;
            }
        }

        ConsoleHelper.printInfo(stoneForNeck);
    }

    public void countWeihtAndCost(Stone[] stones) {
        int allWeight = 0;
        int allCost = 0;

        for (Stone stone : stones) {
            if (stone != null) {
                allCost += stone.getCost();
                allWeight += stone.getWeight();
            }
        }

        ConsoleHelper.writeMessage("Weight of all necklace stones is: " + allWeight);
        ConsoleHelper.writeMessage("The cost of all necklace stones is: " + allCost);
    }


    public void sortStones(Stone[] stones) {
        Stone[] stonesForSort = selectNotNullStones(stones);

        Arrays.sort(stonesForSort);
        ConsoleHelper.printInfo(stonesForSort);
    }


    public void findStonesForTransparency(Stone[] stones) {
        float minTransparency;
        float maxTransparency;

        ConsoleHelper.writeMessage("Enter minimum of transparency");
        minTransparency = ConsoleHelper.readFloat();

        ConsoleHelper.writeMessage("Enter maximum of transparency");
        maxTransparency = ConsoleHelper.readFloat();

        Stone[] currentStones = selectNotNullStones(stones);
        Stone[] resultStones = new Stone[currentStones.length];
        for (int i = 0, j = 0; i < currentStones.length; i++) {
            float transparency = currentStones[i].getTransparency();
            if (transparency > minTransparency && transparency < maxTransparency) {
                resultStones[j] = currentStones[i];
                j++;
            }
        }


        ConsoleHelper.printInfo(resultStones);
    }


    public Stone[] selectNotNullStones(Stone[] stones) {
        int count = 0;
        for (Stone stone : stones) {
            if (stone != null) {
                count++;
            }
        }

        Stone[] result = new Stone[count];
        for (int i = 0, j = 0; i < stones.length; i++) {
            if (stones[i] != null) {
                result[j] = stones[i];
                j++;
            }
        }
        return result;
    }

    private void executeSerialize(Stone[] stones) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream("/Users/admin/Desktop/stones.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput);

        objectOutputStream.writeObject(stones);

        objectOutputStream.close();
    }

}
