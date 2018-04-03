import exception.WrongOperationException;
import factory.RandomStoneFactory;
import model.Gemstone;
import model.Stone;

import java.io.*;
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

    public void execute(Operation operation) throws WrongOperationException {
        switch (operation) {
            case SELECT:
                selectNecklaceStones(stones);
                break;
            case COUNT:
                calculateWeight(stones);
                calculateCost(stones);
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

    public Stone[] selectNecklaceStones(Stone[] stones) {
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

        Stone[] resultListStones = selectNotNullStones(stoneForNeck);

        ConsoleHelper.printInfo(stoneForNeck);
        return resultListStones;
    }

    public double calculateWeight(Stone[] stones) {
        double weight = 0;

        for (Stone stone : stones) {
            if (stone != null) {
                weight += stone.getWeight();
            }
        }

        ConsoleHelper.writeMessage("Weight of all necklace stones is: " + weight);
        return weight;
    }

    public double calculateCost(Stone[] stones) {
        double cost = 0;

        for (Stone stone : stones) {
            if (stone != null) {
                cost += stone.getCost();
            }
        }

        ConsoleHelper.writeMessage("The cost of all necklace stones is: " + cost);
        return cost;
    }


    public Stone[] sortStones(Stone[] stones) {
        Stone[] sortedStones = selectNotNullStones(stones);

        Arrays.sort(sortedStones);

        ConsoleHelper.printInfo(sortedStones);
        return sortedStones;
    }


    public Stone[] findStonesForTransparency(Stone[] stones) {
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
        return resultStones;
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

    private void executeSerialize(Stone[] stones) {
        try (FileOutputStream fileOutput = new FileOutputStream("/Users/admin/Desktop/stones.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput)) {

            objectOutputStream.writeObject(stones);

        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: " + e);
        } catch (NotSerializableException e) {
            System.err.println("Класс не поддерживает сериализацию: " + e);
        } catch (IOException e) {
            System.err.println(e);
        }


    }

}
