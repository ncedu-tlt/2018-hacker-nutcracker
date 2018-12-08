package controller;

import au.com.bytecode.opencsv.CSVWriter;
import model.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Controller {
	private Model model = new Model();

	public void readFile(String path) throws IOException {
		model.loadFile(path);
		showAtt();
	}

	public void showAtt(){
		System.out.println("id    name       way     USD");
		model.getData();

		if (Model.class.getModule()!= null) {
			System.out.println(Arrays.toString(model.getData()).replace("[","").replace("]","") + "\n");
		}
	}

	public void createFile(String line) throws IOException {

		CSVWriter writer = new CSVWriter(new FileWriter(line));
		String [] record = "id,name,way,USD".split(",");
		writer.writeNext(record);
		writer.close();
		System.out.println("Файл бы успешно создан\n");
	}

	public void newParams(String filePath, String paramLine) throws IOException {
		PrintWriter deliter = new PrintWriter(filePath);
		deliter.print("");
		deliter.close();

		String firstLine = "id,name,way,USD";

		CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));

		String [] record= paramLine.split(",");

		writer.writeNext(firstLine.split(","));
		writer.writeNext(record);
		writer.close();
	}

	public void delete(String line){
		if(new File(line).delete()) {
			System.out.println("Файл удален");
		}
	}
}