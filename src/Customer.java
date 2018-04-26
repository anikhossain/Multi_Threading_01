import java.util.LinkedList;
import java.util.Random;


public  class Customer extends Thread{
	
	public static  boolean gotHelp = false;
	public static volatile LinkedList<Customer> line = new LinkedList<Customer>();
	
	public void run(){
	   long time, time2;
	   Random random = new Random ();
	   time= random.nextInt(9999);          // creating random times to sleep
	   time2 = random.nextInt(9999);
	   try {
		Thread.sleep(time);                // Customer is shopping
		System.out.println(getName()+" browsed at Bala for "+time/1000+ " secnonds");
		notifyClark((Customer) currentThread());       //It tells Floor clerk that this customer needs help
		while(FloorClark.counter<BALA.C);
		gotoCashier();                                // Then Customer goes to Cashier
		dPriority();                                  //Gets the default priority
		Thread.sleep(time2);
		line.add((Customer) currentThread());
		if(time2/1000 >5 && time2/1000 <9){              // if customer gets less heavy Items
			
			System.out.println(getName()+" will take a break for "+time2/1000+" seconds");
			singleHelp();
		}
		else if ((time2/1000)>8){                       //if customer gets very heavy item
		   
		    System.out.println(getName()+" will take a break for "+time2/1000+" seconds");
		    doubleHelp();
		}
		else{                                           // If customer gets light item
			System.out.println(getName()+"'s item is light so s(he) will join other customer to leave.");
		}
		
		if (line.size()==BALA.C && line.size()%2 ==0) leaving2();
		else if (line.size()==BALA.C && line.size()%2 ==1) leaving();
		
		
		
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 }// run
	
	public synchronized void notifyClark( Customer thread) throws InterruptedException{ 
		FloorClark.list.put(thread);                                   // Ask Clerk for Help

	}// notifyClark
	
	public synchronized void gotoCashier(){
		int k =  5+(int)(Math.random() * 10);                       //rushes to cashier by changing priority
		if (k>10) k = k-6; 
		setPriority(k);                                          
		System.out.println(currentThread().getName()+" Going to cashier because priority is "+currentThread().getPriority());
		
	}//goto
	
	public synchronized void dPriority(){                          // gets back default priority
		System.out.println(currentThread().getName()+" get new default priority of 5 from cashier");
		setPriority(5);
	}//dP
	
	public synchronized void doubleHelp() throws InterruptedException{     // More than one Storage Clark helps Customer
		StorageClark sclark1 = (StorageClark) StorageClark.slist.take();
		StorageClark sclark2 = (StorageClark) StorageClark.slist.take();
		System.out.println(sclark1.getName()+" and "+sclark2.getName()+" are helping "+currentThread().getName());
		StorageClark.slist.put(sclark1);
		StorageClark.slist.put(sclark2);
	}//DP
	
	public synchronized void singleHelp() throws InterruptedException{    // Only one customer helps Customer
		StorageClark sclark3 = (StorageClark) StorageClark.slist.take();
		System.out.println(sclark3.getName()+" is helping "+currentThread().getName());
		StorageClark.slist.put(sclark3);
	}
	
	public synchronized void leaving() throws InterruptedException{      // Odd number of Customers Are leaving the store
		while(line.size() > 1){
		   System.out.println(line.removeLast().getName()+" joins "+line.removeLast().getName()+" to leave.");
		}
		System.out.println(line.removeLast().getName()+" Interrupted FloorClark to close the Store.");	
		System.out.println("         BALA IS CLOSED!!!");
	}
	
	public synchronized void leaving2() throws InterruptedException{    // Even Number of Customers Leaving the Store  
		while(line.size()>0){
		   System.out.println(line.removeLast().getName()+" joins "+line.removeLast().getName()+" to leave.");
		}
		System.out.println("Customer Interrupted FloorClark to close the Store.");		
		System.out.println("         BALA IS CLOSED!!!");
	}
}
