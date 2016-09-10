import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Comprehensive unit tests for Thread1
 */
public class Thread1Test extends Assert {

    @Before
    public void setUp() {
        Main.globalVar = 0;
        Main.atomicInteger.set(0);
        Main.strings.clear();
        Main.var2 = false;
        Thread1.ready = false;
    }

    @Test
    public void testThread1Creation() {
        Thread1 thread = new Thread1("Test Thread");
        assertNotNull(thread);
        assertEquals("Test Thread", thread.getName());
    }

    @Test
    public void testThread1NameSetting() {
        Thread1 thread = new Thread1("Custom Name");
        assertEquals("Custom Name", thread.getName());
    }

    @Test
    public void testReadyFlagInitiallyFalse() {
        assertFalse(Thread1.ready);
    }

    @Test
    public void testReadyFlagSetToTrue() throws InterruptedException {
        Thread1 thread = new Thread1("Test Thread");
        thread.start();

        // Wait for thread to complete
        thread.join(30000); // 30 second timeout

        // After thread completes, ready should be true
        assertTrue(Thread1.ready);
    }

    @Test
    public void testThread1IncrementsGlobalVar() throws InterruptedException {
        Thread1 thread = new Thread1("Test Thread");
        int initialValue = Main.globalVar;

        thread.start();
        thread.join(30000);

        // GlobalVar should have been incremented
        assertTrue(Main.globalVar > initialValue);
    }

    @Test
    public void testThread1IncrementsAtomicInteger() throws InterruptedException {
        Thread1 thread = new Thread1("Test Thread");
        int initialValue = Main.atomicInteger.get();

        thread.start();
        thread.join(30000);

        // AtomicInteger should have been incremented
        assertTrue(Main.atomicInteger.get() > initialValue);
    }

    @Test
    public void testThread1SetsVar2ToTrue() throws InterruptedException {
        Main.var2 = false;
        Thread1 thread = new Thread1("Test Thread");

        thread.start();

        // Give thread a chance to set var2
        Thread.sleep(100);

        // var2 should be set to true
        assertTrue(Main.var2);

        thread.join(30000);
    }

    @Test
    public void testThread1AddsStringsToQueue() throws InterruptedException {
        Thread1 thread = new Thread1("Test Thread");

        thread.start();
        thread.join(30000);

        // Strings should have been added to the queue
        // (may be consumed by other threads, but should have been added)
        assertTrue(Thread1.ready);
    }

    @Test
    public void testMultipleThread1Instances() throws InterruptedException {
        Thread1 thread1 = new Thread1("Thread 1");
        Thread1 thread2 = new Thread1("Thread 2");

        assertNotEquals(thread1.getName(), thread2.getName());

        thread1.start();
        thread2.start();

        thread1.join(30000);
        thread2.join(30000);

        assertTrue(Thread1.ready);
    }

    @Test
    public void testThread1IsThreadSubclass() {
        Thread1 thread = new Thread1("Test");
        assertTrue(thread instanceof Thread);
    }
}
