//A lot of imports
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * @author Dion de Jong
 * @version November 14, 2013
 * Create a calculator for calculating how long a
 * particular trip will take. It can account for rest
 * stops and can calculate by legs of trip. It should throw
 * exceptions if the user attempts to make two rest stops in a row or 
 * types a negative time. 
 */

//main class
public class TripComputer extends JFrame implements ActionListener{
	//initialize all of the main things you will need
	private JButton newLeg;
	private JButton newStop;
	private JFormattedTextField Display; 
	private JLabel DisplayLabel;
	private JFormattedTextField Distance; 
	private JLabel DistanceLabel;
	private JFormattedTextField Speed;
	private JLabel SpeedLabel;
	private JFormattedTextField StopTime;
	private JLabel StopTimeLabel;

	//instruction required instance variables
	private double totalTime = 0; 
	private boolean restStopTaken = false;

	//constructor 
	public TripComputer()
	{
		super("Trip Calculator");
		this.setSize(500,500);
		this.initialize();
		this.setVisible(true); 	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//Legtime computation method. Receives the parameters of how fast people are going and for how far and determines 
	//how long it will take and adds that to the total time. 
	public double computeLegTime (double distance, double speed) throws NegativeParameterException
	{
		//if the parameter is negative throw a negative parameter exception created in a separate class. 
		if (distance < 0 || speed < 0)
		{
			throw new NegativeParameterException(); 
		}
		double LegTime = distance/speed; 
		totalTime += LegTime;
		restStopTaken = false;
		return LegTime; 

	}

	//adds the time the user intends to take on a rest stop to the total time
	public void takeRestStop (double time) throws NegativeParameterException, TwoStopsException
	{
		//if the time is negative or the user has just taken a stop without another leg, throw an exception
		if (time < 0)
		{
			throw new NegativeParameterException(); 
		}

		if (restStopTaken == true)
		{
			throw new TwoStopsException(); 
		}
		totalTime += time; 
		restStopTaken = true; 
	}

	//Getter for trip time
	public double getTripTime()
	{
		return totalTime;
	}

	//Create the jpanels and everything held in them (define how to create the calculator object)
	public void initialize()
	{
		//Create the content pane that will hold everything.
		Container c = this.getContentPane(); 
		c.setLayout(null);
		c.setBackground(Color.pink);
		c.setForeground(new Color(0,0,0));
		c.setVisible(true);

		//create the panel that holds the buttons, it sits inside your content pane. 
		JPanel ButtonPanel = new JPanel(); 
		//ButtonPanel.setBounds(0, 0, 500, 200);
		ButtonPanel.setLayout(new FlowLayout()); 
		ButtonPanel.setVisible(true); 
		ButtonPanel.setBackground(Color.PINK);
		c.add(ButtonPanel); 

		//create the add a new leg button
		this.newLeg = new JButton();
		this.newLeg.setBounds(280, 75, 150, 50);
		this.newLeg.setActionCommand("newLeg");
		this.newLeg.addActionListener(this);
		this.newLeg.setText("Add New Leg");
		c.add(newLeg); 
		//newLeg.setVisible(true);

		//Create the new stop button
		this.newStop = new JButton();
		this.newStop.setBounds(80, 75, 150, 50);
		this.newStop.setActionCommand("newStop");
		this.newStop.addActionListener(this);
		this.newStop.setText("Add New Stop");
		c.add(newStop); 
		newStop.setVisible(true);

		//create the first half panel that will hold the display and stop time input
		JPanel JP1 = new JPanel(); 
		JP1.setBounds(0, 200, 250, 500);
		JP1.setLayout(null); 
		JP1.setVisible(true);
		JP1.setBackground(Color.MAGENTA);
		c.add(JP1);

		//displays the total trip time. CANT BE EDITED
		this.Display = new JFormattedTextField();
		this.Display.setColumns(1);
		this.Display.setBounds(90, 360, 75, 35);
		this.Display.setValue(totalTime);
		this.Display.setVisible(true);
		this.Display.setEditable(false);
		c.add(Display); 
		
		//Label for display
		this.DisplayLabel = new JLabel();
		this.DisplayLabel.setText("Estimated trip time:");
		this.DisplayLabel.setBounds(55,120,800,50); 
		DisplayLabel.setVisible(true); 
		JP1.add(DisplayLabel);

		//input field for how long a stop will take
		this.StopTime = new JFormattedTextField();
		this.StopTime.setColumns(1);
		this.StopTime.setBounds(90, 270, 75, 35);
		this.StopTime.setText("Stop Time");
		this.StopTime.setVisible(true);
		c.add(StopTime); 

		//label for editing stop time for a rest stop
		this.StopTimeLabel = new JLabel();
		this.StopTimeLabel.setText("Amount of time in hours for stop.");
		this.StopTimeLabel.setBounds(20,35,800,50); 
		StopTimeLabel.setVisible(true); 
		JP1.add(StopTimeLabel);

		//create a second panel that will hold the input for the leg distance and speed.
		JPanel JP2 = new JPanel(); 
		JP2.setBounds(250, 200, 250, 500);
		JP2.setLayout(null); 
		JP2.setVisible(true);
		JP2.setBackground(Color.MAGENTA);
		c.add(JP2);

		//Input field for the distance of the next leg
		this.Distance = new JFormattedTextField();
		this.Distance.setColumns(1);
		this.Distance.setBounds(335, 270, 75, 35);
		this.Distance.setText("Distance");
		this.Distance.setVisible(true);
		c.add(Distance); 

		//label for the distance input
		this.DistanceLabel = new JLabel();
		this.DistanceLabel.setText("Distance of your Leg (in MPH).");
		this.DistanceLabel.setBounds(50,10,800,100);
		this.DistanceLabel.setVisible(true); 
		JP2.add(DistanceLabel);

		//Input field for the speed of the next leg
		this.Speed = new JFormattedTextField();
		this.Speed.setColumns(1);
		this.Speed.setBounds(335, 360, 75, 35);
		this.Speed.setText("Speed"); 
		//this.Speed.setValue(new Double(SpeedValue));
		this.Speed.setVisible(true);
		c.add(Speed); 

		//Label for speed input
		this.SpeedLabel = new JLabel();
		this.SpeedLabel.setText("Predicted Speed for leg (in MPH).");
		this.SpeedLabel.setBounds(45,100,800,100);
		this.SpeedLabel.setVisible(true); 
		JP2.add(SpeedLabel);

	}

	//what happens when the user presses the two buttons is defined below. 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String source = e.getActionCommand();		

		//if new leg is pressed
		if(source.equals("newLeg"))
		{
			//parse the values the user has entered in the appropriate fields to doubles
			String SpeedString = Speed.getText(); 
			double DubSpeed = Double.parseDouble(SpeedString); 
			String DistanceString = Distance.getText(); 
			double DubDistance = Double.parseDouble(DistanceString); 

			//try to get the leg time,
			try 
			{

				this.computeLegTime(DubDistance, DubSpeed);
			} 
			//unless the parameter was negative.
			catch (NegativeParameterException e1)
			{
				e1.printStackTrace();
			}
		}

		//if the user presses stop
		if(source.equals("newStop"))
		{
			//parse time to a double
			String StopTimeString = StopTime.getText(); 
			double DubStopTime = Double.parseDouble(StopTimeString); 
			
			//add it to the total time with the add stop method, 
			try
			{
				this.takeRestStop(DubStopTime);
			} 
			//unless the parameter is negative or the user has just created a stop without a new leg. 
			catch (NegativeParameterException | TwoStopsException e1) 
			{
				e1.printStackTrace();
			} 

		}

		//display the new total time in the display field (must parse to string). 
		this.Display.setText(Double.toString(getTripTime()));

	}
	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//create an object of the calculator to be use. 
		TripComputer Calc = new TripComputer(); 
	}
}
