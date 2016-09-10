import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ThreadDemo and its inner MyClass
 */
public class ThreadDemoTest extends Assert {

    @Test
    public void testMyClassCreation() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        assertNotNull(myClass);
        assertEquals(0, myClass.counter);
    }

    @Test
    public void testMyClassIncrementSingleThread() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        myClass.inc();
        assertEquals(1, myClass.counter);

        myClass.inc();
        assertEquals(2, myClass.counter);

        myClass.inc();
        assertEquals(3, myClass.counter);
    }

    @Test
    public void testMyClassIncrementMultipleTimes() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        for (int i = 0; i < 100; i++) {
            myClass.inc();
        }
        assertEquals(100, myClass.counter);
    }

    @Test
    public void testMyClassIncrementMultipleThreads() throws InterruptedException {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2000, myClass.counter);
    }

    @Test
    public void testMyClassInitialValue() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        assertEquals(0, myClass.counter);
    }

    @Test
    public void testMyClassSynchronization() throws InterruptedException {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        int threadCount = 10;
        int incrementsPerThread = 100;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    myClass.inc();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * incrementsPerThread, myClass.counter);
    }

    @Test
    public void testMyClassNegativeCounter() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        myClass.counter = -5;
        myClass.inc();
        assertEquals(-4, myClass.counter);
    }

    @Test
    public void testMyClassLargeIncrements() {
        ThreadDemo.MyClass myClass = new ThreadDemo.MyClass();
        for (int i = 0; i < 10000; i++) {
            myClass.inc();
        }
        assertEquals(10000, myClass.counter);
    }

    @Test
    public void testThreadDemoStaticCounter() {
        int initialValue = ThreadDemo.counter;
        ThreadDemo.counter = 42;
        assertEquals(42, ThreadDemo.counter);
        ThreadDemo.counter = initialValue; // Reset
    }

    @Test
    public void testThreadDemoStaticMyClass() {
        assertNotNull(ThreadDemo.myClass);
        assertTrue(ThreadDemo.myClass instanceof ThreadDemo.MyClass);
    }
}
