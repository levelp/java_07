import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest extends Assert {

    private int counter = 0;
    private volatile int volatileCounter = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        while (true) {
            test.testVolatile();
        }
    }

    @Test
    public void testVolatile() throws InterruptedException {
        // Создаём много потоков
        int numberOfThreads = 1000;
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; ++i) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter++;
                    volatileCounter++;
                    atomicInteger.incrementAndGet();
                }
            });
            t.start();
            list.add(t); // Добавляем поток в список
        }

        for (Thread t : list)
            t.join();

        System.out.println("counter = " + counter);
        System.out.println("volatileCounter = " + volatileCounter);
        System.out.println("atomicInteger = " + atomicInteger);

        assertTrue(counter < volatileCounter);
        assertTrue(volatileCounter < atomicInteger.get());
    }
}
