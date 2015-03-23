package People;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import InfectionBehaviour.InfectionMethod;
import MovingBehaviour.Move;


public class Person1 extends JPanel implements Human{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x = 0;
	private int y = 0;
	int personAge = 10; 
	public boolean dies = false;
	int boundryLimit = 4; // when a person or virus attempt to go outside the boundry they move 4 pixels in the opposite direction
	private int id = 0;

	private boolean hasSymptoms = false;
	private boolean hadVaccination = false;
	private boolean hasBeeninfected = false;
	private boolean isincubation = false;
	private int stopinfecting = 0;
	public String virusName;
	
	double dirX = 0.5;// move 1 space x
	double dirY= 0.5;// move 1 space y
	
	HumanState.State state = null;
	String output = null;
	int speed = 2;
	double fatalityRate = 0;
	
	int incubationPerod = 0;
	int incubation_interval = 0;//how long
	int deathPeriod = 0;
	
	Color personColor = Color.GREEN;
	Color infectionColor = Color.RED;
	Color symptomColor = Color.ORANGE;	
	Color healthyColor = Color.GREEN;	
	Color immuneColor = Color.yellow;
	int recoversIn = 20;
	int hasSymptomsduration = 20;
	int infectionDuration = 10;
	boolean isrecovered = false;
	int immunity = 0;
	boolean isimmune = false;
	Random rand = new Random();
	
	
	 
	public int infectionDistance = 11; // touch interaction e.g pixels
	
	public boolean  willDie;
	public InfectionMethod infect;
	public Move movingBehaviour;
	public void setrecovery(int i){
		recoversIn = i;
	}
	public int getRecovery(){
		return recoversIn;
	}
	public void setId(int Id){
		id = Id;
	}
	public Person1(){
		x = rand.nextInt(100);
		y = rand.nextInt(60);
		state = HumanState.State.HEALTHY;
		output = "HEALTHY";
		dies = Math.random() < 0.5;// WHO 50%.
		//System.out.println( "Got Created" + " " + "@" + x + " : " + y);
		float chance = rand.nextFloat() * (100 - 1)+1;
		System.out.println(chance);
		if(chance <= 9.6){ // 10% chance of death
			 willDie = true;
		 }
		else
			willDie = false;
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
		if(isimmune){
			immunity = immunity-1;
			if(immunity<=0){
				isimmune = false;
			}
		}
		else
			if(hasSymptoms || hasBeeninfected)// if person  has symtopms then introduce the incubation interval
		{
			stopinfecting --;
			incubation_interval++; 
			if(incubationPerod > 0){	
				// CANNOT INFECT FOR SARS AND EBOLA
				incubationPerod --;
				//}
			}
			
			
			else if(!hadVaccination)// if they are not cured here test // out of incubation should become infected
			{
				hasSymptoms = false;

				setState(HumanState.State.INFECTED);
				hasBeeninfected = true;
				recoversIn --;
				deathPeriod--;
				System.out.println(virusName);
				if(recoversIn == 0){
					if (virusName == "Flu")
					{
						
						if(stopinfecting <= 0){
							//System.out.println(stopinfecting + "Stopped Infecting");
							setState(HumanState.State.RECOVERED);
							isimmune = true;
							immunity=10;
							hasBeeninfected = false;
							
						} // recovered }
						else
						{
							
						}
					}		
					
					else if(deathPeriod > 0){
							//if(daystoDecideFuture == 0){
								deathPeriod--;
								System.out.println(deathPeriod + ""+"id: " +id);
						
							//}
						}
						else if(deathPeriod <=0)
							setState(HumanState.State.DEAD);
						
					}
				
				
				else if(virusName == "Ebola")
				{			
					if(dies)
					{
						
						if(deathPeriod > 0)
						{
							deathPeriod --;
						}
						else if(deathPeriod <=0)
						{
							setState(HumanState.State.DEAD);
						}

					}
					else // Doesent Die // infectious until immune and had vaccination
					{
						if(deathPeriod > 0)
						{
							deathPeriod --;
						}
						else{
							setState(HumanState.State.IMMUNE);
							hadVaccination = true;
						}
					}
				}
			else if(virusName == "SARS")//if virus is SARS
			{
				
				//setState(HumanState.State.SYMPTOMS);
				//isincubation = false;
				hasSymptomsduration--;
				if(hasSymptomsduration<= 0){
					setState(HumanState.State.INFECTED);
					recoversIn--;
					if(recoversIn<= 0){
				
						recoversIn--; // after 10 days they either die or become healthy again 1 in ten people die
				if(willDie){
					setState(HumanState.State.DEAD);
				}
				else{
					setState(HumanState.State.RECOVERED);
					System.out.println("SET STATE TO RECOVERED");
					hasBeeninfected = false;
				}
					}
				}
			}
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
			isrecovered = false;
			hasSymptoms = true;
		}
		else if(s.equals(HumanState.State.INFECTED)){
			setColor(infectionColor);
			setOutput("INFECTED");
			hasBeeninfected = true;
		}
		else if(s.equals(HumanState.State.INCUBATION)){
			setColor(Color.blue);
			setOutput("INCUBATION");
			isincubation= true;
			hasBeeninfected = true;
		}
		else if(s.equals(HumanState.State.IMMUNE)){
			setColor(immuneColor);
		}
		else if(s.equals(HumanState.State.RECOVERED)){
			setColor(Color.green);
			isrecovered = true;
		}
	else if(s.equals(HumanState.State.DEAD)){
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
		return "x:" + x + " y:" + y + "State: " + getState();
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
	public void stopInfecting(int i){
		stopinfecting = i;
	}
	public int getstopInfecting(){
		return stopinfecting;
	}
	@Override
	public boolean isInfected() {
		return this.state == (HumanState.State.INFECTED);
	}

	@Override
	public boolean isHealthy() {
		return this.state == (HumanState.State.HEALTHY);
	}
	@Override
	public boolean isImmune() {
		return this.state == (HumanState.State.IMMUNE);
	}
	public boolean hasRecovered(){
		return this.state == (HumanState.State.RECOVERED);
		
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


	public void onDeath() {
	}

	public int getInfectionDist(){
		return infectionDistance;
	}
	
	@Override
	public void infect(Human h) {
		this.infect.Infect(h);
		repaint();
		
	}
	@Override
	public void setVirusName(String s) {
		virusName = s;
	}
	@Override
	public String getVirusName() {
		return virusName;
	}
	@Override
	public boolean isIcubationPeriod() {
		return isincubation;
	}
	@Override
	public void setFatalityRate(double d) {
		fatalityRate = d;
		
	}
	@Override
	public boolean isrecovered() {
		return isrecovered;
	}



}
