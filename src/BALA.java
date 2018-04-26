/*  Tanvir Hossain
 *  Fall 2017(tu-fri, 3:10PM)
 * 
 * This is the Class where I start Threads
 */
import javax.swing.JOptionPane;

public class BALA {
	public static int C = 0;
	
	public static void main(String[] args ) throws InterruptedException{
		int c;
		int f,s;
		
		// I take the inputs by the JOPtion Pane
		c = Integer.parseInt(JOptionPane.showInputDialog("Please Enter number of customers..."));
		C=c;
		f = Integer.parseInt(JOptionPane.showInputDialog("Please Enter number of Floor Clerks..."));
		s = Integer.parseInt(JOptionPane.showInputDialog("Please Enter number of Storage Clerks..."));
		// Storage Clark Number has to be more than one.
		
		for (int l =1; l< c+1; l++){            //creating Customers
			Customer c_ = new Customer();
			c_.setName("Customer #"+ Integer.toString(l));
			c_.start();
		}
		
		for (int i =1; i< f+1; i++){             //Creating FloorClerks
			FloorClark f_ = new FloorClark();
			f_.setName("Floor_Clerk #"+ Integer.toString(i));
			f_.start();
		}
			
		for (int k =1; k< s+1; k++){              // Creating Storage Clerks
			StorageClark s_ = new StorageClark();
			s_.setName("Storage_Clerk #"+ Integer.toString(k));
			s_.start();
		}	
			
			
	}

}
