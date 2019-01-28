package program.model;

import program.Person;

public class Model {

	private static Model model;

	private Model(){}

	public static Model getInstance(){
		if (model == null){
			model = new Model();
		}
		return model;
	}

	private Person[] data = new Person[100];

	public Person[] getData() {
		return data;
	}

}
