
// 1. Implement the Runnable interface
public class example_1 implements Runnable {

	// 2. Implement the run method
    public void run() {
    	//System.out.println("Hello from a Runnable thread!");
		for (int i = 0 ; i < 5 ; i++) {
        	System.out.println("Hello from a Runnable thread!");
        	try {
			    Thread.sleep(500);                 //500 milliseconds is 0.5 second.
			} catch(InterruptedException ex) {
			    return;
			}
        }
       	
    }

    // 3. Implement the main method
    public static void main(String args[]) {
        // 4. Create a new object of type runnable and give it to the thread
        (new Thread(new example_1())).start();



        // 5. Uncomment the following to see 2 threads running! 
        
        /*
      	for (int i = 0 ; i < 5 ; i++) {
        	System.out.println("Hello from the main!");
        	try {
			    Thread.sleep(500);                 //500 milliseconds is 0.5 second.
			} catch(InterruptedException ex) {
			    return;
			}
        }
		*/
		System.out.println("Last Line in main!");
        
    }

}
