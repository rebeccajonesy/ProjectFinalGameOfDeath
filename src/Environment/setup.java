package Environment;
/**
 * Class which holds the parameters to set the simulation up with.
 * @author rebecca jones
 *
 */
public class setup {
	public String virus;
	public int population;
	public String map;
	public setup(String Virus,int Population, String Map){
		virus = Virus;
		population = Population;
		map = Map;
	}
	public setup(){
		
	}
	public String getVirus() {
		return virus;
	}
	public void setVirus(String virus) {
		this.virus = virus;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	
	

}
