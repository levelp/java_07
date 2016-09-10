import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SyncThread3 and its inner Value class
 */
public class SyncThread3Test extends Assert {

    @Test
    public void testValueCreation() {
        SyncThread3.Value value = new SyncThread3.Value();
        assertNotNull(value);
        assertEquals(0, value.x);
    }

    @Test
    public void testValueInitialValue() {
        SyncThread3.Value value = new SyncThread3.Value();
        assertEquals(0, value.x);
    }

    @Test
    public void testValueIncrement() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.inc();
        assertEquals(1, value.x);

        value.inc();
        assertEquals(2, value.x);

        value.inc();
        assertEquals(3, value.x);
    }

    @Test
    public void testValueDecrement() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.x = 10;
        value.dec();
        assertEquals(9, value.x);

        value.dec();
        assertEquals(8, value.x);
    }

    @Test
    public void testValueIncAndDecBalance() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.inc();
        value.inc();
        value.inc();
        value.dec();
        value.dec();
        assertEquals(1, value.x);
    }

    @Test
    public void testValueMultipleIncrements() {
        SyncThread3.Value value = new SyncThread3.Value();
        for (int i = 0; i < 100; i++) {
            value.inc();
        }
        assertEquals(100, value.x);
    }

    @Test
    public void testValueMultipleDecrements() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.x = 100;
        for (int i = 0; i < 100; i++) {
            value.dec();
        }
        assertEquals(0, value.x);
    }

    @Test
    public void testValueConcurrentIncAndDec() throws InterruptedException {
        SyncThread3.Value value = new SyncThread3.Value();
        final int X = 1000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                value.inc();
            }
        });

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                value.dec();
            }
        });

        incVar.start();
        decVar.start();

        incVar.join();
        decVar.join();

        // With synchronized methods, result should be 0
        assertEquals(0, value.x);
    }

    @Test
    public void testValueMultipleThreadsIncrement() throws InterruptedException {
        SyncThread3.Value value = new SyncThread3.Value();
        final int X = 100;
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    value.inc();
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * X, value.x);
    }

    @Test
    public void testValueMultipleThreadsDecrement() throws InterruptedException {
        SyncThread3.Value value = new SyncThread3.Value();
        value.x = 1000;
        final int X = 100;
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    value.dec();
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(0, value.x);
    }

    @Test
    public void testValueNegativeNumbers() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.dec();
        assertEquals(-1, value.x);

        value.dec();
        assertEquals(-2, value.x);

        value.inc();
        assertEquals(-1, value.x);
    }

    @Test
    public void testValueDirectAccess() {
        SyncThread3.Value value = new SyncThread3.Value();
        value.x = 42;
        assertEquals(42, value.x);
    }

    @Test
    public void testValueMixedConcurrentOperations() throws InterruptedException {
        SyncThread3.Value value = new SyncThread3.Value();
        final int operations = 500;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                value.inc();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                value.inc();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                value.dec();
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < operations; i++) {
                value.dec();
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
        assertEquals(0, value.x);
    }
}
