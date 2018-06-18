package tmp;

import tmp.Cashier;
import tmp.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        List<Cashier> cashiers = new LinkedList<>();
        Queue<Customer> customers = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            cashiers.add(new Cashier("tmp.Cashier " + Name.values()[i], customers));
        }

        for (int i = 0; i < 10; i++) {
            synchronized (customers) {
                customers.add(new Customer("tmp.Customer " + i, 1 + (int) (9 * Math.random())));
                customers.notifyAll();
            }
        }

        synchronized (customers) {
            while (!customers.isEmpty()) {
                try {
                    customers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("All customers have been served");
    }
}
