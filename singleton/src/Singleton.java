import java.util.Calendar;
public class Singleton extends Thread {
    private static Singleton instance;
    // flag
    // get & set
    private Singleton() {}
    public synchronized static Singleton getInstance(){
        if(instance==null) {
            instance = new Singleton();
            instance.start();
        }
        return instance;
    }

    public synchronized void run(){
        try {
            while(true){
                System.out.println(Calendar.getInstance().getTime());
                System.out.println(java.lang.Thread.activeCount());
                wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
