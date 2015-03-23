package Virus;


public class VirusFactory extends Factory{

	@Override
	public Virus createVirus(String Type) {
		if(Type.equals("Ebola")){
			return new Ebola();
		}
		else
			if(Type.equals("SARS")){
				return new SARS();
			}
				else
					if(Type.equals("Flu")){
						return new Flu();
					}
		throw new IllegalArgumentException("That Type of Virus does not exist");
						
	}


}
