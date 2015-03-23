package InfectionBehaviour;


import java.util.Random;

import People.Human;
import People.HumanState;
import Virus.Virus;
/**
 * Ebola Virus Infection Method
 * @author Rebecca Jones
 *
 */
public class VirusInfect implements InfectionMethod {


	@Override
	public void Infect(Human h) {
		Random rand = new Random();
		
		int infectionDistance = 7; 
		int maxIncubation = 10;
		int minIncubation = 2;
		int incubationPerod = rand.nextInt((maxIncubation - minIncubation) + 1) + minIncubation;
		
		int deathMaxPeriod = 10; // towards the end of the illness they will either die or become immune, 10 days after being infected
	
		int deathPeriod = rand.nextInt((deathMaxPeriod - incubationPerod) + 1) + deathMaxPeriod;	
		
		// set 
		h.setDeathPeriod(deathPeriod);
		h.setVirusName("Ebola");
		h.setIncubationPeriod(incubationPerod);
		h.setOutput("Symptoms by VIRUS");
		h.setState(HumanState.State.SYMPTOMS);
	}
}
