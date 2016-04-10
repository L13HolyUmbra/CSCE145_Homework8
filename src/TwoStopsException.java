//import
import javax.swing.JOptionPane;

/**
 * 
 * @author Dion de Jong
 * @version November 14, 2013
 * Create the exception for is the user requests
 * to add another stop if they have just taken one 
 * and have not done another leg of their trip. 
 */

//main class, must extend exception
public class TwoStopsException extends Exception{
	
	//constructor
	public TwoStopsException()
	{
		//inherits the super and says what happens if the exception occurs. 
		super(); 
		JOptionPane.showMessageDialog(null, "You can't take two stops in a row.");
	}

}

