package com.netcracker.edu.controller;
import com.netcracker.edu.Person;

import javax.swing.filechooser.FileSystemView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLHelper extends AbstractController {

	private Person parsingFile(String path, String nameFile){
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

	public void addPerson(Person person) {
		super.addPerson(person);
	}

	public void changePerson(Person person) {
		if (super.isWayExist(person.getWay()) == null){
			super.changePersonInDB(person);
		} else {
			super.changePersonInDB_wayIsExist(person, wayId);
		}
	}

	public void deletePerson(int persId) {
		super.deletePersonInDB(persId);
	}

	public void parseFilesInDir(String path){
		Person person;
		File parent = new File(path);
		String[] nameFiles = parent.list();
		for (String nameFile : nameFiles) {
			if(nameFile.endsWith(".xml")) {
					File file = new File(path +"\\"+nameFile);
					person = parsingFile(path, nameFile);
					addPerson(person);
					file.delete();
			}
		}
	}

	public void parseFile(String path, String nameFile) {
		Person person;
		File file = new File(path +"\\"+nameFile);
		person = parsingFile(path, nameFile);
			if (!super.isPersonIdExist(person.getId())) {
				addPerson(person);
				file.delete();
			}
	}
}