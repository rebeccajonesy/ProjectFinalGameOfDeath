package Virus;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import InfectionBehaviour.InfectionMethod;
import MovingBehaviour.Move;
import People.Human;

/**
 * 
 * @author Rebecca Jones
 * - Virus 1 = Ebola
 */
public class Ebola extends JPanel implements Virus{
	Random rand = new Random();

	private static final long serialVersionUID = 1L;
	int x = 0;
	int y = 0;
	
	int dirX = 1;
	int dirY = 1;

	/**
	 * int will be in days
	 * If the incubation period is between 2 and 12 days for example.
	 * a random number within them days will be generated
	 */ 
	public int infectionDistance = 7; // touch interaction e.g pixels CLOSE CONTACT SMALL VARIABLE
	
	int maxIncubation = 10;//days
	int minIncubation = 2;
	
	int deathMaxPeriod = 16;
	
	Color infectionColor = Color.RED;
	Color symptomColor = Color.ORANGE;
	
	String output = null;
	
	int boundryLimit = 4;
	
	public Ebola(){
		x = rand.nextInt(100);
		y = rand.nextInt(60);
		System.out.println("Got Created" + " " + "@" + x + " : " + y);
	}
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
		public int getInfectionDist(){
		return infectionDistance;
	}
		
	// Instance variable which holds the infection for this virus method at runtime
	public InfectionMethod infect;
	

	//gets the infection method for this virus
	@Override
	public void setInfectionMethod(InfectionMethod i){
		this.infect = i;	
	}
	//gets the infection method for this virus
	@Override
	public InfectionMethod getInfectionMethod(){
		return this.infect;
	}
	
	
	
	public void setx(int newx){
		x = newx;
	}
	public void sety(int newy){
		y = newy;
	}
	
	
	public void update(){
		
	}

	@Override
	public void paint(Graphics g) {
    	g.setColor(infectionColor);
    	g.fillOval(x* 10 , y* 10, personWidth, personheight);
    	g.setColor(infectionColor);
    	//g.drawString("VIRUS!! x:" + x * 10+ ", Y:" + y* 10, x* 10, y* 10);
	}
	
	@Override
	public void infect(Human h) {
		this.infect.Infect(h);
		repaint();		
	}


}
