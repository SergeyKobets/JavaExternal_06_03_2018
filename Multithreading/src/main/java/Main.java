import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) {
        List<Character> cashiers = new ArrayList<>();
        cashiers.add(new Cashier());
        cashiers.add(new Cashier());

        start(cashiers);
    }

    private static boolean isCashiersReady = false;

    private static void start(List<Character> cashiers) {
        final Phaser phaser = new Phaser(cashiers.size());

        for (final Character cashier : cashiers) {
            final String member = cashier.toString();

            new Thread(() -> {
                System.out.println(member + " готовится к открытию ресторана");
                phaser.arriveAndAwaitAdvance();
                if (!isCashiersReady) {
                    isCashiersReady = true;
                    System.out.println("Ресторан готов обслуживать клиентов");
                }
                cashier.run();
            }).start();
        }
        phaser.arriveAndDeregister();
    }
}
