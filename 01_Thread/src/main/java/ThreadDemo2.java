/**
 * Демонстрация работы с потоками
 */
public class ThreadDemo2 {
    static MyClass myClass = new MyClass();

    public static void main(String[] args) {

        final Object lock = new Object();
        // Первый поток
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 98; i++) {
                    synchronized (lock) {
                        myClass.inc(1000);
                        System.out.println("1) i = " + myClass.getCounter());
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Второй поток
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 99; i++) {

                    synchronized (lock) {
                        myClass.inc(1);
                        System.out.println("2) i = " + myClass.getCounter());
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();
        thread1.start();

        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("myClass.counter = " + myClass.getCounter());
    }

    static class MyClass {
        int counter = 0;

        public synchronized void inc(int value) {
            counter += value;
        }

        public synchronized int getCounter() {
            return counter;
        }
    }
}
