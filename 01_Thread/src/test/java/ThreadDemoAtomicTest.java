import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comprehensive unit tests for ThreadDemoAtomic and its inner MyClass
 */
public class ThreadDemoAtomicTest extends Assert {

    @Test
    public void testMyClassCreation() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        assertNotNull(myClass);
        assertEquals(0, myClass.counter.get());
    }

    @Test
    public void testMyClassInitialValue() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        assertEquals(0, myClass.counter.get());
    }

    @Test
    public void testMyClassIncrementSingleThread() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        myClass.inc();
        assertEquals(1, myClass.counter.get());

        myClass.inc();
        assertEquals(2, myClass.counter.get());

        myClass.inc();
        assertEquals(3, myClass.counter.get());
    }

    @Test
    public void testMyClassIncrementMultipleTimes() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        for (int i = 0; i < 100; i++) {
            myClass.inc();
        }
        assertEquals(100, myClass.counter.get());
    }

    @Test
    public void testMyClassIncrementMultipleThreads() throws InterruptedException {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();

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

        assertEquals(2000, myClass.counter.get());
    }

    @Test
    public void testMyClassAtomicIntegerType() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        assertTrue(myClass.counter instanceof AtomicInteger);
    }

    @Test
    public void testMyClassCounterDirectAccess() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        myClass.counter.set(42);
        assertEquals(42, myClass.counter.get());
    }

    @Test
    public void testMyClassConcurrentIncrements() throws InterruptedException {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
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

        assertEquals(threadCount * incrementsPerThread, myClass.counter.get());
    }

    @Test
    public void testMyClassGetAndIncrement() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        int oldValue = myClass.counter.getAndIncrement();
        assertEquals(0, oldValue);
        assertEquals(1, myClass.counter.get());
    }

    @Test
    public void testMyClassIncrementFromNonZero() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        myClass.counter.set(100);
        myClass.inc();
        assertEquals(101, myClass.counter.get());
    }

    @Test
    public void testThreadDemoAtomicStaticCounter() {
        int initialValue = ThreadDemoAtomic.counter;
        ThreadDemoAtomic.counter = 42;
        assertEquals(42, ThreadDemoAtomic.counter);
        ThreadDemoAtomic.counter = initialValue; // Reset
    }

    @Test
    public void testThreadDemoAtomicStaticMyClass() {
        assertNotNull(ThreadDemoAtomic.myClass);
        assertTrue(ThreadDemoAtomic.myClass instanceof ThreadDemoAtomic.MyClass);
    }

    @Test
    public void testMyClassLargeNumberOfIncrements() {
        ThreadDemoAtomic.MyClass myClass = new ThreadDemoAtomic.MyClass();
        for (int i = 0; i < 10000; i++) {
            myClass.inc();
        }
        assertEquals(10000, myClass.counter.get());
    }
}
