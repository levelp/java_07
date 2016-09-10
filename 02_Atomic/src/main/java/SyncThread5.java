import java.util.concurrent.atomic.AtomicInteger;

/**
 * Синхронизация потоков
 */
public class SyncThread5 {
    private static AtomicInteger x = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            final int X = 10000000;
            Thread incVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.incrementAndGet();
                    // 1. Загрузить из памяти
                    // 2. Поменять значение
                    // 3. Записать в память
                }
            });
            incVar.start();

            Thread decVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.decrementAndGet();
                }
            });
            decVar.start();
            // Подождём оба потока
            incVar.join();
            decVar.join();
            // Какое же значение переменной?
            System.out.println("x = " + x.get());
        }
    }
}
