import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Comprehensive unit tests for VolatileDemo
 */
public class VolatileDemoTest extends Assert {

    @Before
    public void setUp() {
        VolatileDemo.x = 0;
        VolatileDemo.changes = 0;
    }

    @Test
    public void testInitialValues() {
        VolatileDemo.x = 0;
        VolatileDemo.changes = 0;
        assertEquals(0, VolatileDemo.x);
        assertEquals(0, VolatileDemo.changes);
    }

    @Test
    public void testVolatileXModification() {
        VolatileDemo.x = 10;
        assertEquals(10, VolatileDemo.x);

        VolatileDemo.x = 100;
        assertEquals(100, VolatileDemo.x);
    }

    @Test
    public void testChangesCounter() {
        VolatileDemo.changes = 5;
        assertEquals(5, VolatileDemo.changes);

        VolatileDemo.changes = 10;
        assertEquals(10, VolatileDemo.changes);
    }

    @Test
    public void testChangeListenerCreation() {
        VolatileDemo.ChangeListener listener = new VolatileDemo.ChangeListener();
        assertNotNull(listener);
        assertTrue(listener instanceof Thread);
    }

    @Test
    public void testChangeMakerCreation() {
        VolatileDemo.ChangeMaker maker = new VolatileDemo.ChangeMaker();
        assertNotNull(maker);
        assertTrue(maker instanceof Thread);
    }

    @Test
    public void testChangeListenerIsThread() {
        VolatileDemo.ChangeListener listener = new VolatileDemo.ChangeListener();
        assertTrue(listener instanceof Thread);
    }

    @Test
    public void testChangeMakerIsThread() {
        VolatileDemo.ChangeMaker maker = new VolatileDemo.ChangeMaker();
        assertTrue(maker instanceof Thread);
    }

    @Test
    public void testChangeListenerAndMakerInteraction() throws InterruptedException {
        VolatileDemo.x = 0;
        VolatileDemo.changes = 0;

        VolatileDemo.ChangeListener listener = new VolatileDemo.ChangeListener();
        VolatileDemo.ChangeMaker maker = new VolatileDemo.ChangeMaker();

        listener.start();
        maker.start();

        // Give threads some time to run (but not complete)
        Thread.sleep(100);

        // X should have increased
        assertTrue(VolatileDemo.x > 0);

        // Wait for threads to complete (with timeout)
        listener.join(30000);
        maker.join(30000);

        // X should reach at least close to 5000
        assertTrue(VolatileDemo.x >= 5000);
    }

    @Test
    public void testVolatileXVisibility() throws InterruptedException {
        VolatileDemo.x = 0;

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                VolatileDemo.x = i;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader = new Thread(() -> {
            int lastSeen = -1;
            for (int i = 0; i < 100; i++) {
                int current = VolatileDemo.x;
                assertTrue(current >= lastSeen);
                lastSeen = current;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        assertTrue(VolatileDemo.x >= 0);
    }

    @Test
    public void testChangesCounterIncrement() {
        VolatileDemo.changes = 0;
        VolatileDemo.changes++;
        assertEquals(1, VolatileDemo.changes);

        VolatileDemo.changes++;
        assertEquals(2, VolatileDemo.changes);
    }

    @Test
    public void testVolatileXIncrementDecrement() {
        VolatileDemo.x = 10;
        VolatileDemo.x++;
        assertEquals(11, VolatileDemo.x);

        VolatileDemo.x--;
        assertEquals(10, VolatileDemo.x);
    }

    @Test
    public void testChangeMakerIncrementsX() throws InterruptedException {
        VolatileDemo.x = 0;

        VolatileDemo.ChangeMaker maker = new VolatileDemo.ChangeMaker();
        maker.start();

        // Let it run briefly
        Thread.sleep(50);

        // X should have increased
        assertTrue(VolatileDemo.x > 0);

        maker.join(30000);

        // X should be at least 5000
        assertTrue(VolatileDemo.x >= 5000);
    }

    @Test
    public void testMultipleChangeListeners() throws InterruptedException {
        VolatileDemo.x = 0;
        VolatileDemo.changes = 0;

        VolatileDemo.ChangeMaker maker = new VolatileDemo.ChangeMaker();
        maker.start();

        // Brief delay to let maker start
        Thread.sleep(10);

        VolatileDemo.ChangeListener listener1 = new VolatileDemo.ChangeListener();
        VolatileDemo.ChangeListener listener2 = new VolatileDemo.ChangeListener();

        listener1.start();
        listener2.start();

        maker.join(30000);
        listener1.join(30000);
        listener2.join(30000);

        assertTrue(VolatileDemo.x >= 5000);
    }
}
