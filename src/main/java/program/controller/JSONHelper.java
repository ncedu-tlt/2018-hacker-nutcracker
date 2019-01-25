package program.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import program.Person;
import program.model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JSONHelper implements PersonInt{

	private static JSONHelper jsonHelper;

	private JSONHelper(){}

	public static JSONHelper getInstance(){
		if (jsonHelper == null){
			jsonHelper = new JSONHelper();
		}
		return jsonHelper;
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
		switch (saveOrNot.trim()){
			case "n":
				if(new File(path+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}

		String[] param;
		param = params.split(",");
		Person person = new Person(Integer.parseInt(param[0]),param[1],param[2],Integer.parseInt(param[3]));

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			if (path.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(path).reverse().toString();

				rev =rev.replaceFirst("vsc","nosj");
				path = new StringBuffer(rev).reverse().toString();
			}

			mapper.writeValue(new File(path),person);

		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createFile(String path) {
		if (path.lastIndexOf(".json")==-1) {
			path += ".json";
		}
		File file = new File(path);
	}
}
