package lessons.lesson4;

import lessons.lesson4.factories.AbstractVehicleFactory;

import lessons.lesson4.factories.VehicleFactory;
import lessons.lesson4.model.*;

import java.util.Arrays;

public class Main {
    private static AbstractVehicleFactory vehicleFactory = new VehicleFactory();

    public static void main(String[] args) {
        Controller controller = new Controller();
        Vehicle[] vehicles = new Vehicle[12];

        for (int i = 0; i < vehicles.length; ++i) {
            vehicles[i] = vehicleFactory.next();
        }

      ConsoleHelper.printVehicleInfo(vehicles);
////
//        controller.executeSelection(vehicles);

        controller.canSwim(vehicles);
        controller.canFly(vehicles);
        controller.canMove(vehicles);
    }
}
