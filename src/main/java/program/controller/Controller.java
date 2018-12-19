package program.controller;

import program.model.CsvHelper;
import program.model.Model;

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

	public void addPerson(){
	}

	public void createCsvFile(String path){
		csvHelper.createFile(path);
	}

	public void newCsvParams(String filePath, String paramLine){
		csvHelper.newParams(filePath,paramLine);
	}

	public void saveCsvLikeXML(String filePath, String paramLine, String saveOrNot){
		csvHelper.saveXML(filePath,paramLine,saveOrNot);
	}

	public void saveCsvLikeJSON(String filePath, String paramLine, String saveOrNot)  {
		csvHelper.saveJSON(filePath,paramLine,saveOrNot);
	}

	public void delete(String path){
			if (new File(path + ".csv").delete()) {
		}
	}

}