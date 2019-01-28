package program.helpers;

import program.Person;
import program.controller.PersonInterface;
import program.model.Model;

public class PersonHelper implements PersonInterface {

	private static PersonHelper personHelper;

	private PersonHelper(){}

	public static PersonHelper getInstance(){
		if (personHelper == null){
			personHelper = new PersonHelper();
		}
		return personHelper;
	}

	Model model = Model.getInstance();

	private  int IforFile =0;

	@Override
	public void deletePerson(String id) {
		int pid = Integer.parseInt(id);
		for(int i = 0; i< model.getData().length; i++){
			if (model.getData()[i].getId() == pid){
				model.getData()[i]=null;
			}
		}
	}

	@Override
	public void createPerson(String params) {
		String record[] = params.split(",");

		int s=0;
		for (int i=0;i<model.getData().length;i++) {
			if (model.getData()[i] == null) {
				if (s!=1){
					model.getData()[i] = new Person(Integer.parseInt(record[0]), record[1], record[2],
							Integer.parseInt(record[3]));
					s++;
				}
			}
		}
		s=0;
	}

	@Override
	public void updatePerson(String id, String params) {
		for (int i=0;i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				if (model.getData()[i].getId() == Integer.parseInt(id)){
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

	@Override
	public void showAllPersons() {
		System.out.println("id   name           way       USD");
		for (int i = 0; i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				System.out.println(model.getData()[i].toString());
			}
		}
	}

	@Override
	public Person showOnePerson(String id) {
		for (int i = 0; i<model.getData().length;i++) {
			if (model.getData()[i]!=null) {
				if (model.getData()[i].getId()== Integer.parseInt(id)) {
					return model.getData()[i];
				}
			}
		}
		return null;
	}


	@Override
	public void takePersonFromFile(String path) {

	}

	@Override
	public void savePersonToFile(String path, String params, String saveOrNot) {

	}

	@Override
	public void createFile(String path) {

	}
}
