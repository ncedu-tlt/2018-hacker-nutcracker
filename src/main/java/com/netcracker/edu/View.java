package com.netcracker.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;

import static java.lang.Integer.parseInt;

public class View {
	private String path;
	private String nameFile;
	private int choice;
	private boolean isCorrect=true;
//	private String login;
//	private String password;

	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	private Controller controller = new Controller();

	public void menu() throws IOException, SQLException {
//		if (login==null){
//			System.out.println("Введите логин и пароль для доступа к базе данных");
//			login=buf.readLine();
//			password=buf.readLine();
//			OracleDriverManager ora = new OracleDriverManager();
//			ora.getLogPas(login, password);
//		}
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
		} catch (NumberFormatException e) {
			choice=10; // В случае ввода текстовой строки
		}

		choiceMenu();
	}

	private void choiceMenu() throws IOException, SQLException {
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
					while(isCorrect) {
						try {
							System.out.println("id: ");
							person.setId(parseInt(buf.readLine()));
							System.out.println("USD: ");
							person.setUSD(parseInt(buf.readLine()));
							isCorrect=false;
						} catch (NumberFormatException e) {
							System.out.println("Неверный формат данных. ID и USD должны быть целыми числами!");
						}
					}
					isCorrect=true;
					System.out.println("name: ");
					person.setName(buf.readLine());
					System.out.println("way: ");
					person.setWay(buf.readLine());

					if(!controller.createPerson(path, nameFile, person, choice)){
	                    System.out.println("Person с таким ID уже существует");
					}
				} else System.out.println("Файл с таким именем уже имеется!");
				menu();
			}
			break;

			case (2): {//Изменение
				System.out.println(controller.getAllPerson());
				Person person = new Person();
				System.out.println("Введите ID:");
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
				controller.changePerson(person);
				menu();
			}
			break;

			case (3): {//Удаление
				System.out.println("Введите ID:");
				Integer persId = Integer.parseInt(buf.readLine());
				controller.deletePerson(persId);

				menu();
			}
			break;

			case (4): {//Вывод на экран всех Person
				System.out.println(controller.getAllPerson());
				menu();
			}
			break;

			case(5): {//Вывести на экран Person по ID
				System.out.println("Введите ID: ");
				System.out.println(controller.getPerson(parseInt(buf.readLine())));
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
				if(!controller.parseFile(path, nameFile)) {
					System.out.println("Ошибка добавления записи. Person с таким ID уже существует");
				}
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