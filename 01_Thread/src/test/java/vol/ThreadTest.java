package vol;

public class ThreadTest {
    volatile boolean running = true;

    public static void main(String[] args) {
        while (true)
            new ThreadTest().test();
    }

    public void test() {
        new Thread(() -> {
            int counter = 0;
            while (running) {
                counter++;
            }
            System.out.println("Thread 1 finished. Counted up to " + counter);
        }).start();
        new Thread(() -> {
            // Sleep for a bit so that thread 1 has a chance to start
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Thread 2 finishing");
            running = false;
        }).start();
    }
}