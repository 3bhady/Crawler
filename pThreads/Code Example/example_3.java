
public class example_3 implements Runnable {
 
    public void run() {
          do_work();  
    }
 
    public void do_work()  {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread isInterrupted()="+ Thread.currentThread().isInterrupted());
                break;
            }
        }
    }

    public static void main(String args[]) {

        Thread t = new Thread(new example_3());
        t.start();

        try {  Thread.sleep(2000);  }
        catch (InterruptedException x) { return; }

        t.interrupt();
    }

}



