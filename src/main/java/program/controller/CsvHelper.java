package program.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import program.Person;
import program.model.Model;

import java.io.*;

public class CsvHelper implements PersonInt{

	private static CsvHelper csvHelper;

	private CsvHelper() {	}

	public static CsvHelper getInstance(){
		if (csvHelper==null){
			csvHelper = new CsvHelper();
		}
		return csvHelper;
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
		try {
			if (path.lastIndexOf(".csv")==-1) {
				path += ".csv";
			}
			CSVReader csvReader = new CSVReader(new FileReader(path), ',' , '"' , 1);

			String[] csv = csvReader.readNext();

			Person person= new Person(Integer.parseInt(csv[0]),csv[1],csv[2],Integer.parseInt(csv[3]));

			if (model.getData()[0]==null){
				model.getData()[0] = person;
			}else {
				model.getData()[IforFile + 1] = person;
				IforFile++;
			}
			showAllPersons();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void savePersonToFile(String path,String params,String saveOrNot) {
		try {
			if (path.lastIndexOf(".csv")==-1) {
				path += ".csv";
			}
			PrintWriter deliter = new PrintWriter(path);
			deliter.print("");
			deliter.close();

			String firstLine = "id,name,way,USD";

			CSVWriter writer = new CSVWriter(new FileWriter(path, true));

			String [] record= params.split(",");

			writer.writeNext(firstLine.split(","));
			writer.writeNext(record);
			writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void createFile(String line)  {
		try {
			if (line.lastIndexOf(".csv")==-1) {
				line += ".csv";
			}
			CSVWriter writer = new CSVWriter(new FileWriter(line));
			String[] record = "id,name,way,USD".split(",");
			writer.writeNext(record);
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
