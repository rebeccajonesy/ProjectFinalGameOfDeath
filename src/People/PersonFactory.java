package People;


public class PersonFactory extends Factory {

	@Override
	public Human createHuman(String selection) {
		if(selection.equalsIgnoreCase("Person1")){
			return new Person1();
		}
		else if (selection.equalsIgnoreCase("Person2")){
			return new Person2();
		}
		throw new IllegalArgumentException("Selection does not exist");
	}
	

}
