/**
 * Синхронизация потоков
 */
public class SyncThread {
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            final int X = 100000000;
            Thread incVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x++;
                    // 1. Загрузить из памяти
                    // 2. Поменять значение
                    // 3. Записать в память
                }
            });
            incVar.start();

            Thread decVar = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x--;
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
