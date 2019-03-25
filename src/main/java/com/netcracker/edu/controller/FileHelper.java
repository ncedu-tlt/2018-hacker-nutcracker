package com.netcracker.edu.controller;

import com.google.gson.Gson;
import com.netcracker.edu.model.dto.PersonDto;
import org.springframework.stereotype.Component;

import javax.swing.filechooser.FileSystemView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

@Component
public class FileHelper {

	public void createFileXML (PersonDto person){
		FileSystemView filesys = FileSystemView.getFileSystemView();
		try {
			JAXBContext context = JAXBContext.newInstance(PersonDto.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(person, new File(filesys.getHomeDirectory()+"\\"+person.getName()+".xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void createFileJSON(PersonDto person) {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		Gson gson = new Gson();
		String json = gson.toJson(person);
		try {
			FileWriter fw = new FileWriter(filesys.getHomeDirectory()+"\\"+person.getName()+".json");
			fw.write(json);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createFileCSV(PersonDto person) {
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
