package vol;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация volatile
 */
public class Volatile {
    static int diffCounter = 0;

    @Test
    public void testVolatile() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    TestVolatile.one();
                    TestVolatile.two();
                }
            });
            t1.start();
            threads.add(t1);
        }
        for (Thread t : threads)
            t.join();
        System.out.println("diffCounter = " + diffCounter);
    }

    static class TestVolatile {
        static volatile int i = 0, j = 0;

        static void one() {
            i++;
            j++;
        }

        static void two() {
            int lj = j, li = i;
            if (lj - li > 1) {
                diffCounter++;
            }
        }
    }
}
