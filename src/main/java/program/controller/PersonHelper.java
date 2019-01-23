package program.controller;

import program.Person;
import program.model.Model;

public class PersonHelper {

	private static PersonHelper personHelper;

	private PersonHelper(){}

	public static PersonHelper getInstance(){
		if (personHelper == null){
			personHelper = new PersonHelper();
		}
		return personHelper;
	}

	Model model = Model.getInstance();


	public void readFile(String path) {
		model.loadFile(path);
		showAtt();

	}

	public void showAtt(){
		System.out.println("id   name           way       USD");
		for (int i = 0; i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				System.out.println(model.getData()[i].toString());//+"\n"
			}
		}
	}

	public void showSpecificPerson(String id){
		System.out.println("id   name           way       USD");
		for (int i = 0; i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				if (model.getData()[i].getId()== Integer.parseInt(id)) {
					System.out.println(model.getData()[i].toString()+ "\n" );
				}
			}
		}
	}

	public Person returnSpecificPerson(String id){

		for (int i = 0; i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				if (model.getData()[i].getId()== Integer.parseInt(id)) {
					return model.getData()[i];
				}
			}
		}
		System.out.println("Персона с данным id не найдена");
		return null;
	}

	public void changePersonParams(int id, String params){
		for (int i=0;i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				if (model.getData()[i].getId()==id){
					String[] p = params.split(",");
					model.getData()[i].setId(Integer.parseInt(p[0]));
					model.getData()[i].setName(p[1]);
					model.getData()[i].setWay(p[2]);
					model.getData()[i].setUSD(Integer.parseInt(p[3]));
					System.out.println(model.getData()[i].toString());
				}
			}
		}
	}

	public void addPerson(int id, String name,String way, int usd){
		int s=0;
		for (int i=0;i<model.getData().length;i++) {
			if (model.getData()[i] == null) {
				if (s!=1){
					model.getData()[i] = new Person(id, name, way, usd);
					s++;
				}
			}
		}
		s=0;
	}

	public String personToLine(Person person){

		String newline = person.id+","+person.name+","+person.way+","+person.USD;

		return newline;
	}
}
