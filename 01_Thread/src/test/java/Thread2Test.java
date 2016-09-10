import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Comprehensive unit tests for Thread2
 */
public class Thread2Test extends Assert {

    @Before
    public void setUp() {
        Main.globalVar = 0;
        Main.atomicInteger.set(0);
        Main.strings.clear();
        Main.var2 = false;
        Thread1.ready = false;
    }

    @Test
    public void testThread2Creation() {
        Thread2 thread2 = new Thread2();
        assertNotNull(thread2);
    }

    @Test
    public void testThread2IsRunnable() {
        Thread2 thread2 = new Thread2();
        assertTrue(thread2 instanceof Runnable);
    }

    @Test
    public void testThread2IncrementsAtomicInteger() throws InterruptedException {
        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);

        int initialValue = Main.atomicInteger.get();

        thread.start();
        thread.join(30000);

        assertTrue(Main.atomicInteger.get() > initialValue);
    }

    @Test
    public void testThread2IncrementsGlobalVar() throws InterruptedException {
        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);

        int initialValue = Main.globalVar;

        thread.start();
        thread.join(30000);

        assertTrue(Main.globalVar > initialValue);
    }

    @Test
    public void testThread2WaitsForThread1Ready() throws InterruptedException {
        Thread1.ready = false;
        Thread2 thread2 = new Thread2();
        Thread t2 = new Thread(thread2);

        t2.start();

        // Let Thread2 start running
        Thread.sleep(100);

        // Set Thread1.ready to true
        Thread1.ready = true;

        t2.join(30000);

        assertTrue(t2.getState() == Thread.State.TERMINATED);
    }

    @Test
    public void testThread2ProcessesStrings() throws InterruptedException {
        // Add strings to queue
        for (int i = 0; i < 10; i++) {
            Main.strings.add("Test string " + i);
        }

        Thread1.ready = true;

        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);

        thread.start();
        thread.join(30000);

        // Strings should have been consumed
        assertTrue(Main.strings.isEmpty() || Main.strings.size() < 10);
    }

    @Test
    public void testThread2WithEmptyQueue() throws InterruptedException {
        Main.strings.clear();
        Thread1.ready = true;

        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);

        thread.start();
        thread.join(30000);

        assertTrue(thread.getState() == Thread.State.TERMINATED);
    }

    @Test
    public void testThread2ExecutionWithThread1() throws InterruptedException {
        Thread1 thread1 = new Thread1("Test Thread1");
        Thread2 thread2 = new Thread2();
        Thread t2 = new Thread(thread2);

        thread1.start();
        t2.start();

        thread1.join(30000);
        t2.join(30000);

        assertTrue(Thread1.ready);
        assertTrue(Main.atomicInteger.get() > 0);
    }
}
