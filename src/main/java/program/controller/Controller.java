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

	PersonHelper personHelper = PersonHelper.getInstance();

	XMLHelper xmlHelper = XMLHelper.getInstance();

	JSONHelper jsonHelper = JSONHelper.getInstance();

	public void createCsvFile(String path){
		csvHelper.createFile(path);
	}

	public void newCsvParams(String filePath, String params){
		csvHelper.saveParams(filePath,params);
	}

	public void chanchePersonParams(String id, String paramLine){
		personHelper.changePersonParams(Integer.parseInt(id),paramLine);
	}

	public void saveCsvLikeXML(String filePath, String paramLine, String saveOrNot){
		xmlHelper.saveXML(filePath,paramLine,saveOrNot);
	}

	public void saveCsvLikeJSON(String filePath, String paramLine, String saveOrNot)  {
		jsonHelper.saveJSON(filePath,paramLine,saveOrNot);
	}

	public void delete(String path){
			if (new File(path + ".csv").delete()) {
		}
	}
}