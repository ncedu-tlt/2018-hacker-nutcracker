package com.netcracker.edu.controller;
import com.netcracker.edu.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVHelper extends AbstractController {

	@Override
	protected String addExtension(String nameFile) {
		if(!nameFile.endsWith(".csv")) {
			nameFile = nameFile + ".csv";
		}
		return nameFile;
	}

	public void createFileCSV(String path, String nameFile, Person person) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(path + "\\" + nameFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "\"id\",\"name\",\"way\",\"USD\"";
		builder.append(ColumnNamesList +"\n");
		builder.append("\"" + person.getId() + "\"" +",");
		builder.append('\"' + person.getName() + '\"' +',');
		builder.append('\"' + person.getWay() + '\"' +',');
		builder.append("\"" + person.getUSD() + "\"");
		builder.append('\n');
		pw.write(builder.toString());
		pw.close();
	}

	public void addPerson(Person person) {
		super.addPerson(person);
	}
}