import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FloorClark extends Thread{
	public static volatile int counter =0; 
	//Using counter to make sure all the customer gets help and then can take rest
	
	public static BlockingQueue<Thread> list = new ArrayBlockingQueue<Thread>(18);
	// This list will make sure First Customer will served first.
	// Also it makes sure that customer will wait for available Floor Clerk

	public void run(){
		while (counter< BALA.C-1){
		   try {
			   Thread.sleep(5000);                  // Initially waits for few customer to get done with The shopping
		    	helpCustomer();                     // Helping Customer
		    	
		   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   
		}//while
		System.out.println(getName()+" is going to take rest"); // Goes to take rest
		
		
	}//run
	
	public synchronized void helpCustomer () throws InterruptedException{
	    Customer customer =(Customer) list.take();
	    System.out.println(getName()+" is helping "+customer.getName());
    	counter++;
    	customer.gotHelp =  true;
	}

}

