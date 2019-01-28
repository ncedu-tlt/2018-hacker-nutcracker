package program.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import program.Person;
import program.controller.PersonInterface;
import program.model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JSONHelper implements PersonInterface {

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

	@Override
	public void takePersonFromFile(String path) {

	}


	@Override
	public void deletePerson(String id) {

	}

	@Override
	public void createPerson(String params) {

	}

	@Override
	public void updatePerson(String id, String params) {

	}

	@Override
	public void showAllPersons() {

	}

	@Override
	public Person showOnePerson(String id) {

		return null;
	}

}
