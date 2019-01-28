package program.controller;

import program.enumFormat;
import program.helpers.CsvHelper;
import program.helpers.JSONHelper;
import program.helpers.PersonHelper;
import program.helpers.XMLHelper;

import java.io.*;

public class Controller {


	private static Controller controller;

	private Controller() {	}

	public static Controller getInstance(){
		if (controller==null){
			controller = new Controller();
		}
		return controller;
	}

	CsvHelper csvHelper = CsvHelper.getInstance();

	XMLHelper xmlHelper = XMLHelper.getInstance();

	JSONHelper jsonHelper = JSONHelper.getInstance();

	PersonHelper personHelper = PersonHelper.getInstance();

	public void createFile(String path, String format){
		switch (format.toUpperCase()){
			case "CSV":
				csvHelper.createFile(path);
			case "XML":
				xmlHelper.createFile(path);
			case "JSON":
				jsonHelper.createFile(path);
		}
	}


	public void newCsvParams(String filePath, String params,String saveOrNot){
		csvHelper.savePersonToFile(filePath,params,saveOrNot);
	}

	public void chanchePersonParams(String id, String paramLine){
		personHelper.updatePerson(id,paramLine);
	}

	public void saveXML(String filePath, String paramLine, String saveOrNot){
		xmlHelper.savePersonToFile(filePath,paramLine,saveOrNot);
	}

	public void saveJSON(String filePath, String paramLine, String saveOrNot)  {
		jsonHelper.savePersonToFile(filePath,paramLine,saveOrNot);
	}

	public void takePersonFromFile(String path, String format){
		switch (format.toUpperCase()){
			case "CSV":
				csvHelper.takePersonFromFile(path);
			case "XML":
				xmlHelper.takePersonFromFile(path);
			case "JSON":
				jsonHelper.takePersonFromFile(path);
		}
	}

	public void delete(String path){
			if (new File(path + ".csv").delete()) {
		}
	}


}