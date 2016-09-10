package vol;

public class DelayWrite implements Runnable {
    private String str;

    public static void main(String[] args) throws InterruptedException {
        DelayWrite delay = new DelayWrite();
        new Thread(delay).start();
        Thread.sleep(1000);
        delay.setStr("Hello world!!");
    }

    void setStr(String str) {
        this.str = str;
    }

    public void run() {
        while (str == null) ;
        System.out.println(str);
    }
}