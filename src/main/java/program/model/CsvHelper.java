package program.model;

import au.com.bytecode.opencsv.CSVWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import program.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.*;
import java.util.Arrays;

public class CsvHelper {

	private static CsvHelper csvHelper;

	private CsvHelper() {	}

	public static CsvHelper getInstance(){
		if (csvHelper==null){
			csvHelper = new CsvHelper();
		}
		return csvHelper;
	}

	Model model = new Model();

	public void readFile(String path) {
		model.loadFile(path);
		showAtt();
	}

	public void showAtt(){
		System.out.println("id    name       way     USD");
		model.getData();
		System.out.println(Arrays.toString(model.getData()).replace("[","").replace("]","") + "\n");
	}



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

	public void newParams(String filePath, String paramLine)  {
		try {
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
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void saveXML(String filePath, String paramLine, String saveOrNot){
		switch (saveOrNot.trim()){
			case "n":
				if(new File(filePath+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}
		String[] params;
		params = paramLine.split(",");
		Person person = new Person(Integer.parseInt(params[0]),params[1],params[2],Integer.parseInt(params[3]));
		try {

			JAXBContext context = JAXBContext.newInstance(Person.class);

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			JAXBElement<Person> jaxbElement = new JAXBElement<>(new QName("","person"),Person.class,person);

			if (filePath.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(filePath).reverse().toString();

				rev =rev.replaceFirst("vsc","lmx");
				filePath = new StringBuffer(rev).reverse().toString();
			}

			marshaller.marshal(jaxbElement,new File(filePath));


		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void saveJSON(String filePath, String paramLine, String saveOrNot)  {

		switch (saveOrNot.trim()){
			case "n":
				if(new File(filePath+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}

		String[] params;
		params = paramLine.split(",");
		Person person = new Person(Integer.parseInt(params[0]),params[1],params[2],Integer.parseInt(params[3]));

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			if (filePath.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(filePath).reverse().toString();

				rev =rev.replaceFirst("vsc","nosj");
				filePath = new StringBuffer(rev).reverse().toString();
			}

			mapper.writeValue(new File(filePath),person);

		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
