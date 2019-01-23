package com.netcracker.edu;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

	public Person parsingFile(String path, String nameFile){
		File file = new File(path + "\\" + nameFile);
		Person person = new Person();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			Unmarshaller un = jaxbContext.createUnmarshaller();

			person = (Person) un.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return person;
	}

	public void saveXML(String path, String nameFile, Person person) {
		try {
			JAXBContext context = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(person, new File(path+"\\"+nameFile));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void saveCSV(String path, String nameFile, Person person) {
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

	public void saveJSON(String path, String nameFile, Person person) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(path + "\\" + nameFile), person);
	}

	public String printPerson(ResultSet result) throws SQLException {
		Person person = new Person();
		String pers="";
		while(result.next()) {
			person.setId(result.getInt(1));
			person.setName(result.getString(2));
			person.setWay(result.getString(3));
			person.setUSD(result.getInt(4));
			pers+=(person.toString() + "\n");
		}
		return pers;
	}

	public Integer theWayId(ResultSet result) throws SQLException {
		Integer wayId=null;
		while(result.next()) {
			wayId = (result.getInt(1));
		}
		return wayId;
	}

	public boolean isExistPersonId (ResultSet result, Integer id) throws SQLException {
		Integer persId;
		while (result.next()) {
			persId = result.getInt(1);
			if (id.equals(persId)){
				return true;
			}
		}
		return false;
	}
}