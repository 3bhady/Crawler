
// 1. Extend the Thread class
public class example_2 extends Thread {

	// 2. Implement the run method
    public void run() {
        System.out.println("Hello from a thread!");
    }

    // 3. Implement the main method
    public static void main(String args[]) {
        // 4. Create a new thread and start it
        (new example_2()).start();
    }

    // 5. Can you replicate the same behavior of example 1? 

}


