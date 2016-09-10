import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/// Работа с потоками
/// -----------------
public class Main {
    public static final SharedClass lock = new SharedClass();
    public static final Queue<String> strings = new LinkedList<String>();
    public static int globalVar = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static boolean var2 = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Имя главного потока: " +
                Thread.currentThread().getName());

        Thread1 thread1 = new Thread1("Первый поток");
        thread1.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();

        System.out.println("Ждём завершения потока 2");
        thread2.join();
        System.out.println("Основной поток после завершения потока 2");
        // Остановились
        System.out.println("Ждём завершения потока 1");
        thread1.join();
        System.out.println("Основной поток после завершения потока 1");

        System.out.println("globalVar = " + globalVar);
        System.out.println("atomicInteger = " + atomicInteger);
        System.out.println("Программа завершена!");
    }
}
