import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ThreadDemo2 and its inner MyClass
 */
public class ThreadDemo2Test extends Assert {

    @Test
    public void testMyClassCreation() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        assertNotNull(myClass);
        assertEquals(0, myClass.getCounter());
    }

    @Test
    public void testMyClassInitialValue() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        assertEquals(0, myClass.getCounter());
    }

    @Test
    public void testMyClassIncWithPositiveValue() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        myClass.inc(10);
        assertEquals(10, myClass.getCounter());

        myClass.inc(5);
        assertEquals(15, myClass.getCounter());
    }

    @Test
    public void testMyClassIncWithNegativeValue() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        myClass.inc(100);
        myClass.inc(-50);
        assertEquals(50, myClass.getCounter());
    }

    @Test
    public void testMyClassIncWithZero() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        myClass.inc(10);
        myClass.inc(0);
        assertEquals(10, myClass.getCounter());
    }

    @Test
    public void testMyClassIncMultipleTimes() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        for (int i = 0; i < 100; i++) {
            myClass.inc(1);
        }
        assertEquals(100, myClass.getCounter());
    }

    @Test
    public void testMyClassIncLargeValue() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        myClass.inc(1000);
        assertEquals(1000, myClass.getCounter());
    }

    @Test
    public void testMyClassGetCounter() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        assertEquals(0, myClass.getCounter());

        myClass.inc(42);
        assertEquals(42, myClass.getCounter());
    }

    @Test
    public void testMyClassSynchronizationWithMultipleThreads() throws InterruptedException {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc(1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc(1);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2000, myClass.getCounter());
    }

    @Test
    public void testMyClassConcurrentIncWithDifferentValues() throws InterruptedException {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myClass.inc(10);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myClass.inc(5);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(1500, myClass.getCounter());
    }

    @Test
    public void testMyClassThreadSafety() throws InterruptedException {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        int threadCount = 10;
        int incrementsPerThread = 100;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    myClass.inc(1);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * incrementsPerThread, myClass.getCounter());
    }

    @Test
    public void testThreadDemo2StaticMyClass() {
        assertNotNull(ThreadDemo2.myClass);
        assertTrue(ThreadDemo2.myClass instanceof ThreadDemo2.MyClass);
    }

    @Test
    public void testMyClassCounterDirectAccess() {
        ThreadDemo2.MyClass myClass = new ThreadDemo2.MyClass();
        myClass.counter = 100;
        assertEquals(100, myClass.counter);
        assertEquals(100, myClass.getCounter());
    }
}
