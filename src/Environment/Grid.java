package Environment;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import InfectionBehaviour.Virus2Infect;
import InfectionBehaviour.VirusFluInfect;
import InfectionBehaviour.VirusInfect;
import MovingBehaviour.MoveInBoundries;
import MovingBehaviour.MoveInDonut;
import People.Human;
import People.HumanState;
import People.PersonFactory;
import Virus.Virus;
import Virus.VirusFactory;

/**
 * This class is the core to the project. Where the main focus is on the movement and interactions between the people
 * @author Rebecca Jones
 *
 */
public class Grid extends JPanel implements Runnable 
{

	private static final long serialVersionUID = 1L;
	static OutputWindow outputWindow;
	static MainWindow selector;
	int[][] position;
	public boolean running;
	int x = 0;
	int y = 0;
	Random rand = new Random();
	public String movement;

	public String noOfInfected;
	public String noOfHealthy;
	public String noOfSymptoms;
	public String noOfDead;
	public String noOfimmune;
	public String noOfrecovered;
	public String noinincubation;
	public static int numRows = 60; 
	public static int numCols = 100; // array size
	
	private Vector<Virus> virus;
	private Vector<Human> humans;
	
	public static int cellSize = 10; // 10 px 
	public static int WorldWidth = cellSize * numCols;// number of pixels
	public static int WorldHeight = cellSize * numRows; // number of pixels
	
	
	private PersonFactory humanFactory;
	private VirusFactory virusFactory;
	
	// Initial number in population, can be changed
	int noOfPeople= 550;

	public void setPopulation(int pop){
		noOfPeople = pop;
	}
	
	public void setmove(String move){
		movement = move;
	}
	
	int noOFVirus = 1; // ALWAYS 1.
	
	public String VirusType;
	public String mapType;
	public String dataType;
	
	// Sets the virus type
	public void setVirusType(String virus){
		VirusType = virus;
	}
	// creates the factory to create the people
	public void createHumanFactory(){
	humanFactory = new PersonFactory();	
	}
	// creates the factory in which creates the viruses
	public void createVirusFactory(){
		virusFactory = new VirusFactory();	
	}
	// constructor which requires no parameters
	public Grid(){
		setVirusType("Ebola");
		generateSim(); // Re activate
		
	}

	// constructor which requires a setup which includes the parameters to start simulation and the output window
	public Grid(setup s,OutputWindow out){
		setVirusType(s.getVirus());
		setPopulation(s.getPopulation());
		setmove(s.getMap());
		outputWindow = out; 
		generateSim();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Grid g = new Grid();
		frame.setLayout(new BorderLayout());
		frame.add(g, BorderLayout.CENTER);
		frame.setSize(1300, WorldHeight+50);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle(" A Simulation of a Virus Spread Over The Population");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(outputWindow, BorderLayout.LINE_END);
		g.start(); // starts the runnable
	}
	
	int numberofDays = 0;
	int timer = 0;
	int dayNumber = 0;
	
