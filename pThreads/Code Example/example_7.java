/*
This code should illustrate that synchronizing 2 methods of the same object
makes the access to them synchronized with each other
Run this program with and without synchronized several times to see the point
What we're expecting is that 2 threads working on 2 different synched methods, 
should execute in parallel, well it's not!
Although the 2 methods are different, they are synchronized with each other, (i.e
the 2 methods are working sequentially not in parallel), why? 
Because "synchronized" locks the object holding these 2 methods, so even if the 
one method is separate from the other, they'll be synchronized, as any method won't 
get the the lock until it's released by any other thread holding it
*/

public class example_7 {
	public static void main (String [] args) throws InterruptedException {
		MyCounter counter = new MyCounter();

		Thread t1 = new Thread (new CounterRunnable(counter)); t1.setName("1");
		Thread t2 = new Thread (new CounterRunnable(counter)); t2.setName("2");
		Thread t3 = new Thread (new CounterRunnable(counter)); t2.setName("3");

		// uncomment this line to see that synchronization is only on the same object only.
		// t2 = new Thread (new CounterRunnable(new MyCounter())); t2.setName("2");
		t1.start(); 	t2.start();	t3.start();
		t1.join(); 	t2.join();	t3.join();
	}
}

class CounterRunnable implements Runnable {
	private MyCounter counter;

	public CounterRunnable (MyCounter counter) {
	this.counter = counter;
	}

	public void run () {
		if (Thread.currentThread().getName().equals("1"))
			counter.increment();
		else if (Thread.currentThread().getName().equals("2"))
			counter.decrement();
		else 
			counter.nonSynchronized();
	}
}

class MyCounter  {
	public synchronized void increment () {
		System.out.println ("thread 1 : Increment, sleeping ... ");
		try { Thread.sleep(2000);} catch (InterruptedException e) { }
		System.out.println ("thread 1 : Increment, wakeup ... ");
	}

	public synchronized void decrement () {

		try { Thread.sleep(100);} catch (InterruptedException e) { }		
		System.out.println ("thread 2 : Decrement, no sleep");
	}

	public  void nonSynchronized () {
		
		try { Thread.sleep(100);} catch (InterruptedException e) { }
		System.out.println ("thread 3 : I can run anytime");
	}
	
}
