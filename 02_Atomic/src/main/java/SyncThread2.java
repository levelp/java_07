/**
 * Синхронизация потоков
 */
public class SyncThread2 {
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Object myObject = 1;
            final int X = 10000000;
            Thread incVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    synchronized (myObject) { // try
                        x++;
                    } // release
                    // 1. Загрузить из памяти
                    // 2. Поменять значение
                    // 3. Записать в память
                }
            });
            incVar.start();

            Thread decVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    synchronized (myObject) {
                        x--;
                    }
                }
            });
            decVar.start();
            // Подождём оба потока
            incVar.join();
            decVar.join();
            // Какое же значение переменной?
            System.out.println("x = " + x);
        }
    }
}
