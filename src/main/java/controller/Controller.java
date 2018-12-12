package controller;

import au.com.bytecode.opencsv.CSVWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import model.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	private  Model model = new Model();

	public void readFile(String path) throws IOException {
		model.loadFile(path);
		showAtt();
	}

	public void showAtt(){
		System.out.println("id    name       way     USD");
		model.getData();
			System.out.println(Arrays.toString(model.getData()).replace("[","").replace("]","") + "\n");
	}

	public void createFile(String line) throws IOException {
		if (line.indexOf(".csv")==0){
			line +=".csv";
		}
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

	public void saveXML(String filePath, String paramLine, String saveOrNot){
		switch (saveOrNot){
			case "n":
			if(new File(filePath+".csv").delete()) {
				System.out.println("Файл удален");
			}
			case "y":

		}
		class Person{
			private int USD;
			private int id;
			private String name;
			private String way;

			public Person(int USD, int id, String name, String way) {
				this.USD = USD;
				this.id = id;
				this.name = name;
				this.way = way;
			}
		}
		String[] params;
		params = paramLine.split(",");
		Person person = new Person(Integer.parseInt(params[3]),Integer.parseInt(params[0]),params[1],params[2]);

		XStream xStream = new XStream(new DomDriver()){
			@Override
			protected void setupConverters() {
			}
		};

		xStream.registerConverter(new ReflectionConverter(xStream.getMapper(), xStream.getReflectionProvider()),
				XStream.PRIORITY_VERY_LOW);
		xStream.registerConverter(new IntConverter(), XStream.PRIORITY_NORMAL);
		xStream.registerConverter(new StringConverter(), XStream.PRIORITY_NORMAL);
		xStream.registerConverter(new CollectionConverter(xStream.getMapper()), XStream.PRIORITY_NORMAL);

		xStream.alias("person",Person.class);

		String information = xStream.toXML(person).replace("<outer-class>","" )
												  .replace("<model/>","")
												  .replace(" </outer-class>","");

		try {
			File f = new File(filePath+".xml");
			OutputStreamWriter fileOut = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			xStream.toXML(information,fileOut);
			System.out.println("Файл в формате .XML был успешно создан!");
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String line){
		if(new File(line+".csv").delete()) {
			System.out.println("Файл удален");
		}
	}


}