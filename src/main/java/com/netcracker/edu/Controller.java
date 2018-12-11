package com.netcracker.edu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

	private Model model = new Model();
	private HashMap<String, Person> map = model.getMap();

	public void parseFiles(String path) {
		try {
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
							person.setId(childElement.getTextContent());
							break;
						}
						case "USD": {
							person.setUsd(childElement.getTextContent());
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
		} catch (Exception e) {
			e.getMessage();
		}
	}
	//Создание нового тега в новый файл
	private void createNewElementInFile(String elementField, Document doc, Element root, String textContent){
		Element item = doc.createElement(elementField);
		item.setTextContent(textContent);
		root.appendChild(item);
	}

	public void createFile(String path, String nameFile, Person person) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document doc = dbf.newDocumentBuilder().newDocument();
			Element root = doc.createElement("person");
			doc.appendChild(root);

			createNewElementInFile("USD", doc, root, person.getUsd());
			createNewElementInFile("id", doc, root, person.getId());
			createNewElementInFile("name", doc, root, person.getName());
			createNewElementInFile("way", doc, root, person.getWay());

			File file = new File(path + "\\" + nameFile);
			if(file.createNewFile()){
				System.out.println("Файл успешно создан!");
			} else {
				System.out.println("Файл не создан!");
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(file));

			map.put(nameFile, person);
			model.setMap(map);
		}catch (Exception e) {
			System.out.println("Ошибка при создании файла!");
		}
	}

	public void changeFile(String path, String nameFile, Person person) {
		try {
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
						childElement.setTextContent(person.getId());
						break;
					}
					case "USD": {
						childElement.setTextContent(person.getUsd());
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
		}catch (Exception e){
			System.out.println("Ошибка изменения файла !");
		}
	}

	public void deleteFile(String path, String nameFile){
		File file = new File(path + "\\" + nameFile);
		if (file.delete()){
			System.out.println("Файл "+ nameFile + " удален!");
		} else {
			System.out.println("Файл "+ nameFile + " не найден!");
		}
		map.remove(nameFile);
	}

	public void printMap(){
		for (Map.Entry<String, Person> item : map.entrySet()) {
			System.out.printf("nameFile: %s  Value: %s \n", item.getKey(), item.getValue());
		}
		System.out.println();
	}

	public String checkNameFile(String nameFile){
		boolean endsWith = nameFile.endsWith(".xml");
		if(!endsWith) {
			nameFile = nameFile + ".xml";
		}
		return nameFile;
	}
}
