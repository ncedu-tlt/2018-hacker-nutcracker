package com.netcracker.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class View {
	private String path;
	private int choice;
	private boolean wasWorking;
	private boolean isCorrect=true;

	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	private Controller controller = new Controller();

	/**
	 * Меню для диалога с пользователем
	 * @throws IOException
	 */
	public void menu() throws IOException {
		System.out.println("Выберите действие из предложенных");
		System.out.println("1. Создать файл");
		System.out.println("2. Вывести файлы на экран");
		System.out.println("3. Изменить данные в файле");
		System.out.println("4. Удалить файл");
		System.out.println("0. Выход");

		// Блок проверки данных на корректность.
		try {
			String line = buf.readLine();
			choice = parseInt(line);
		} catch (NumberFormatException e) {
			choice=5; // В случае ввода текстовой строки
		}
		//Парсим все файлы из директории один раз, если выбраны 1,2,3,4 пункты в меню
		if (choice!=0 && choice!=5){
			if (!wasWorking){
				System.out.println("Введите путь до файла: ");
				path = buf.readLine();
				wasWorking=controller.parseFiles(path);
			}
		}
		choiceMenu();
	}

	/**
	 * Действия для каждого пункта menu()
	 * @throws IOException
	 */
	private void choiceMenu() throws IOException {
		String nameFile;
		switch (choice) {
			case (1): {//Создание
				System.out.println("Введите название файла");
				nameFile = buf.readLine();
				nameFile = controller.checkNameFile(nameFile);
				if (!controller.checkFileInMap(nameFile)) {
					Person person = new Person();
					while(isCorrect) {
						try {
							System.out.println("USD: ");
							person.setUSD(parseInt(buf.readLine()));
							System.out.println("id: ");
							person.setId(parseInt(buf.readLine()));
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

					controller.createFile(path, nameFile, person);
					for (Map.Entry<String, Person> item : controller.getAllPerson().entrySet()) {
						System.out.printf("nameFile: %s  Value: %s \n", item.getKey(), item.getValue());
					}
				} else System.out.println("Такой файл уже имеется!");
				menu();
			}
			break;

			case (2): {//Вывод на экран
				for (Map.Entry<String, Person> item : controller.getAllPerson().entrySet()) {
					System.out.printf("nameFile: %s  Value: %s \n", item.getKey(), item.getValue());
				}
				menu();
			}
			break;

			case (3): {//Изменение
				System.out.println("Введите название файла");
				nameFile = buf.readLine();
				nameFile = controller.checkNameFile(nameFile);
				if (controller.checkFileInMap(nameFile)) {
					Person person = new Person();
					while(isCorrect) {
						try {
							System.out.println("USD: ");
							person.setUSD(parseInt(buf.readLine()));
							System.out.println("id: ");
							person.setId(parseInt(buf.readLine()));
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
					controller.changeFile(path, nameFile, person);
					for (Map.Entry<String, Person> item : controller.getAllPerson().entrySet()) {
						System.out.printf("nameFile: %s  Value: %s \n", item.getKey(), item.getValue());
					}
				} else System.out.println("Файл не найден!");
				menu();
			}
			break;

			case (4): {//Удаление
				System.out.println("Введите название файла");
				nameFile = buf.readLine();
				nameFile = controller.checkNameFile(nameFile);

				if(controller.deleteFile(path, nameFile)){
					System.out.println("Файл "+ nameFile + " удален!");
				} else {
					System.out.println("Файл "+ nameFile + " не найден!");
				}

				System.out.println();
				for (Map.Entry<String, Person> item : controller.getAllPerson().entrySet()) {
					System.out.printf("nameFile: %s  Value: %s \n", item.getKey(), item.getValue());
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