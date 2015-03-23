package InfectionBehaviour;

import java.util.Random;

import People.Human;
import People.HumanState;

public class VirusFluInfect implements InfectionMethod {
//Flu is hard to contain because it spreads from person to person in as little as three days, often before the first person feels ill.
	@Override
	public void Infect(Human h) {
		Random rand = new Random();
		
		int maxIncubation = 4;//days
		int minIncubation = 1;
		int incubationPerod = rand.nextInt((maxIncubation - minIncubation) + 1) + minIncubation;
		
		int canInfectOthers = incubationPerod - 1; // can effect others one day before symptoms appear
		// can infect 1 day before symptoms to 5 to 7 days after symptoms
		int stopInfecting = canInfectOthers + 5; // assume is 5
		//int infectionDistance = 20; // coughing and sneezing
		
		// set 
		h.setVirusName("Flu");
		h.setrecovery(8);
		h.setIncubationPeriod(canInfectOthers);
		h.stopInfecting(stopInfecting);
		h.setOutput("Symptoms by FLU");
		h.setState(HumanState.State.SYMPTOMS);
	}

}
