import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class ManyThread {

    static final Object lock = new Object();
    static MyClass myClass = new MyClass();
    private static int counter = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger();
    private static int counterLock = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            counter = 0;
            atomicCounter.set(0);
            counterLock = 0;
            myClass = new MyClass();
            List<Thread> threads = new ArrayList<Thread>();
            for (int i = 0; i < 10000; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pause();
                        myClass.inc();
                        pause();
                        counter++;
                        pause();
                        atomicCounter.addAndGet(1);
                        pause();
                        synchronized (lock) {
                            counterLock++;
                        }
                        pause();
                    }

                    private void pause() {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                threads.add(thread);
            }
            for (Thread thread : threads)
                thread.join();
            System.out.println("counter = " + counter);
            System.out.println("atomicCounter = " + atomicCounter.intValue());
            System.out.println("counterLock = " + counterLock);
            System.out.println("myClass.get() = " + myClass.get());
        }
    }

    static class MyClass {
        private int counter;

        synchronized void inc() {
            counter++;
        }

        void inc2() {
            synchronized (this) {
                counter++;
            }
        }

        int get() {
            return counter;
        }
    }
}
