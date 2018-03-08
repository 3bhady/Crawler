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

public class example_8 {
	public static void main (String [] args) throws InterruptedException {
		MyCounter counter = new MyCounter();

		Thread t1 = new Thread (new CounterRunnable(counter)); t1.setName("1");
		Thread t2 = new Thread (new CounterRunnable(counter)); t2.setName("2");

		t1.start();  t2.start();
		t1.join();   t2.join();
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
		else
			counter.decrement();
		
	}
}

class MyCounter  {
	private Object o1 = new Object();
	private Object o2 = new Object();

	public void increment () {
		synchronized (o1) {
			System.out.println ("Increment, sleeping ... ");
			try { 
				Thread.sleep(2000);
			 } catch (InterruptedException e) { }

			System.out.println ("Increment, wakeup ... ");
		}
	}
	public void decrement () {
		//why it is n't synchronized??
		synchronized (o2) {
			try { 
				Thread.sleep(100);
			 } catch (InterruptedException e) { }

			System.out.println ("Decrement, no sleep");
		}
	}
	
}
