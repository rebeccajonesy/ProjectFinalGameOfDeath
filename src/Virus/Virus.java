package Virus;

import java.awt.Graphics;

import InfectionBehaviour.InfectionMethod;
import MovingBehaviour.Move;
import People.Human;


/**
 *  The interface in which sets what each virus should implement
 * @author Rebecca Jones
 *
 */
public interface Virus {

	// future Work
	public int strength = 0;
	
	int personheight = 5;
	int personWidth = 5;
	
	public int getInfectionDist();
	public void setInfectionMethod(InfectionMethod i);
	public InfectionMethod getInfectionMethod();
// should be float
	public int getX();
	public int getY();
	public void setx(int newx);
	public void sety(int newy);	
	public void infect(Human h);	
	void paint(Graphics g);

}
