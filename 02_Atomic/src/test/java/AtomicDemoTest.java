import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comprehensive unit tests for AtomicDemo and its inner MyThread class
 */
public class AtomicDemoTest extends Assert {

    @Before
    public void setUp() {
        AtomicDemo.sum = 0;
        AtomicDemo.ATOMIC_SUM.set(0);
        AtomicDemo.globalI = 0;
        AtomicDemo.threadCount = 0;
    }

    @Test
    public void testAtomicDemoStaticFields() {
        assertNotNull(AtomicDemo.LOCK);
        assertNotNull(AtomicDemo.ATOMIC_SUM);
        assertNotNull(AtomicDemo.CDL);
    }

    @Test
    public void testAtomicSumIncrement() {
        int initialValue = AtomicDemo.ATOMIC_SUM.get();
        AtomicDemo.ATOMIC_SUM.incrementAndGet();
        assertEquals(initialValue + 1, AtomicDemo.ATOMIC_SUM.get());
    }

    @Test
    public void testIncMethod() {
        AtomicDemo.sum = 0;
        AtomicDemo.inc();
        assertEquals(1, AtomicDemo.sum);

        AtomicDemo.inc();
        assertEquals(2, AtomicDemo.sum);

        AtomicDemo.inc();
        assertEquals(3, AtomicDemo.sum);
    }

    @Test
    public void testIncMethodMultipleTimes() {
        AtomicDemo.sum = 0;
        for (int i = 0; i < 100; i++) {
            AtomicDemo.inc();
        }
        assertEquals(100, AtomicDemo.sum);
    }

    @Test
    public void testIncMethodThreadSafety() throws InterruptedException {
        AtomicDemo.sum = 0;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                AtomicDemo.inc();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                AtomicDemo.inc();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // With synchronized inc(), result should be exactly 2000
        assertEquals(2000, AtomicDemo.sum);
    }

    @Test
    public void testMyThreadCreation() {
        AtomicDemo.MyThread thread = new AtomicDemo.MyThread();
        assertNotNull(thread);
        assertTrue(thread instanceof Thread);
    }

    @Test
    public void testMyThreadExecution() throws InterruptedException {
        AtomicDemo.sum = 0;
        AtomicDemo.ATOMIC_SUM.set(0);
        AtomicDemo.globalI = 0;
        AtomicDemo.threadCount = 0;

        AtomicDemo.MyThread thread = new AtomicDemo.MyThread();
        thread.start();
        thread.join();

        // Thread should have incremented sum 100 times
        assertEquals(100, AtomicDemo.sum);
        assertEquals(100, AtomicDemo.ATOMIC_SUM.get());
        assertEquals(1, AtomicDemo.threadCount);
    }

    @Test
    public void testMyThreadMultipleInstances() throws InterruptedException {
        AtomicDemo.sum = 0;
        AtomicDemo.ATOMIC_SUM.set(0);
        AtomicDemo.threadCount = 0;

        AtomicDemo.MyThread thread1 = new AtomicDemo.MyThread();
        AtomicDemo.MyThread thread2 = new AtomicDemo.MyThread();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // Each thread increments 100 times
        assertEquals(200, AtomicDemo.sum);
        assertEquals(200, AtomicDemo.ATOMIC_SUM.get());
    }

    @Test
    public void testLockObject() {
        assertNotNull(AtomicDemo.LOCK);
        assertTrue(AtomicDemo.LOCK instanceof Object);
    }

    @Test
    public void testAtomicSumType() {
        assertTrue(AtomicDemo.ATOMIC_SUM instanceof AtomicInteger);
    }

    @Test
    public void testGlobalIIncrement() {
        AtomicDemo.globalI = 0;
        AtomicDemo.globalI++;
        assertEquals(1, AtomicDemo.globalI);

        AtomicDemo.globalI++;
        assertEquals(2, AtomicDemo.globalI);
    }

    @Test
    public void testThreadCountIncrement() {
        AtomicDemo.threadCount = 0;
        AtomicDemo.threadCount++;
        assertEquals(1, AtomicDemo.threadCount);

        AtomicDemo.threadCount++;
        assertEquals(2, AtomicDemo.threadCount);
    }

    @Test
    public void testSumIncrement() {
        AtomicDemo.sum = 0;
        AtomicDemo.sum++;
        assertEquals(1, AtomicDemo.sum);

        AtomicDemo.sum++;
        assertEquals(2, AtomicDemo.sum);
    }

    @Test
    public void testAtomicIntegerOperations() {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        assertEquals(2, atomicInteger.get());

        atomicInteger.addAndGet(10);
        assertEquals(12, atomicInteger.get());

        atomicInteger.incrementAndGet();
        assertEquals(13, atomicInteger.get());
    }

    @Test
    public void testMyThreadIncrementsSynchronizedSum() throws InterruptedException {
        AtomicDemo.sum = 0;

        int threadCount = 5;
        AtomicDemo.MyThread[] threads = new AtomicDemo.MyThread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new AtomicDemo.MyThread();
            threads[i].start();
        }

        for (AtomicDemo.MyThread thread : threads) {
            thread.join();
        }

        // Each thread increments sum 100 times with synchronization
        assertEquals(threadCount * 100, AtomicDemo.sum);
    }

    @Test
    public void testMyThreadIncrementsAtomicSum() throws InterruptedException {
        AtomicDemo.ATOMIC_SUM.set(0);

        int threadCount = 5;
        AtomicDemo.MyThread[] threads = new AtomicDemo.MyThread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new AtomicDemo.MyThread();
            threads[i].start();
        }

        for (AtomicDemo.MyThread thread : threads) {
            thread.join();
        }

        // Each thread increments ATOMIC_SUM 100 times
        assertEquals(threadCount * 100, AtomicDemo.ATOMIC_SUM.get());
    }
}
