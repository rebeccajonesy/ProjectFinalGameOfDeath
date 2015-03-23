package People;
/**
 * Enumeration for the state of the human
 * @author rebecca jones
 *
 */
public interface HumanState {
	// States of people
	public static enum State{
		INFECTED, HEALTHY, IMMUNE, SYMPTOMS, DEAD, CARRIER, INCUBATION, RECOVERED
	}

}
