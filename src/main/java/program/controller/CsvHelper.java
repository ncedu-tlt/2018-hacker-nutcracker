package program.controller;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;

public class CsvHelper {

	private static CsvHelper csvHelper;

	private CsvHelper() {	}

	public static CsvHelper getInstance(){
		if (csvHelper==null){
			csvHelper = new CsvHelper();
		}
		return csvHelper;
	}

	public void saveParams(String filePath, String params)  {
		try {
			if (filePath.lastIndexOf(".csv")==-1) {
				filePath += ".csv";
			}
		PrintWriter deliter = new PrintWriter(filePath);
		deliter.print("");
		deliter.close();

		String firstLine = "id,name,way,USD";

		CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));

		String [] record= params.split(",");

		writer.writeNext(firstLine.split(","));
		writer.writeNext(record);
		writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
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
}
