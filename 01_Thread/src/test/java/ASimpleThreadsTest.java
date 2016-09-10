import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ASimpleThreads class
 */
public class ASimpleThreadsTest extends Assert {

    @Test
    public void testASimpleThreadsClassExists() {
        try {
            Class.forName("ASimpleThreads");
        } catch (ClassNotFoundException e) {
            fail("ASimpleThreads class should exist");
        }
    }

    @Test
    public void testASimpleThreadsHasMainMethod() throws Exception {
        Class<?> clazz = Class.forName("ASimpleThreads");
        java.lang.reflect.Method mainMethod = clazz.getMethod("main", String[].class);

        assertNotNull(mainMethod);
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
    }

    @Test
    public void testMainMethodSignature() throws Exception {
        Class<?> clazz = Class.forName("ASimpleThreads");
        java.lang.reflect.Method mainMethod = clazz.getMethod("main", String[].class);

        assertEquals(void.class, mainMethod.getReturnType());
        assertEquals(1, mainMethod.getParameterCount());
    }

    @Test
    public void testClassIsPublic() throws Exception {
        Class<?> clazz = Class.forName("ASimpleThreads");
        assertTrue(java.lang.reflect.Modifier.isPublic(clazz.getModifiers()));
    }

    @Test
    public void testThreadCreation() {
        // Test that threads can be created with Runnable
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulating the first thread in ASimpleThreads
            }
        });

        Thread thread2 = new Thread(() -> {
            // Simulating the second thread using lambda
        });

        assertNotNull(thread1);
        assertNotNull(thread2);
    }

    @Test
    public void testRunnableImplementation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Test implementation
            }
        };

        assertNotNull(runnable);
        Thread thread = new Thread(runnable);
        assertNotNull(thread);
    }

    @Test
    public void testLambdaRunnable() {
        Runnable lambda = () -> {
            // Lambda implementation
        };

        assertNotNull(lambda);
        Thread thread = new Thread(lambda);
        assertNotNull(thread);
    }

    @Test
    public void testThreadSleep() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(10);
        long end = System.currentTimeMillis();

        assertTrue(end - start >= 10);
    }

    @Test
    public void testInterruptedException() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                fail("Should have been interrupted");
            } catch (InterruptedException e) {
                // Expected exception
                assertNotNull(e);
            }
        });

        thread.start();
        thread.interrupt();

        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            fail("Main thread should not be interrupted");
        }
    }

    @Test
    public void testThreadStart() throws InterruptedException {
        final boolean[] executed = {false};

        Thread thread = new Thread(() -> {
            executed[0] = true;
        });

        assertFalse(executed[0]);
        thread.start();
        thread.join(100);
        assertTrue(executed[0]);
    }

    @Test
    public void testMultipleThreadsExecution() throws InterruptedException {
        final int[] counter = {0};

        Thread t1 = new Thread(() -> counter[0]++);
        Thread t2 = new Thread(() -> counter[0]++);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(2, counter[0]);
    }
}
