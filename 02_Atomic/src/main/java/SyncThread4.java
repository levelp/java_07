/**
 * Синхронизация потоков
 */
public class SyncThread4 {
    static final Object xx = new Object();
    private static Value x = new Value();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            final int X = 10000000;
            Thread incVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.inc();
                    // 1. Загрузить из памяти
                    // 2. Поменять значение
                    // 3. Записать в память
                }
            });
            incVar.start();

            Thread decVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.dec();
                }
            });
            decVar.start();
            // Подождём оба потока
            incVar.join();
            decVar.join();
            // Какое же значение переменной?
            System.out.println("x = " + x.x);
        }
    }

    static class Value {
        public int x = 0;

        synchronized void inc() {
            x++;
        }

        void dec() {
            synchronized (xx) {
                x--;
            }
        }
    }
}
