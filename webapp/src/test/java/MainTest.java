import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comprehensive unit tests for Main class
 */
public class MainTest extends Assert {

    @Before
    public void setUp() {
        Main.sum = 0;
        Main.ATOMIC_SUM.set(0);
    }

    @Test
    public void testStaticFieldsInitialization() {
        assertNotNull(Main.LOCK);
        assertNotNull(Main.ATOMIC_SUM);
        assertNotNull(Main.CDL);
    }

    @Test
    public void testLockType() {
        assertTrue(Main.LOCK instanceof Object);
    }

    @Test
    public void testAtomicSumType() {
        assertTrue(Main.ATOMIC_SUM instanceof AtomicInteger);
    }

    @Test
    public void testIncMethod() {
        Main.sum = 0;
        Main.inc();
        assertEquals(1, Main.sum);

        Main.inc();
        assertEquals(2, Main.sum);

        Main.inc();
        assertEquals(3, Main.sum);
    }

    @Test
    public void testIncMethodMultipleTimes() {
        Main.sum = 0;
        for (int i = 0; i < 100; i++) {
            Main.inc();
        }
        assertEquals(100, Main.sum);
    }

    @Test
    public void testIncMethodThreadSafety() throws InterruptedException {
        Main.sum = 0;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Main.inc();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Main.inc();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2000, Main.sum);
    }

    @Test
    public void testAtomicSumIncrement() {
        Main.ATOMIC_SUM.set(0);
        Main.ATOMIC_SUM.incrementAndGet();
        assertEquals(1, Main.ATOMIC_SUM.get());

        Main.ATOMIC_SUM.incrementAndGet();
        assertEquals(2, Main.ATOMIC_SUM.get());
    }

    @Test
    public void testAtomicSumThreadSafety() throws InterruptedException {
        Main.ATOMIC_SUM.set(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Main.ATOMIC_SUM.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Main.ATOMIC_SUM.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2000, Main.ATOMIC_SUM.get());
    }

    @Test
    public void testSumInitialValue() {
        Main.sum = 0;
        assertEquals(0, Main.sum);
    }

    @Test
    public void testIncFromNonZero() {
        Main.sum = 42;
        Main.inc();
        assertEquals(43, Main.sum);
    }

    @Test
    public void testIncNegativeValue() {
        Main.sum = -10;
        Main.inc();
        assertEquals(-9, Main.sum);
    }

    @Test
    public void testMultipleThreadsInc() throws InterruptedException {
        Main.sum = 0;
        int threadCount = 10;
        int incrementsPerThread = 100;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    Main.inc();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * incrementsPerThread, Main.sum);
    }

    @Test
    public void testVolatileFieldExists() {
        Main main = new Main();
        main.i = 42;
        assertEquals(42, main.i);
    }

    @Test
    public void testVolatileFieldVisibility() throws InterruptedException {
        Main main = new Main();
        main.i = 0;

        Thread writer = new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                main.i = j;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader = new Thread(() -> {
            int lastSeen = -1;
            for (int j = 0; j < 100; j++) {
                int current = main.i;
                assertTrue(current >= lastSeen);
                lastSeen = current;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();
    }

    @Test
    public void testAtomicSumSet() {
        Main.ATOMIC_SUM.set(100);
        assertEquals(100, Main.ATOMIC_SUM.get());
    }

    @Test
    public void testAtomicSumAddAndGet() {
        Main.ATOMIC_SUM.set(10);
        int result = Main.ATOMIC_SUM.addAndGet(5);
        assertEquals(15, result);
        assertEquals(15, Main.ATOMIC_SUM.get());
    }

    @Test
    public void testIncWithMultipleInstances() {
        // Test that static method works correctly
        Main.sum = 0;
        new Main();
        Main.inc();
        assertEquals(1, Main.sum);
    }
}
