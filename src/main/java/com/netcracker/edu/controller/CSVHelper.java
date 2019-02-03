package com.netcracker.edu.controller;
import com.netcracker.edu.Person;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVHelper extends AbstractController {

	public void createFileCSV(Person person) {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filesys.getHomeDirectory()+"\\"+person.getName()+".csv"));
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
}