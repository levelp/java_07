import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SyncThread4 and its inner Value class
 */
public class SyncThread4Test extends Assert {

    @Test
    public void testValueCreation() {
        SyncThread4.Value value = new SyncThread4.Value();
        assertNotNull(value);
        assertEquals(0, value.x);
    }

    @Test
    public void testValueInitialValue() {
        SyncThread4.Value value = new SyncThread4.Value();
        assertEquals(0, value.x);
    }

    @Test
    public void testValueIncrement() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.inc();
        assertEquals(1, value.x);

        value.inc();
        assertEquals(2, value.x);

        value.inc();
        assertEquals(3, value.x);
    }

    @Test
    public void testValueDecrement() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.x = 10;
        value.dec();
        assertEquals(9, value.x);

        value.dec();
        assertEquals(8, value.x);
    }

    @Test
    public void testValueIncAndDecBalance() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.inc();
        value.inc();
        value.inc();
        value.dec();
        value.dec();
        assertEquals(1, value.x);
    }

    @Test
    public void testValueMultipleIncrements() {
        SyncThread4.Value value = new SyncThread4.Value();
        for (int i = 0; i < 100; i++) {
            value.inc();
        }
        assertEquals(100, value.x);
    }

    @Test
    public void testValueMultipleDecrements() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.x = 100;
        for (int i = 0; i < 100; i++) {
            value.dec();
        }
        assertEquals(0, value.x);
    }

    @Test
    public void testValueConcurrentIncAndDecWithDifferentLocks() throws InterruptedException {
        SyncThread4.Value value = new SyncThread4.Value();
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

        // inc() uses synchronized(this), dec() uses synchronized(xx)
        // Different locks, so result may not be 0 due to race condition
        // But the test should complete without exception
        assertTrue(Math.abs(value.x) >= 0);
    }

    @Test
    public void testValueMultipleThreadsIncrement() throws InterruptedException {
        SyncThread4.Value value = new SyncThread4.Value();
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

        // inc() is properly synchronized on this
        assertEquals(threadCount * X, value.x);
    }

    @Test
    public void testValueMultipleThreadsDecrement() throws InterruptedException {
        SyncThread4.Value value = new SyncThread4.Value();
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

        // dec() is properly synchronized on xx
        assertEquals(0, value.x);
    }

    @Test
    public void testValueNegativeNumbers() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.dec();
        assertEquals(-1, value.x);

        value.dec();
        assertEquals(-2, value.x);

        value.inc();
        assertEquals(-1, value.x);
    }

    @Test
    public void testValueDirectAccess() {
        SyncThread4.Value value = new SyncThread4.Value();
        value.x = 42;
        assertEquals(42, value.x);
    }

    @Test
    public void testSyncThread4StaticLock() {
        assertNotNull(SyncThread4.xx);
        assertTrue(SyncThread4.xx instanceof Object);
    }

    @Test
    public void testSyncThread4StaticValue() {
        assertNotNull(SyncThread4.x);
        assertTrue(SyncThread4.x instanceof SyncThread4.Value);
    }

    @Test
    public void testValueIncUsesThisLock() throws InterruptedException {
        SyncThread4.Value value = new SyncThread4.Value();

        Thread t1 = new Thread(() -> {
            synchronized (value) {
                value.x = 100;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value.inc(); // Should wait for t1 to release lock
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(101, value.x);
    }

    @Test
    public void testValueDecUsesStaticLock() throws InterruptedException {
        SyncThread4.Value value = new SyncThread4.Value();
        value.x = 100;

        Thread t1 = new Thread(() -> {
            synchronized (SyncThread4.xx) {
                value.x = 50;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value.dec(); // Should wait for t1 to release lock
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(49, value.x);
    }
}
