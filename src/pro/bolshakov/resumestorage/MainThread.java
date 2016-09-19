package pro.bolshakov.resumestorage;


public class MainThread {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread thread0 = new Thread(() -> {
            synchronized (lock1){
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {

                }
                synchronized (lock2){
                    System.out.println("do not have deadlock");
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            synchronized (lock2){
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {

                }
                synchronized (lock1){
                    System.out.println("do not have deadlock");
                }
            }
        });

        thread0.start();
        thread1.start();

    }



}
