package program.model;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;


public class Model {

	private String[] data;
	
	public void loadFile(String path)  {
		try {
			if (path.lastIndexOf(".csv")==-1) {
				path += ".csv";
			}
		CSVReader csvReader = new CSVReader(new FileReader(path), ',' , '"' , 1);
		data = csvReader.readNext();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public String[] getData() {
		return data;
	}



}
