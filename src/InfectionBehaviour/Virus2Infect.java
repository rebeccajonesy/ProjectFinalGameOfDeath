package InfectionBehaviour;

import java.util.Random;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;

import People.Human;
import People.HumanState;

/**
 *  SARS infection method
 * @author Rebecca Jones
 *
 */
public class Virus2Infect implements InfectionMethod {
	double fatality = 9.6;
	@Override
	public void Infect(Human h) {
		Random rand = new Random();
		int maxIncubation = 10;//days
		int minIncubation = 2;
		int incubationPerod = rand.nextInt((maxIncubation - minIncubation) + 1) + minIncubation;
		double fatality = 9.6;
		
		int deathMaxPeriod = 16;
		
		int deathPeriod = rand.nextInt((deathMaxPeriod - incubationPerod) + 1) + deathMaxPeriod;	
		
		// set 
		h.setDeathPeriod(deathPeriod);
		h.setVirusName("SARS");
		h.setrecovery(10);
		h.setFatalityRate(9.6);
		h.setIncubationPeriod(incubationPerod);
		h.setOutput("Symptoms by SARS");
		h.setState(HumanState.State.SYMPTOMS);
	}

	public void setFatality(double d, Human h){
		h.setFatalityRate(d);
	}
	public double getFatality(){
		return fatality;
	}
}
