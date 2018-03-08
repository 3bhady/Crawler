
// 1. Implement the Runnable interface
public class example_4 implements Runnable {

	// 2. Implement the run method
    public void run() {
    	// 5. Uncomment the following to see 2 threads running! 
    	
    	for (int i = 0 ; i < 5 ; i++) {
       		System.out.println("Hello from "+Thread.currentThread().getId());
			
			try {
				// Thread.sleep causes the current thread to suspend execution for a specified period
			    Thread.sleep(100+Thread.currentThread().getId());                 //1000 milliseconds is one second.
			
			//This is an exception that sleep throws when another thread interrupts the current thread while sleep is active.
			} catch(InterruptedException ex) { 
			    return;
			}
       	}
       	
    }

    // 3. Implement the main method
    public static void main(String args[]) throws Exception {
        
        // Get a handler on the current main thread
        Thread t0 = Thread.currentThread();

        // 4. Create 2 threads
        Thread t1 = new Thread(new example_4());
        Thread t2 = new Thread(new example_4());

        // 5. Set the name of each thread
        t0.setName("Main Thread");
        t1.setName("Thread 1");
        t2.setName("Thread 2");

        // 6. Start threads (t0 already running)
        t1.start();
        t2.start();

        // 7. Wait for them to join the current thread
        t1.join(); 
        t2.join(); 

      	for (int i = 0 ; i < 5 ; i++) {
        	System.out.println("Hello from the main!");
        	try {
			    Thread.sleep(500);                 //500 milliseconds is 0.5 second.
			} catch(InterruptedException ex) {
			    return;
			}
        }
    }

}