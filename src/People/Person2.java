package People;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import InfectionBehaviour.InfectionMethod;
import MovingBehaviour.Move;

/**
 * This class is never actually used. But just here to represent the functionality of the factory pattern
 * @author rebecca jones
 *
 */
public class Person2 implements Human{
	private static final long serialVersionUID = 1L;
	private int x = 0;
	private int y = 0;
	int personAge = 10; 
	int boundryLimit = 4; // when a person or virus attempt to go outside the boundry they move 4 pixels in the opposite direction
	private int id = 0;

	private boolean hasSymptoms = false;
	private boolean hadVaccination = false;
	private boolean hasBeeninfected = false;
	
	int dirX = 1;// move 1 space x
	int dirY= 1;// move 1 space y
	
	HumanState.State state = null;
	String output = null;
	int speed = 2;
	
	int incubationPerod = 0;
	int incubation_interval = 0;//how long
	int deathPeriod = 0;
	
	Color personColor = Color.GREEN;
	Color infectionColor = Color.RED;
	Color symptomColor = Color.ORANGE;	
	Color healthyColor = Color.GREEN;	
	Color immuneColor = Color.yellow;
	
	Random rand = new Random();
	 
	public int infectionDistance = 11; // touch interaction e.g pixels
	
	
	public InfectionMethod infect;
	public Move movingBehaviour;
	
	public void setId(int Id){
		id = Id;
	}
	public Person2(){
		x = rand.nextInt(100);
		y = rand.nextInt(60);
		state = HumanState.State.HEALTHY;
		output = "HEALTHY";
		 Random rand = new Random();	
		System.out.println( "Got Created" + " " + "@" + x + " : " + y);
	
		}

	@Override
	public State getState(){
		return state;
	}
	

	@Override
	public void paint(Graphics g){
    	g.setColor(personColor);
    	g.fillOval(x * 10, y * 10, personWidth, personheight); // times by 10 just to paint
    	g.setColor(personColor);
    	//g.drawString(output + "X: "+ x*10 + "Y: " + y*10, x * 10, y * 10);
	}


	public void setDirection() {
		
	}

	int incubation_intervals = 0;
	// (g.isValidMove((x += dirX)+1, y)) <-- checks the direction + 1 because it checks contact from 0,0 so need to +1 (10px) to substitute for the 10px for person and height
	@Override // move for a grid. boundries
	public void checkInfectionState() {
		
		if(hasSymptoms || hasBeeninfected)// if person  has symtopms then introduce the incubation interval
		{
			incubation_interval++; 
			if(incubationPerod > 0){	
				if(incubation_interval > INC_INTERVAL){
					//System.out.println(this.incubationPerod);
					//System.out.println(this.incubation_interval);
				incubationPerod --;
				}
			
			}
			else if(!hadVaccination)// if they are not cured here test // out of incubation should become infected
			{
				hasSymptoms = false;
				
				
				incubation_intervals++;
				setState(HumanState.State.INFECTED);
				hasBeeninfected = true;
			
				if(deathPeriod > 0){
					if(incubation_interval > 2){
						deathPeriod--;
						System.out.println(deathPeriod + ""+"id: " +id);
				
					}
				}
				else if(deathPeriod <=0)
					setState(HumanState.State.DEAD);
				
			}
			else // had vaccination, survival improved
			{
				
			}
		}
		
	}
	
	public boolean isDead(){
		return this.state == HumanState.State.DEAD;
	}
	
	public void setDeathPeriod(int d){
		deathPeriod = d;
	}


	@Override
	public void setState(State s) {
		if(s.equals(HumanState.State.HEALTHY)){
			setColor(healthyColor);
		}
		else if(s.equals(HumanState.State.SYMPTOMS)){
			setColor(symptomColor);
			hasSymptoms = true;
		}
		else if(s.equals(HumanState.State.INFECTED)){
			setColor(infectionColor);
			setOutput("INFECTED");
		}
		else if(s.equals(HumanState.State.IMMUNE)){
			setColor(immuneColor);
		}else if(s.equals(HumanState.State.DEAD)){
			setColor(Color.black);
			setOutput("DEAD");
			onDeath();
			
		}
		this.state = s;	
		
	}


	@Override
	public int getX() {
		return x;
	}


	@Override
	public int getY() {
		return y;
	}

	public void setx(int newx){
		x = newx;
	}
	public void sety(int newy){
		y = newy;
	}

	public void setOutput(String out){
		output = out;
	}

	@Override
	public void setColor(Color c) {
		personColor = c;
		
	}
	
	public String toString(){
		String x = String.valueOf(getX());
		String y = String.valueOf(getY());
		String stateout = getState().toString();
		return "x:" + x + " y:" + y + "State: " + stateout;
	}
	
	public void setInfectionMethod(InfectionMethod i){
		this.infect = i;
	}

	public InfectionMethod getInfectionMethod(){
		return this.infect;
	}

	public void becomeInfected(){
		if(incubationPerod<=0){
			setState(HumanState.State.INFECTED);
		}
	}

	public void setIncubationPeriod(int n){
		incubationPerod = n;
	}
	@Override
	public boolean isInfected() {
		return this.state == (HumanState.State.INFECTED);
	}

	@Override
	public boolean isHealthy() {
		return this.state == (HumanState.State.HEALTHY);
	}

	public boolean hasSymptoms(){
		return this.state == (HumanState.State.SYMPTOMS);
	}
	
	@Override
	public void setMoveBehaviour(Move m) {
		this.movingBehaviour = m;
		
	}

	@Override
	public Move getMoveBehaviour() {
		return movingBehaviour;
	}


	public void animate(){
		if(hasSymptoms){
			if(incubationPerod > 0){
				incubationPerod --;
				return;
			}
			else // out of incubation should become infected
			{
				hasSymptoms = false;
				// keep in bounds
				//move(g);
			}
		}
	}

	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	public int getInfectionDist(){
		return infectionDistance;
	}
	
	@Override
	public void infect(Human h) {
		this.infect.Infect(h);
		//repaint();
		
	}
	@Override
	public void setFatalityRate(double d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setrecovery(int i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stopInfecting(int i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getstopInfecting() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setVirusName(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getVirusName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isIcubationPeriod() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isrecovered() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isImmune() {
		// TODO Auto-generated method stub
		return false;
	}



}
