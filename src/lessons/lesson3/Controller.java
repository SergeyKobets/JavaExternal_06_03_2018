//package lessons.lesson3;
//
//import lessons.lesson3.model.Car;
//import lessons.lesson3.model.Ship;
//import lessons.lesson3.model.Vehicle;
//
//public class Serializator {
//    public void execute(Vehicle[] vehicles) {
//        int req = ConsoleHelper.askOperation();
//        switch (req) {
//            case 1:
//                findHighPrice(vehicles);
//                break;
//            case 2:
//                findByYear(vehicles);
//                break;
//            case 3:
//                findCar(vehicles);
//                break;
//            case 4:
//                findShip(vehicles);
//                break;
//            default:
//                System.out.println("default");
//        }
//
//    }
//
//
//    private void findHighPrice(Vehicle[] vehicles) {
//        Vehicle tmp = vehicles[0];
//        for (int i = 1; i < vehicles.length; ++i) {
//            if (vehicles[i].getPrice() > tmp.getPrice()) {
//                tmp = vehicles[i];
//            }
//        }
//
//        ConsoleHelper.printVehicleInfo(tmp);
//    }
//
//    private void findByYear(Vehicle[] vehicles) {
//        int year, speed, price;
//        Vehicle tmp = null;
//        int lowestCost = vehicles[0].getPrice();
//        for (int i = 1; i < vehicles.length; ++i) {
//            if (vehicles[i].getPrice() < lowestCost) {
//                lowestCost = vehicles[i].getPrice();
//            }
//        }
//
//        for (int i = 0; i < vehicles.length; ++i) {
//            year = vehicles[i].getYear();
//            speed = vehicles[i].getSpeed();
//            price = vehicles[i].getPrice();
//
//            if ((year > 2000 && year < 2005) && (speed > 150) && (price == lowestCost)) {
//                tmp = vehicles[i];
//            }
//        }
//        ConsoleHelper.printVehicleInfo(tmp);
//    }
//
//    private void findCar(Vehicle[] vehicles) {
//        int year;
//        String currentType;
//        Car[] cars = new Car[vehicles.length - 1];
//        String carType = "com.company.lessons.lesson3.model.Car";
//
//
//        for (int i = 0, j = 0; i < vehicles.length; ++i) {
//            year = vehicles[i].getYear();
//            currentType = vehicles[i].getClass().getName();
//            if ((carType.equals(currentType) && (year > 2013))) {
//                cars[j] = (Car) vehicles[i];
//                ++j;
//            }
//        }
//
//        ConsoleHelper.printVehicleInfo(cars);
//    }
//
//    private void findShip(Vehicle[] vehicles) {
//        int year;
//        String currentType;
//        Ship[] ships = new Ship[vehicles.length - 1];
//        String shipType = "com.company.lessons.lesson3.model.Ship";
//
//        int len = 0;
//        for (int i = 0, j = 0; i < vehicles.length; ++i) {
//            year = vehicles[i].getYear();
//            currentType = vehicles[i].getClass().getName();
//            if ((shipType.equals(currentType)) && (year > 2013)) {
//                ships[j] = (Ship) vehicles[i];
//                ++len;
//                ++j;
//            }
//        }
//
//
//        sortArray(ships, len);
//        ConsoleHelper.printVehicleInfo(ships);
//    }
//
//
//    private void sortArray(Vehicle[] vehicles, int len) {
//        for (int j = 0; j < len; j++)
//            for (int k = 0; k < len - 1; k++) {
//                if ((vehicles[k].getPrice() < vehicles[k + 1].getPrice())) {
//                    Vehicle temp = vehicles[k];
//                    vehicles[k] = vehicles[k + 1];
//                    vehicles[k + 1] = temp;
//                }
//            }
//    }
//}
