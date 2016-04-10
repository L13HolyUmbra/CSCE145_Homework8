//import
import javax.swing.JOptionPane;

/**
 * 
 * @author Dion de Jong
 * @version November 14, 2013
 * Create the exception for if the user inputs a negative 
 * value. 
 */

//main class, must extend exception
public class NegativeParameterException extends Exception{
	
	//constructor
	public NegativeParameterException()
	{
		//inherits the super and says what happens if the exception occurs. 
		super(); 
		JOptionPane.showMessageDialog(null, "No negative parameters!");
	}

}
