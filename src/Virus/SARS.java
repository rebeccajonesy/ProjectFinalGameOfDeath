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
 * SARS - 1 in ten people die of sars 
 * 1 in 25 people die if 250 people
 *
 */
public class SARS extends JPanel implements Virus {

	/**
	 * the incubation period for  this virus is between 2 and 10 days
	 * 
	 */
	Random rand = new Random();
	int x = 0;
	int y = 0;
	
	int dirX = 1;
	int dirY = 1;
	
	Color pnemoniaColor = Color.red;
	Color FinalSymptomColor = Color.orange;
	Color FirstsymptomColor = Color.YELLOW;
	
	public InfectionMethod infect;
	
	double fatalityrate = 9.6;
	
	public int infectionDistance = 15;//Up to 3 feet, can be spread from touching objects a person has touched. NOTE this is alot larger than the infectionDistance
	
	
	@Override
	public int getInfectionDist(){
		return infectionDistance;
	}



	public void setInfectionMethod(InfectionMethod i){
		this.infect = i;	
	}

	public InfectionMethod getInfectionMethod(){
		return this.infect;
	}
	

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void setx(int newx){
		x = newx;
	}
	@Override
	public void sety(int newy){
		y = newy;
	}

	@Override
	public void infect(Human h) {
		this.infect.Infect(h);
		repaint();		
	}

	@Override
	public void paint(Graphics g) {
    	g.setColor(FirstsymptomColor);
    	g.fillOval(x* 10 , y* 10, personWidth, personheight);
    	g.setColor(FirstsymptomColor);
    	//g.drawString("VIRUS!! x:" + x * 10+ ", Y:" + y* 10, x* 10, y* 10);
	
	}
}