	public void run() {		
		while(running){
			movePeople();
			checkInteractionWithHuman();
			infectHumanFromHuman();
			repaint();
			update();
			timer++;
			try { 
				Thread.sleep(100);// 60 f ps java
				if(timer%1 == 0){
					numberofDays++;
				}
					if(numberofDays%7== 0){
						dayNumber = numberofDays;
						writetoOutput(dayNumber );
					}
					int sum;
					sum = (Integer.parseInt(noOfimmune) + Integer.parseInt(noOfDead));
					int recovered =Integer.parseInt(noOfrecovered);
					// if all the health population is 0, or if half the population are dead or are immune then stop simulation	
					// when 90% of the population is immune and dead
					int percentForEbola = 90 * noOfPeople / 100;
					
					//int percentForFlu = 4 * noOfPeople / 100;
					//||  recovered >= percentForEbola
					if(sum >= percentForEbola ||  recovered >= percentForEbola || numberofDays>400 ){
					stop();
					writetoOutput(numberofDays);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void start(){
		// set up a thread, to "this"
		Thread thread = new Thread(this);	
		thread.start();
		running = true;
		
	}
	// Writes to the outputwindow, displays statistics
	public void writetoOutput(int dayno){
		System.out.println(Integer.parseInt(getNumberDead())+ "DEAD");
		outputWindow.addTo(dayno,getNumberrecovered(),getNumberWtihIncubation(), getNumberWtihSymptoms(), getNumberInfected(), getNumberDead(), getNumberImmune(), getNumberHealthy());
		System.out.println(getNumberHealthy());
	}
	
	public void stop(){
		running = false;
	}
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	
	// update method is called on each step through the run method
	// circulates the people and initialises the virus
	public void update(){
		//circulateVirus();
		outputWindow.appendText(getNumberInfected(),getNumberrecovered(),getNumberWtihIncubation(),getNumberHealthy(), getNumberWtihSymptoms(), getNumberDead(), getNumberImmune(), numberofDays, dayNumber);		
	}

	// generates the simulation, sets up the virus, people initialised the grid.
	public void generateSim(){
		virus = new Vector<Virus>(50,10);
		humans = new Vector<Human>(50,10);
		createVirusFactory();
		createHumanFactory();
		initialiseGrid();
		deployVirus(VirusType);
		generatePopulation();	
		
	}
	// returns number of infected people,, used for the output statistics
	public String getNumberInfected(){
		int numIfected = 0;	
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isInfected())
			{
				//System.out.println(human.getState());
				numIfected++;
			}	
		}
		noOfInfected = Integer.toString(numIfected);
		return noOfInfected;
	}
	// returns number of people with symptoms, used for the output statistics
	public String getNumberWtihSymptoms(){
		int symptoms = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.hasSymptoms())
				symptoms++;
		
		}
		noOfSymptoms = Integer.toString(symptoms);
		return noOfSymptoms;
	}
	public String getNumberWtihIncubation(){
		int symptoms = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isIcubationPeriod())
				symptoms++;
		
		}
		noinincubation = Integer.toString(symptoms);
		return noinincubation;
	}
	// returns number of healthy people,, used for the output statistics
	public String getNumberHealthy(){
		int healthy = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isHealthy())
				healthy++;
		
		}
		noOfHealthy = Integer.toString(healthy);
		return noOfHealthy;
	}
	// returns number of dead people,, used for the output statistics
	public String getNumberDead(){
		int dead = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isDead())
				dead++;
		
		}
		noOfDead = Integer.toString(dead);
		return noOfDead;
	}
	public String getNumberrecovered(){
		int recovered = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isrecovered())
				recovered++;
		
		}
		noOfrecovered = Integer.toString(recovered);
		//System.out.println(noOfrecovered);
		return noOfrecovered;
		
	}
	public String getNumberImmune(){
		int immune = 0;
		for(int i = 0; i < humans.size(); i ++){
			Human human = (Human)humans.elementAt(i);
			if(human.isImmune())
				immune++;
		
		}
		noOfimmune = Integer.toString(immune);
		return noOfimmune;
	}
	
	// creates the virus using the factory and deploys to grid.
	public void deployVirus(String VirusType){
		
		for(int i = 0; i<noOFVirus; i++) 
		{		
			Virus v;
			v = virusFactory.createVirus(VirusType);	
			virus.addElement(v);

		}
	}

	// checks the virus is within infection distance with human
	public boolean withinInfectionDist(Human h, Virus v){
		int infectionDist = v.getInfectionDist();
		int Vx = v.getX()+5;
		int Vy = v.getY()+5;
		int hx = h.getX()+5;
		int hy = h.getY()+5;
		
		return ( (hx-Vx)*(hx-Vx) + (hy-Vy)*(hy-Vy) <=infectionDist);
		
	}
	// checks the person is within infection distance with an infected human
	public boolean personToPersonContact(Human h, Human other){
		int infectionDist = h.getInfectionDist();
		int Vx = h.getX();
		int Vy = h.getY();
		int hx = other.getX();
		int hy = other.getY();
		
		return ( (hx-Vx)*(hx-Vx) + (hy-Vy)*(hy-Vy) <=infectionDist);
		
	}

	// for each human, check if they are near a virus, if the virus if within the infection distance then the human becomes infected and the virus is destroyed.
	public void checkInteractionWithHuman(){
		for (int h = 0; h < humans.size(); h++){
			Human human = (Human)humans.elementAt(h);
			for(int v = 0; v < virus.size(); v++){
				Virus newVirus = (Virus)virus.elementAt(v);			
				newVirus.setInfectionMethod(new VirusInfect());
				if(withinInfectionDist(human,newVirus)){
					if(human.getState()!= HumanState.State.INFECTED && human.getState()!= HumanState.State.SYMPTOMS&& human.getState()!= HumanState.State.DEAD){
							newVirus.infect(human);
							virus.remove(newVirus);
							System.out.println("Infected Person");
					}
				}
			}
		}
		
	}
	// for each human set the infection method, this was implemented so that multiple viruses can be in one simulation
	// futher work.
	public void checkAroundHuman(Human h){
	
		for (int i = 0; i < humans.size(); i++){
			Human human = (Human)humans.elementAt(i);
			personToPersonContact(h, human);
			
			if(human.getState() != (HumanState.State.SYMPTOMS) && human.getState() != (HumanState.State.INCUBATION) && human.getState() != (HumanState.State.INFECTED ) && human.getState() != (HumanState.State.DEAD ) && human.getState() != (HumanState.State.IMMUNE )  ) // if is not already infected
			{
				if(h.getVirusName()!= null){
					if(VirusType == "Flu"){
						if(personToPersonContact(h,human))
						{
							h.infect(human);
						}
					}
			}
			else
			{
				if(human.getState() != (HumanState.State.RECOVERED)){
			
				if(personToPersonContact(h,human))
				{
					if(h.getVirusName()!= null){
					if(h.getVirusName().equals("Ebola"))
					{
						if(h.getState()!= HumanState.State.SYMPTOMS){ //ebola cannot infect whilst have symptoms
							//h.setFatalityRate(deathFatality);
							h.infect(human);
						}
						else
						{
							
						}
					}
						else if(h.getVirusName().equals("SARS"))
						{
							if(h.getState()!= HumanState.State.SYMPTOMS)
								{
								//istrue = true;
								//System.out.println(istrue);
								System.out.println(h.getState());
								h.infect(human);
								System.out.println(human.getState());
							}
							else
							{
								
							}
							//h.infect(human);
						}
						else if(VirusType == "Flu")
						{
							h.infect(human);
							System.out.println(h.getState());
						}
						}
				}
				}
			}
			}
		}
	}
	
	// this is called and checks the status of each human, if a human is infected then the distance around
	// that human is checked to infect others.
	public void infectHumanFromHuman(){ 
		
		for (int h = 0; h < humans.size(); h++){
			Human human = (Human)humans.elementAt(h);
			if(VirusType == "Ebola"){
				human.setInfectionMethod(new VirusInfect());
			}
			else if(VirusType == "Flu"){
				human.setInfectionMethod(new VirusFluInfect());
				
			}
			else if(VirusType == "SARS"){
				human.setInfectionMethod(new Virus2Infect());
				
			}
			if(human.getState().equals(HumanState.State.INFECTED) || (human.getState().equals(HumanState.State.SYMPTOMS)))
			{
				System.out.println(human.getstopInfecting());
				if(human.getstopInfecting() <= 0)
					checkAroundHuman(human);		
			}
			
		}
			
	}
	// moves the people, depending on the move behaviour. This is also here because in futher work suggestions were made to make people
	// self aware, so their movements could change if infected people were near
	public void movePerson(Human h){
		if(h.getState().equals(HumanState.State.DEAD)){
			return;	
		}
		else
			h.checkInfectionState(); // check the infection state
		if(movement.equals("Fixed")){
			h.setMoveBehaviour((new MoveInBoundries())); // sets the moving behaviour to move within the boundries
									}
			else if(movement.equals("No Boundries"))
			{
					h.setMoveBehaviour((new MoveInDonut()));
				}

			int[] move = h.getMoveBehaviour().getMove();
			int oldPosx = h.getX();
			int oldPosy = h.getY();
			
			h.getMoveBehaviour().movePerson(h,oldPosx+(int)move[0], oldPosy+(int)move[1]);

	}
	
	// method to see if the virus move is valid
	public boolean isValidMovelocation(int x, int y){
		return x>0 && x<numCols && y>0 && y<numRows;
		
	}
	
	/**
	 * Generates a random population of people and infect one person
	 */
	public void generatePopulation(){
		Human human;
		Random r = new Random();
		int infectPersonId = r.nextInt((100 - 1)+1);
		for(int i = 0; i<noOfPeople; i++) 
		{	
			human = humanFactory.createHuman("Person1");	
			human.setId(i);	
			human.setState(HumanState.State.HEALTHY);
			humans.addElement(human);

			if(i == infectPersonId)
			{
				for(int v = 0; v < virus.size(); v++){
					Virus newVirus = (Virus)virus.elementAt(v);	
					if(VirusType == "Ebola"){
						newVirus.setInfectionMethod(new VirusInfect());
					}
					else if(VirusType == "Flu"){
						newVirus.setInfectionMethod(new VirusFluInfect());
						
					}
					else if(VirusType == "SARS"){
						newVirus.setInfectionMethod(new Virus2Infect());
					}					
								newVirus.infect(human);
								virus.remove(newVirus);
				}
			}
		}
		
	}
	//moves the people
	public void movePeople(){
	     for (int i=0; i < humans.size(); i++) {
	            Human newHuman = (Human)humans.elementAt(i);	  
	            movePerson(newHuman);
	        }
	     
	}
	public void paint(Graphics g)
	{	
		super.paint(g);
		g.drawRect(0, 0, WorldWidth, WorldHeight);
		     for (int i=0; i < humans.size(); i++) {
		            Human newHuman = (Human)humans.elementAt(i);
		            newHuman.paint(g);		            
		        }
		     for (int i=0; i < virus.size(); i++) {
		    	 Virus newVirus = (Virus)virus.elementAt(i);
		    	 newVirus.paint(g);	            
		        }
		}

	// initialise the grid with specified parameters
	public void initialiseGrid(){
		position = new int[numCols][numRows];
	}


}
