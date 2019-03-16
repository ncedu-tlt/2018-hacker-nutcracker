package com.netcracker.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.Person;

import javax.swing.filechooser.FileSystemView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHelper {

	public void createFileXML (Person person){
		FileSystemView filesys = FileSystemView.getFileSystemView();
		try {
			JAXBContext context = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(person, new File(filesys.getHomeDirectory()+"\\"+person.getName()+".xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void createFileJSON(Person person) {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(filesys.getHomeDirectory()+"\\"+person.getName()+".json"), person);
		}catch (IOException e) {e.printStackTrace();}
	}

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
