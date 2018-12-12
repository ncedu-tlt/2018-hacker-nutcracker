package model;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;


public class Model {

	private String[] data;

	public void loadFile(String path) throws IOException {
		if (path.indexOf(".csv")==0){
			path +=".csv";
		}
		CSVReader csvReader = new CSVReader(new FileReader(path), ',' , '"' , 1);
		data = csvReader.readNext();
	}

	public String[] getData() {
		return data;
	}

}
