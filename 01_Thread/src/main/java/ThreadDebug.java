/**
 *
 */
public class ThreadDebug {

    public static void main(String[] args) throws InterruptedException {
        MyClass ii = new MyClass();
        Thread t2 = null;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                ii.inc();
                System.out.println("i = " + i);
                try {
                    //t2.wait();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("Поток 1");
        t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                ii.inc();
                System.out.println("Thread2: i = " + i);
                try {
                    t1.wait();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.setName("Поток 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    static public class MyClass {
        public int i;

        synchronized public void inc() {
            i++;
            System.out.println(Thread.currentThread().getName());
        }
    }
}
