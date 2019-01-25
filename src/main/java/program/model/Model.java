package program.model;

import au.com.bytecode.opencsv.CSVReader;
import program.Person;

import java.io.FileReader;
import java.io.IOException;

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
//	int i=0;

//	public void loadFile(String path)  {
//		try {
//			if (path.lastIndexOf(".csv")==-1) {
//				path += ".csv";
//			}
//			CSVReader csvReader = new CSVReader(new FileReader(path), ',' , '"' , 1);
//
//			String[] csv = csvReader.readNext();
//
//			Person person= new Person(Integer.parseInt(csv[0]),csv[1],csv[2],Integer.parseInt(csv[3]));
//
//			if (data[0]==null){
//				data[0] = person;
//			}else {
//				data[i + 1] = person;
//				i++;
//			}
//
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//	}



	public Person[] getData() {
		return data;
	}

}
