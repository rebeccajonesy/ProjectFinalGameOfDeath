package Environment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Class which generates the setup screen when the simulation is ran
 * @author rebecca jones
 *
 */
public class MainWindow{
	public String virusType;

    JLabel VirusSelection;

  	 JLabel Map;
  	 JLabel Viewstatus;
  	 
    String[] items = {"Daily", "Weekly", "Monthly"};
  	 JComboBox dataCollection;
  	 String[] Virus = {"Ebola", "Flu", "SARS"};
  	 JComboBox VirusOption;
  	 
  	 String[] mapType = {"Fixed", "No Boundries"};
  	 JComboBox Maps;
  	 
  	String[] population = {"100", "300", "500", "1000", "1500", "2000"};
 	 JComboBox Population;
 
  	 JButton buttonB;
	public static void addComponents(java.awt.Container pane){
		 pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		 addLabels(pane);
			 
	}
	private static void addLabels(Container container) {
     	 
     JLabel VirusSelection = new JLabel("Virus Selection: ");
     VirusSelection.setAlignmentX(Component.RIGHT_ALIGNMENT);
     
   	 JLabel Map = new JLabel("Map Selection: ");
   	 Map.setAlignmentX(Component.RIGHT_ALIGNMENT);
   	 
     JLabel popSelection = new JLabel("Population Selection: ");
     popSelection.setAlignmentX(Component.RIGHT_ALIGNMENT);
     
   	 String[] Virus = {"Ebola", "Flu", "SARS"};
   	 final JComboBox VirusOption = new JComboBox(Virus);
   	 VirusOption.setAlignmentX(Component.LEFT_ALIGNMENT);
   	 
   	 String[] mapType = {"Fixed", "No Boundries"};
   	 final JComboBox Maps = new JComboBox(mapType);
   	 Maps.setAlignmentX(Component.LEFT_ALIGNMENT);
   	 
   	String[] population = {"100", "300", "500", "1000", "1500", "2000"};
  	 final JComboBox Population = new JComboBox(population);
  	 Maps.setAlignmentX(Component.LEFT_ALIGNMENT);
   	 
   	 JButton buttonB = new JButton("Start Simulation");
   	 buttonB.setAlignmentX(Component.CENTER_ALIGNMENT);
   	 buttonB.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String virusType = (String) VirusOption.getSelectedItem();
			String GraphType = (String) Maps.getSelectedItem();
			String population = (String)Population.getSelectedItem();
			int pop = Integer.parseInt(population);
			//String dataView = (String) dataCollection.getSelectedItem();
			
			OutputWindow outputWindow = new OutputWindow();
			
			setup s = new setup(virusType,pop,GraphType);	
			JFrame frame = new JFrame();
			Grid g = new Grid(s,outputWindow); // Virus CHECK, Population , Boundries
			
			frame.setLayout(new BorderLayout());

			frame.add(g, BorderLayout.CENTER);
			frame.setSize(1250, 650);
			frame.setResizable(false);
			
			frame.setVisible(true);
			frame.setTitle(" A Simulation of a Virus Spread Over The Population");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(outputWindow, BorderLayout.LINE_END);
			g.start(); // starts the runnable
		
		}
	});

    container.add(Map);
    container.add(Maps);
    container.add(VirusSelection);
   	container.add(VirusOption);
   	container.add(popSelection);
   	container.add(Population);
  
   	
    container.add(buttonB);
    }
	 
	 
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Setup Screen");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Set up the content pane.
	        addComponents(frame.getContentPane());
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	 public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }

	public String getVirus(){
		return virusType;
	}

}
