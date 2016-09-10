import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ManyThread and its inner MyClass
 */
public class ManyThreadTest extends Assert {

    @Test
    public void testMyClassCreation() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        assertNotNull(myClass);
        assertEquals(0, myClass.get());
    }

    @Test
    public void testMyClassInitialValue() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        assertEquals(0, myClass.get());
    }

    @Test
    public void testMyClassIncSingleThread() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        myClass.inc();
        assertEquals(1, myClass.get());

        myClass.inc();
        assertEquals(2, myClass.get());

        myClass.inc();
        assertEquals(3, myClass.get());
    }

    @Test
    public void testMyClassIncMultipleTimes() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        for (int i = 0; i < 100; i++) {
            myClass.inc();
        }
        assertEquals(100, myClass.get());
    }

    @Test
    public void testMyClassInc2SingleThread() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        myClass.inc2();
        assertEquals(1, myClass.get());

        myClass.inc2();
        assertEquals(2, myClass.get());

        myClass.inc2();
        assertEquals(3, myClass.get());
    }

    @Test
    public void testMyClassInc2MultipleTimes() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        for (int i = 0; i < 100; i++) {
            myClass.inc2();
        }
        assertEquals(100, myClass.get());
    }

    @Test
    public void testMyClassMixedIncAndInc2() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        myClass.inc();
        myClass.inc2();
        myClass.inc();
        myClass.inc2();
        assertEquals(4, myClass.get());
    }

    @Test
    public void testMyClassGetWithoutInc() {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        assertEquals(0, myClass.get());
    }

    @Test
    public void testMyClassIncMultipleThreads() throws InterruptedException {
        ManyThread.MyClass myClass = new ManyThread.MyClass();

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

        assertEquals(2000, myClass.get());
    }

    @Test
    public void testMyClassInc2MultipleThreads() throws InterruptedException {
        ManyThread.MyClass myClass = new ManyThread.MyClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc2();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                myClass.inc2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2000, myClass.get());
    }

    @Test
    public void testMyClassMixedIncAndInc2MultipleThreads() throws InterruptedException {
        ManyThread.MyClass myClass = new ManyThread.MyClass();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                myClass.inc();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                myClass.inc2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(1000, myClass.get());
    }

    @Test
    public void testMyClassThreadSafety() throws InterruptedException {
        ManyThread.MyClass myClass = new ManyThread.MyClass();
        int threadCount = 10;
        int incrementsPerThread = 100;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    if (threadIndex % 2 == 0) {
                        myClass.inc();
                    } else {
                        myClass.inc2();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount * incrementsPerThread, myClass.get());
    }

    @Test
    public void testManyThreadStaticLock() {
        assertNotNull(ManyThread.lock);
        assertTrue(ManyThread.lock instanceof Object);
    }

    @Test
    public void testManyThreadStaticMyClass() {
        assertNotNull(ManyThread.myClass);
        assertTrue(ManyThread.myClass instanceof ManyThread.MyClass);
    }

    @Test
    public void testMyClassEquivalenceOfIncAndInc2() {
        ManyThread.MyClass myClass1 = new ManyThread.MyClass();
        ManyThread.MyClass myClass2 = new ManyThread.MyClass();

        for (int i = 0; i < 100; i++) {
            myClass1.inc();
            myClass2.inc2();
        }

        assertEquals(myClass1.get(), myClass2.get());
    }
}
