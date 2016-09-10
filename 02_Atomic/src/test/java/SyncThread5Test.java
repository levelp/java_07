import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comprehensive unit tests for SyncThread5
 */
public class SyncThread5Test extends Assert {

    @Test
    public void testAtomicIntegerIncrement() {
        AtomicInteger x = new AtomicInteger(0);
        x.incrementAndGet();
        assertEquals(1, x.get());

        x.incrementAndGet();
        assertEquals(2, x.get());

        x.incrementAndGet();
        assertEquals(3, x.get());
    }

    @Test
    public void testAtomicIntegerDecrement() {
        AtomicInteger x = new AtomicInteger(10);
        x.decrementAndGet();
        assertEquals(9, x.get());

        x.decrementAndGet();
        assertEquals(8, x.get());
    }

    @Test
    public void testAtomicIntegerMultipleIncrements() {
        AtomicInteger x = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            x.incrementAndGet();
        }
        assertEquals(100, x.get());
    }

    @Test
    public void testAtomicIntegerMultipleDecrements() {
        AtomicInteger x = new AtomicInteger(100);
        for (int i = 0; i < 100; i++) {
            x.decrementAndGet();
        }
        assertEquals(0, x.get());
    }

    @Test
    public void testAtomicIntegerConcurrentIncAndDec() throws InterruptedException {
        AtomicInteger x = new AtomicInteger(0);
        final int X = 1000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x.incrementAndGet();
            }
        });

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x.decrementAndGet();
            }
        });

        incVar.start();
        decVar.start();

        incVar.join();
        decVar.join();

        // With AtomicInteger, result should be exactly 0
        assertEquals(0, x.get());
    }

    @Test
    public void testAtomicIntegerMultipleThreadsIncrement() throws InterruptedException {
        AtomicInteger x = new AtomicInteger(0);
        final int X = 100;
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.incrementAndGet();
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * X, x.get());
    }

    @Test
    public void testAtomicIntegerMultipleThreadsDecrement() throws InterruptedException {
        AtomicInteger x = new AtomicInteger(1000);
        final int X = 100;
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    x.decrementAndGet();
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(0, x.get());
    }

    @Test
    public void testAtomicIntegerGetAndIncrement() {
        AtomicInteger x = new AtomicInteger(5);
        int oldValue = x.getAndIncrement();
        assertEquals(5, oldValue);
        assertEquals(6, x.get());
    }

    @Test
    public void testAtomicIntegerGetAndDecrement() {
        AtomicInteger x = new AtomicInteger(5);
        int oldValue = x.getAndDecrement();
        assertEquals(5, oldValue);
        assertEquals(4, x.get());
    }

    @Test
    public void testAtomicIntegerIncrementAndGet() {
        AtomicInteger x = new AtomicInteger(5);
        int newValue = x.incrementAndGet();
        assertEquals(6, newValue);
        assertEquals(6, x.get());
    }

    @Test
    public void testAtomicIntegerDecrementAndGet() {
        AtomicInteger x = new AtomicInteger(5);
        int newValue = x.decrementAndGet();
        assertEquals(4, newValue);
        assertEquals(4, x.get());
    }

    @Test
    public void testAtomicIntegerSet() {
        AtomicInteger x = new AtomicInteger(0);
        x.set(42);
        assertEquals(42, x.get());
    }

    @Test
    public void testAtomicIntegerInitialValue() {
        AtomicInteger x = new AtomicInteger(100);
        assertEquals(100, x.get());
    }

    @Test
    public void testAtomicIntegerNegativeValues() {
        AtomicInteger x = new AtomicInteger(0);
        x.decrementAndGet();
        assertEquals(-1, x.get());

        x.decrementAndGet();
        assertEquals(-2, x.get());

        x.incrementAndGet();
        assertEquals(-1, x.get());
    }

    @Test
    public void testAtomicIntegerThreadSafety() throws InterruptedException {
        AtomicInteger x = new AtomicInteger(0);
        final int operations = 500;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                x.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                x.incrementAndGet();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                x.decrementAndGet();
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                x.decrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // 2*operations increments - 2*operations decrements = 0
        assertEquals(0, x.get());
    }
}
