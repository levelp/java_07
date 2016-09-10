import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

// atomic
// ------
// Классы из пакета java.util.concurrent.atomic обеспечивают
// выполнение атомарных операций

/**
 * AtomicInteger
 */
//-->
public class AtomicDemo {
    static final Object LOCK = new Object();
    static final AtomicInteger ATOMIC_SUM = new AtomicInteger();
    static final CountDownLatch CDL = new CountDownLatch(100000);
    static int sum = 0;
    static volatile int globalI;
    static int threadCount = 0;

    static {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        AtomicLong atomicLong = new AtomicLong(3232L);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        // Нет: AtomicByte, AtomicShort, AtomicFloat, AtomicDouble, AtomicString
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 3, 4});
        atomicInteger.addAndGet(10);
        atomicBoolean.set(true);
        atomicBoolean.get();
        atomicLong.addAndGet(100000);
        atomicIntegerArray.addAndGet(1, 10);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
        System.out.println("sum = " + sum);
        System.out.println("ATOMIC_SUM = " + ATOMIC_SUM.get());
        System.out.println("globalI = " + globalI);
        System.out.println("wait");
        Thread.sleep(500);
        CDL.await();
        System.out.println("threadCount = " + threadCount);
        System.out.println("sum = " + sum);
        System.out.println("ATOMIC_SUM = " + ATOMIC_SUM.get());
    }

    static void inc() {
        synchronized (LOCK) {
            sum++;
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            threadCount++;
            for (int i = 0; i < 100; ++i) {
                globalI++;
                inc();
                ATOMIC_SUM.incrementAndGet();
                CDL.countDown();
            }
        }
    }
}
//<--