package People;

import java.awt.Color;
import java.awt.Graphics;

import Environment.Grid;
import InfectionBehaviour.InfectionMethod;
import MovingBehaviour.Move;
import People.HumanState.State;
import Virus.Virus;

public interface Human extends HumanState {

	public int numRows = 60; // array size
	public int numCols = 60; // array size
	
	public int gridSizeX = 1000; // was 1000
	public int gridSizeY = 600;
	
	int personheight = 5; // WAS 10
	int personWidth = 5; // WAS 10
	int immuneSystem = 10; // randomly give a value for this, If the immune system is less than 5 got a smaller chance of survival
	
	public int INC_INTERVAL = 100; // timeout before begins
	
	// behaviours
	public void setFatalityRate(double d);
	public void setDeathPeriod(int d);
	public void setMoveBehaviour(Move m);
	public void setrecovery(int i);
	public Move getMoveBehaviour();
	public void setInfectionMethod(InfectionMethod i);
	public InfectionMethod getInfectionMethod();
	public void setId(int Id);
	public void setIncubationPeriod(int n);
	public void setOutput(String out);
	public void stopInfecting(int i);
	public int getstopInfecting();
	public void setVirusName(String s);
	public String getVirusName();
	public int getX();
	public void setx(int newx);
	public void sety(int newy);
	public int getY();
	public void onDeath();
	public void setDirection();
	public void setColor(Color c);
	public void checkInfectionState();
	public State getState();
	public boolean isInfected();
	public boolean isIcubationPeriod();
	public boolean isHealthy();
	public boolean isrecovered();
	public boolean isDead();
	public boolean hasSymptoms();
	public void setState(State s);
	void paint(Graphics g);
	void infect(Human h);
	public int getInfectionDist();
	boolean isImmune();
	
}

