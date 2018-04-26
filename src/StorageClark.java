import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class StorageClark extends Thread{
	
	public static BlockingQueue<Thread> slist = new ArrayBlockingQueue<Thread>(4);
	
	public void run(){
		try {
			Thread.sleep(5000);               // Each Storage Clerk Helps Customer for 5 seconds
			slist.put(currentThread());
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
