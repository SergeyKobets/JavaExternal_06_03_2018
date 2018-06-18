import java.util.concurrent.ThreadLocalRandom;

public abstract class Character implements Runnable {

    protected abstract int getId();

    public void run() {
        System.out.println(toString() + " принимает заказ");
        doSomething();
        System.out.println(toString() + " закончил обслуживание клиента");
    }

    private void doSomething() {
        try {
            System.out.println(toString() + " Обслуживает клиентов");
            Thread.currentThread().sleep(ThreadLocalRandom.current().nextInt(500, 5000));
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + getId();
    }
}
