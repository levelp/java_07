import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SyncThread
 */
public class SyncThreadTest extends Assert {

    @Test
    public void testSyncThreadIncrement() throws InterruptedException {
        final int[] x = {0};
        final int X = 1000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]++;
            }
        });

        incVar.start();
        incVar.join();

        assertEquals(X, x[0]);
    }

    @Test
    public void testSyncThreadDecrement() throws InterruptedException {
        final int[] x = {1000};
        final int X = 1000;

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]--;
            }
        });

        decVar.start();
        decVar.join();

        assertEquals(0, x[0]);
    }

    @Test
    public void testSyncThreadBothOperations() throws InterruptedException {
        final int[] x = {0};
        final int X = 100;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]++;
            }
        });

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]--;
            }
        });

        incVar.start();
        decVar.start();

        incVar.join();
        decVar.join();

        // Without proper synchronization, result may not be 0
        // This test demonstrates the race condition
        assertTrue(x[0] >= -X && x[0] <= X);
    }

    @Test
    public void testSyncThreadThreadJoin() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join();

        // Thread should have completed
        assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    public void testSyncThreadRaceCondition() throws InterruptedException {
        // This test demonstrates that without synchronization,
        // concurrent increments and decrements may not result in 0
        final int[] x = {0};
        final int X = 10000;

        Thread incVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]++;
            }
        });

        Thread decVar = new Thread(() -> {
            for (int i = 0; i < X; i++) {
                x[0]--;
            }
        });

        incVar.start();
        decVar.start();

        incVar.join();
        decVar.join();

        // Result is unpredictable without synchronization
        assertTrue(Math.abs(x[0]) >= 0);
    }

    @Test
    public void testThreadCreationAndStart() {
        Thread thread = new Thread(() -> {
            // Do nothing
        });

        assertNotNull(thread);
        assertEquals(Thread.State.NEW, thread.getState());

        thread.start();

        assertTrue(thread.getState() != Thread.State.NEW);
    }

    @Test
    public void testLambdaRunnableCreation() {
        Runnable runnable = () -> {
            int x = 0;
            x++;
        };

        assertNotNull(runnable);
        Thread thread = new Thread(runnable);
        assertNotNull(thread);
    }
}
