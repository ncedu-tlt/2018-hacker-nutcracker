package program.controller;

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

	public void createCsvFile(String path){
		csvHelper.createFile(path);
	}
	public void createXmlFile(String path){
		xmlHelper.createFile(path);
	}
	public void createJsonFile(String path){
		jsonHelper.createFile(path);
	}

	public void newCsvParams(String filePath, String params,String saveOrNot){
		csvHelper.savePersonToFile(filePath,params,saveOrNot);
	}

	public void chanchePersonParams(String id, String paramLine){
		csvHelper.updatePerson(id,paramLine);
	}

	public void saveXML(String filePath, String paramLine, String saveOrNot){
		xmlHelper.savePersonToFile(filePath,paramLine,saveOrNot);
	}

	public void saveJSON(String filePath, String paramLine, String saveOrNot)  {
		jsonHelper.savePersonToFile(filePath,paramLine,saveOrNot);
	}

	public void delete(String path){
			if (new File(path + ".csv").delete()) {
		}
	}


}