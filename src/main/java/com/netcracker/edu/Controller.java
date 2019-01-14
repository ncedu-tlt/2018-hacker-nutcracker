package com.netcracker.edu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Controller {

	private Model model = new Model();
	private HashMap<String, Person> map = model.getMap();

	/**
	 * Метод парсит все файлы из принятой директории
	 * @param path путь до директории
	 * @return возвращает true для того, чтобы данный метод не вызывался больше 1 раза
	 */

	public boolean parseFiles(String path) {
		File parent = new File(path);
		String[] nameFiles = parent.list();

		for (String nameFile : nameFiles) {
			File file = new File(path + "\\" + nameFile);
			Person person = new Person();
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
				Unmarshaller un = jaxbContext.createUnmarshaller();

				person = (Person) un.unmarshal(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			map.put(nameFile, person);
			model.setMap(map);
		}
		return true;
	}

	/**
	 * Метод создает файл по принимаемым параметрам, используя метод createNewElementInFile() для создания элементов
	 * @param path путь до директории
	 * @param nameFile имя файла
	 * @param person объект Person, в котором содержатся  параметры для создания нового файла
	 */
	public void createFile(String path, String nameFile, Person person) {

		File file = new File(path + "\\" + nameFile);
		try {
			JAXBContext context = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(person, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		map.put(nameFile, person);
		model.setMap(map);
	}

	/**
	 * Метод проверяет наличие файла в коллекции
	 * @param nameFile имя файла
	 * @return возвращает true - если файл имеется в коллекции, введенной в view.pasreAllFilesInDirectory()
	 *                    false - если файл отсутствует в коллекции, введенной в view.pasreAllFilesInDirectory()
	 */
	public boolean checkFileInMap(String nameFile){
		return map.containsKey(nameFile);
	}

	/**
	 * Метод изменяет файл по принимаемым параметрам
	 * @param path путь до директории
	 * @param nameFile имя файла
	 * @param person объект Person, в котором содержатся  параметры для изменения файла
	 * @throws IOException
	 */
	public void changeFile(String path, String nameFile, Person person) {

		File file = new File(path + "\\" + nameFile);
		try {
			JAXBContext context = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(person, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		map.put(nameFile, person);
		model.setMap(map);
	}

	/**
	 * Метод удаляет файл из директории и Hashmap
	 * @param path путь до директории
	 * @param nameFile имя файла
	 * @return возвращает результат выполнения операции
	 */
	public boolean deleteFile(String path, String nameFile){
		File file = new File(path + "\\" + nameFile);
		map.remove(nameFile);
		return file.delete();
	}

	/**
	 * Метод передает HashMap из Model во View для вывода на экран
	 */
	public HashMap<String, Person> getAllPerson(){
		return model.getMap();
	}

	/**
	 * Метод проверяет наличие в файле окончания .xml
	 * @param nameFile имя файла
	 * @return имя файла, с окончанием .xml, если его не было
	 */
	public String checkNameFile(String nameFile){
		boolean endsWith = nameFile.endsWith(".xml");
		if(!endsWith) {
			nameFile = nameFile + ".xml";
		}
		return nameFile;
	}
}