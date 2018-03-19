package lessons.lesson4;

import lessons.lesson4.factories.AbstractVehicleFactory;

import lessons.lesson4.factories.UnrealVehicleFactory;
import lessons.lesson4.model.*;

public class Main {
    private static AbstractVehicleFactory vehicleFactory = new UnrealVehicleFactory();

    public static void main(String[] args) {
        Controller controller = new Controller();
        Vehicle[] vehicles = new Vehicle[12];

        for (int i = 0; i < vehicles.length; ++i) {
            vehicles[i] = vehicleFactory.next();
        }
//
//        Vehicle[] listVehicle = new Vehicle[8];
//        listVehicle[0] = new Plane(5000, 2006, 800, 10, 29, 8000, 350);
//        listVehicle[1] = new Car(100, 2017, 300, 56, 90);
//        listVehicle[2] = new Car(303, 2006, 300, 56, 90);
//        listVehicle[3] = new Car(530, 2014, 300, 56, 90);
//        listVehicle[4] = new Ship(100, 2004, 160, 14, 76, "Barсelona port", 100);
//        listVehicle[5] = new Ship(130, 2014, 160, 14, 76, "Barсelona port", 160);
//        listVehicle[6] = new Ship(140, 2015, 160, 14, 76, "Barсelona port", 170);
//        listVehicle[7] = new Ship(150, 2016, 160, 14, 76, "Barсelona port", 200);
//
        ConsoleHelper.printVehicleInfo(vehicles);
        //controller.executeSelection(listVehicle);

//        listVehicle[0] = new Batmobile(5000, 2006, 500, 10, 29);
//        listVehicle[1] = new Car(100, 2014, 170, 56, 90);
//        listVehicle[2] = new CarAmphibian(500, 2016, 300, 56, 90);
//        listVehicle[3] = new Plane(5000, 2006, 800, 10, 29, 8000, 350);
//        listVehicle[4] = new Ship(130, 2014, 160, 14, 76, "Barсelona port", 160);
//
//        controller.canSwim(listVehicle);
//        controller.canFly(listVehicle);
//        controller.canMove(listVehicle);
    }
}
