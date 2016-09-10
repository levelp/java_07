import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprehensive unit tests for SharedClass
 */
public class SharedClassTest extends Assert {

    @Before
    public void setUp() {
        Main.globalVar = 0;
    }

    @Test
    public void testIncrementSingleThread() {
        SharedClass sharedClass = new SharedClass();
        sharedClass.increment();
        assertEquals(1, Main.globalVar);

        sharedClass.increment();
        assertEquals(2, Main.globalVar);

        sharedClass.increment();
        assertEquals(3, Main.globalVar);
    }

    @Test
    public void testIncrementMultipleTimes() {
        SharedClass sharedClass = new SharedClass();
        for (int i = 0; i < 100; i++) {
            sharedClass.increment();
        }
        assertEquals(100, Main.globalVar);
    }

    @Test
    public void testIncrementMultipleThreads() throws InterruptedException {
        SharedClass sharedClass = new SharedClass();
        int threadCount = 10;
        int incrementsPerThread = 1000;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    sharedClass.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * incrementsPerThread, Main.globalVar);
    }

    @Test
    public void testIncrementFromZero() {
        Main.globalVar = 0;
        SharedClass sharedClass = new SharedClass();
        sharedClass.increment();
        assertEquals(1, Main.globalVar);
    }

    @Test
    public void testIncrementFromNonZero() {
        Main.globalVar = 42;
        SharedClass sharedClass = new SharedClass();
        sharedClass.increment();
        assertEquals(43, Main.globalVar);
    }

    @Test
    public void testIncrementNegativeValue() {
        Main.globalVar = -10;
        SharedClass sharedClass = new SharedClass();
        sharedClass.increment();
        assertEquals(-9, Main.globalVar);
    }

    @Test
    public void testSynchronizationWithMultipleInstances() throws InterruptedException {
        SharedClass sharedClass1 = new SharedClass();
        SharedClass sharedClass2 = new SharedClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sharedClass1.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sharedClass2.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(1000, Main.globalVar);
    }
}
