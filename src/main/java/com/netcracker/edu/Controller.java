package com.netcracker.edu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class Controller {

	private Model model = new Model();
	private HashMap<String, Person> map = model.getMap();

	/**
	 * Метод парсит все файлы из принятой директории
	 * @param path путь до директории
	 */
	public void parseFiles(String path) throws IOException, ParserConfigurationException, SAXException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		File parent = new File(path);
		String[] nameFiles = parent.list();

		for (String nameFile : nameFiles) {
			File file = new File(path + "\\" + nameFile);
			Document doc = db.parse(file);
			Element persElement = (Element) doc.getElementsByTagName("person").item(0);
			NodeList childNode = persElement.getChildNodes();
			Person person = new Person();
			for (int i = 0; i < childNode.getLength(); i++) {
				if (childNode.item(i).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				Element childElement = (Element) childNode.item(i);
				switch (childElement.getNodeName()) {
					case "id": {
						person.setId(parseInt(childElement.getTextContent()));
						break;
					}
					case "USD": {
						person.setUsd(parseInt(childElement.getTextContent()));
						break;
					}
					case "name": {
						person.setName(childElement.getTextContent());
						break;
					}
					case "way": {
						person.setWay(childElement.getTextContent());
						break;
					}
				}
			}
			map.put(nameFile, person);
			model.setMap(map);
		}
	}

	/**
	 * Метод создает файл по принимаемым параметрам, используя метод createNewElementInFile() для создания элементов
	 * @param path путь до директории
	 * @param nameFile имя файла
	 * @param person объект Person, в котором содержатся  параметры для создания нового файла
	 */
	public void createFile(String path, String nameFile, Person person) throws IOException, TransformerException, ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().newDocument();
		Element root = doc.createElement("person");
		doc.appendChild(root);

		createNewElementInFile("USD", doc, root, Integer.toString(person.getUsd()));
		createNewElementInFile("id", doc, root, Integer.toString(person.getId()));
		createNewElementInFile("name", doc, root, person.getName());
		createNewElementInFile("way", doc, root, person.getWay());

		File file = new File(path + "\\" + nameFile);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(doc), new StreamResult(file));
		map.put(nameFile, person);
		model.setMap(map);
	}

	/**
	 * Метод создает элементы в элемент заголовок по принимаемым параметрам
	 * @param elementField элемент-заголовок создаваемого параметра
	 * @param doc экземпляр документа, в который происходит запись параметров
	 * @param root отцовский элемент для всех созданных элементов
	 * @param textContent параметр, заносимый в элемент-заголовок
	 */
	private void createNewElementInFile(String elementField, Document doc, Element root, String textContent){
		Element item = doc.createElement(elementField);
		item.setTextContent(textContent);
		root.appendChild(item);
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
	public void changeFile(String path, String nameFile, Person person) throws IOException, ParserConfigurationException, SAXException, TransformerException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		File file = new File(path + "\\" + nameFile);
		Document doc = db.parse(file);
		Node nodePerson = doc.getElementsByTagName("person").item(0);
		NodeList childNode = nodePerson.getChildNodes();
		for (int i = 0; i < childNode.getLength(); i++) {
			if (childNode.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element childElement = (Element) childNode.item(i);
			switch (childElement.getNodeName()) {
				case "id": {
					map.remove(nameFile);
					childElement.setTextContent(Integer.toString(person.getId()));
					break;
				}
				case "USD": {
					childElement.setTextContent(Integer.toString(person.getUsd()));
					break;
				}
				case "name": {
					childElement.setTextContent(person.getName());
					break;
				}
				case "way": {
					childElement.setTextContent(person.getWay());
					break;
				}
			}
		}
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path + "\\" + nameFile));
		transformer.transform(source, result);
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