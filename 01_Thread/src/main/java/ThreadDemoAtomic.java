import java.util.concurrent.atomic.AtomicInteger;

/**
 * Демонстрация работы с потоками
 */
public class ThreadDemoAtomic {
    static int counter = 0;
    static MyClass myClass = new MyClass();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    myClass.inc();
                    System.out.println("1) i = " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    myClass.inc();
                    System.out.println("2) i = " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("counter = " + counter);
        System.out.println("myClass.counter = " + myClass.counter);
    }

    static class MyClass {
        AtomicInteger counter = new AtomicInteger(0);

        public void inc() {
            counter.getAndIncrement();
        }
    }
}
