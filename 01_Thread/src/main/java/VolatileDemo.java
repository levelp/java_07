/**
 * Работа с volatile
 */
public class VolatileDemo {
    //private static AtomicInteger x = new AtomicInteger(0);
    volatile static int x = 0;
    //volatile static int x2 = 0;
    static int changes = 0;

    public static void main(String[] args) {
        // Запускаем 2 потока
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    /**
     * Следит за изменениями
     */
    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = x; // x.intValue();
            while (local_value < 5000) {
                if (local_value != x) { // x.intValue()
                    //System.out.println("x changed = " + x);
                    local_value = x; //.intValue();
                    changes++;
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
            System.out.println("changes = " + changes);
        }
    }

    /**
     * Меняет переменную
     */
    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int local_value = x;
            while (x < 5000) {
                System.out.println("Incrementing x to " + (x + 1)); //  (local_value + 1)
                x = ++local_value;
                //x.incrementAndGet();
                try {
                    Thread.sleep(1);
                    // Прерывание своего потока
                    //Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}