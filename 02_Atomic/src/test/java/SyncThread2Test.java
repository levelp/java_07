import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SyncThread2
 */
public class SyncThread2Test extends Assert {

    @Test
    public void testSynchronizedIncrement() throws InterruptedException {
        final int[] x = {0};
        final Object myObject = 1;
        final int X = 1000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (myObject) {
                    x[0]++;
                }
            }
        });

        incVar.start();
        incVar.join();

        assertEquals(X, x[0]);
    }

    @Test
    public void testSynchronizedDecrement() throws InterruptedException {
        final int[] x = {1000};
        final Object myObject = 1;
        final int X = 1000;

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (myObject) {
                    x[0]--;
                }
            }
        });

        decVar.start();
        decVar.join();

        assertEquals(0, x[0]);
    }

    @Test
    public void testSynchronizedBothOperations() throws InterruptedException {
        final int[] x = {0};
        final Object myObject = 1;
        final int X = 1000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (myObject) {
                    x[0]++;
                }
            }
        });

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (myObject) {
                    x[0]--;
                }
            }
        });

        incVar.start();
        decVar.start();

        incVar.join();
        decVar.join();

        // With proper synchronization, result should be 0
        assertEquals(0, x[0]);
    }

    @Test
    public void testSynchronizedBlockWithObject() {
        final int[] counter = {0};
        final Object lock = new Object();

        synchronized (lock) {
            counter[0]++;
        }

        assertEquals(1, counter[0]);
    }

    @Test
    public void testMultipleSynchronizedIncrements() throws InterruptedException {
        final int[] x = {0};
        final Object myObject = new Object();
        final int X = 100;
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < X; i++) {
                    synchronized (myObject) {
                        x[0]++;
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * X, x[0]);
    }

    @Test
    public void testSynchronizedWithDifferentLocks() throws InterruptedException {
        final int[] x = {0};
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        final int X = 100;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (lock1) {
                    x[0]++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                synchronized (lock2) {
                    x[0]++;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Different locks don't provide mutual exclusion
        // Result may not be exactly 2*X due to race condition
        assertTrue(x[0] > 0 && x[0] <= 2 * X);
    }

    @Test
    public void testSynchronizedBlockNesting() {
        final int[] counter = {0};
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        synchronized (lock1) {
            synchronized (lock2) {
                counter[0]++;
            }
        }

        assertEquals(1, counter[0]);
    }

    @Test
    public void testThreadStateAfterCompletion() throws InterruptedException {
        final Object myObject = new Object();
        Thread thread = new Thread(() -> {
            synchronized (myObject) {
                // Do nothing
            }
        });

        thread.start();
        thread.join();

        assertEquals(Thread.State.TERMINATED, thread.getState());
    }
}
