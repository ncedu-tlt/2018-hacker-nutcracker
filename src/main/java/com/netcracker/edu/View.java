package com.netcracker.edu;
import com.netcracker.edu.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class View {
	private String path;
	private String nameFile;
	private int choice;
	private boolean isCorrect=true;

	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	private Controller controller = new Controller();


	public void menu() throws IOException {
		System.out.println("Выберите действие из предложенных");
		System.out.println("1. Создать файл и новую запись в базе данных");
		System.out.println("2. Изменить Person в базе данных");
		System.out.println("3. Удалить Person из базы данных");
		System.out.println("4. Вывести на экран всех Person из базы данных");
		System.out.println("5. Вывести на экран Person по ID");
		System.out.println("6. Добавить все файлы из директории в базу данных");
		System.out.println("7. Добавить Person в базу данных из файла");
		System.out.println("0. Выход");

		// Блок проверки данных на корректность.
		try {
			choice = parseInt(buf.readLine());
		} catch (Exception e) {
			choice=10; // В случае ввода текстовой строки
		}
		choiceMenu();
	}

	private void choiceMenu()throws IOException {
		switch (choice) {
			case (1): {//Создание
				System.out.println("Введите путь до директории: ");
				path = buf.readLine();
				System.out.println("Введите название файла");
				nameFile = buf.readLine();
				System.out.println("В каком формате сохранить файл? \n1. XML \n2. CSV \n3. JSON");
				choice = Integer.parseInt(buf.readLine());
				nameFile = controller.addExtension(nameFile, choice);
				if (controller.checkFileInDir(path, nameFile)) {
					Person person = new Person();
					while (isCorrect) {
						try {
							System.out.println("id: ");
							person.setId(parseInt(buf.readLine()));
							System.out.println("USD: ");
							person.setUSD(parseInt(buf.readLine()));
							isCorrect = false;
						} catch (NumberFormatException e) {
							System.out.println("Неверный формат данных. ID и USD должны быть целыми числами!");
						}
					}
					isCorrect = true;
					System.out.println("name: ");
					person.setName(buf.readLine());
					System.out.println("way: ");
					person.setWay(buf.readLine());

					controller.createPerson(path, nameFile, person, choice);
				} else System.out.println("Файл с таким именем уже имеется!");
				menu();
			}
			break;

			case (2): {//Изменение
				System.out.println("ID  NAME               WAY       USD");
				ArrayList<Person> persons = controller.getAllPerson();
				for (Person person: persons){
					System.out.print(person.toString());
				}
				Person person = new Person();
				System.out.println("Введите ID Person'a для изменения:");
				person.setId(Integer.parseInt(buf.readLine()));

				System.out.println("name: ");
				person.setName(buf.readLine());
				System.out.println("way: ");
				person.setWay(buf.readLine());
				while(isCorrect) {
					try {
						System.out.println("USD: ");
						person.setUSD(parseInt(buf.readLine()));
						isCorrect=false;
					} catch (NumberFormatException e) {
						System.out.println("Неверный формат данных. USD должен быть целым числом!");
					}
				}
				isCorrect=true;
				if((controller.getPerson(person.getId()).getId())==0){
					System.out.println("Person с таким ID  не найден!");
				} else controller.changePerson(person);
				menu();
			}
			break;

			case (3): {//Удаление
				System.out.println("ID  NAME               WAY       USD");
				ArrayList<Person> persons = controller.getAllPerson();
				for (Person person: persons){
					System.out.print(person.toString());
				}
				System.out.println("Введите ID:");
				Integer persId = Integer.parseInt(buf.readLine());
				if(controller.getPerson(persId).getId()==0){
					System.out.println("Person с таким ID  не найден!");
				} else controller.deletePerson(persId);
				menu();
			}
			break;

			case (4): {//Вывод на экран всех Person
				System.out.println("ID  NAME               WAY       USD");
				ArrayList<Person> persons = controller.getAllPerson();
				for (Person person: persons){
					System.out.print(person.toString());
				}
				System.out.println();
				menu();
			}
			break;

			case(5): {//Вывести на экран Person по ID
				System.out.println("Введите ID: ");
				Integer persId = Integer.parseInt(buf.readLine());
				System.out.println("ID  NAME               WAY       USD");
				System.out.println(controller.getPerson(persId));
				menu();
			}
			break;

			case(6): {//Добавить все файлы из дирректории
				System.out.println("Введите путь до директории: ");
				path = buf.readLine();
				controller.parseFilesInDir(path);
				menu();
			}
			break;

			case(7): {//Добавить Person из файла
				System.out.println("Введите путь до директории: ");
				path = buf.readLine();
				System.out.println("Введите имя файла: ");
				nameFile = buf.readLine();

				nameFile = controller.addExtension(nameFile, 1);
				controller.parseFile(path, nameFile);
				menu();
			}
			break;

			case (0): {//Выход
				System.exit(0);
			}
			break;

			default: {
				System.out.println("Неверное действие");
				menu();
			}
		}
	}
}