package lessons.lesson4;


import lessons.lesson4.model.*;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        Vehicle[] listVehicle = new Vehicle[8];
        listVehicle[0] = new Plane(5000, 2006, 800, 10, 29, 8000, 350);
        listVehicle[1] = new Car(100, 2017, 300, 56, 90);
        listVehicle[2] = new Car(303, 2006, 300, 56, 90);
        listVehicle[3] = new Car(530, 2014, 300, 56, 90);
        listVehicle[4] = new Ship(100, 2004, 160, 14, 76, "Barсelona port", 100);
        listVehicle[5] = new Ship(130, 2014, 160, 14, 76, "Barсelona port", 160);
        listVehicle[6] = new Ship(140, 2015, 160, 14, 76, "Barсelona port", 170);
        listVehicle[7] = new Ship(150, 2016, 160, 14, 76, "Barсelona port", 200);

        controller.execute(listVehicle);
    }

}
