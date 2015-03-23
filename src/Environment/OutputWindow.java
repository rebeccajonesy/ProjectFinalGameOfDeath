package Environment;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

/**
 * The class which generates the statistics panel
 * @author Rebecca Jones
 *
 */
public class OutputWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(8,15);
	private JTextArea textArea2 = new JTextArea();
	private Label titleLbl = new Label("Virus Spread Simulation");

	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

	public OutputWindow(){
		Font myfont = new Font("Sefif", Font.BOLD, 12);
		Font TitleFont = new Font("Sefif", Font.BOLD, 15);
		this.setLayout(new GridLayout(3,1));
		this.setSize(500,1000);
	
		// title
		titleLbl.setFont(TitleFont);
		this.add(titleLbl);

		add(textArea);	
		JScrollPane scroll = new JScrollPane(textArea2);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		DefaultCaret caret = (DefaultCaret)textArea2.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		setBackground(Color.gray);

		textArea2.setEditable(false);
		textArea.setEditable(false);
		textArea.setFont(myfont);
		textArea2.setFont(myfont);
		add(scroll);
		
	}
	public void appendText(String infected, String recovered, String incubation, String healthy, String symptoms, String dead, String immune, int numberOfDays, int dayno){
		textArea.setText("Number of infected people: " + infected + "\n" +"People in incubationP: " + incubation +"People Recovered: " + recovered + "\n" + "Number of healthy people: " + healthy + "\n" + "Number of people with symptoms:" + symptoms + "\n" + "Number of dead people: " + dead +"\n"+ "Number of Immune people: " + immune +"\n" +"\n" + "Number of days Passed: " + numberOfDays );		
	}
	
	public void addVirusToSelection(){
		
	}
	
	public void addTo(int str,String recovered,String inc, String noSymptoms ,String noOfInfected, String noOfDead, String immune,String nohealthy){
		textArea2.append("\n" + "Day Number: " + str +"\n"  +"People Recovered: " + recovered + "\n" + "Number With Symptoms: " + noSymptoms + "\n" + "People in incubationP:" + inc + "\n"+ "Number of dead people: " + noOfDead + "\n" + "Number of infected people: " + noOfInfected  + "\n" + "Number of Immune people: " + immune +"\n" + "Number Healthy : " + nohealthy + "\n" );
		
	}
}
