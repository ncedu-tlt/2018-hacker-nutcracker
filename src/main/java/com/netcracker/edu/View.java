package com.netcracker.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
	private String path;
	private int choice;
	private boolean isCorrect=true;
	private boolean wasWorking;

	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	private Controller controller = new Controller();

	public void menu() throws IOException {
		System.out.println("Выберите действие из предложенных");
		System.out.println("1. Создать файл");
		System.out.println("2. Прочесть файл");
		System.out.println("3. Изменить данные в файле");
		System.out.println("4. Удалить файл");
		System.out.println("0. Выход");

		// Блок проверки данных на корректность.
		try {
			String line = buf.readLine();
			choice = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			choice=5; // В случае ввода текстовой строки
		}
		choiceMenu();
	}

	private void choiceMenu() throws IOException{
		while (isCorrect) {
			String nameFile;
			switch (choice) {
				case (1): {
					pasreAllFilesInDirectory();
					System.out.println("Введите найзвание файла");
					nameFile = buf.readLine();
					nameFile = controller.checkNameFile(nameFile);

					Person person = new Person();
					System.out.println("USD: "); person.setUsd(buf.readLine());
					System.out.println("id: "); person.setId(buf.readLine());
					System.out.println("name: "); person.setName(buf.readLine());
					System.out.println("way: "); person.setWay(buf.readLine());

					controller.createFile(path, nameFile, person);
					controller.printMap();
					menu();
				}break;

				case (2): {
					pasreAllFilesInDirectory();
					controller.printMap();
					menu();
				}break;

				case (3): {
					pasreAllFilesInDirectory();
					System.out.println("Введите название файла");
					nameFile = buf.readLine();
					nameFile = controller.checkNameFile(nameFile);

					Person person = new Person();
					System.out.println("Введите новые данные.");
					System.out.println("USD: "); person.setUsd(buf.readLine());
					System.out.println("id: "); person.setId(buf.readLine());
					System.out.println("name: "); person.setName(buf.readLine());
					System.out.println("way: "); person.setWay(buf.readLine());

					controller.changeFile(path, nameFile, person);
					controller.printMap();
					menu();
				}break;

				case (4): {
					pasreAllFilesInDirectory();
					System.out.println("Введите название файла");
					nameFile = buf.readLine();
					nameFile = controller.checkNameFile(nameFile);
					controller.deleteFile(path, nameFile);
					System.out.println();
					controller.printMap();
					menu();
				}break;

				case (0): {
					isCorrect=false;
				}break;

				default: {
					System.out.println("Неверное действие");
					menu();
				}
			}
		}
	}

	// Парсим все файлы из директории только 1 раз
	private void pasreAllFilesInDirectory() throws IOException {
		if(!wasWorking){
			System.out.println("Введите путь до файла: ");
			path = buf.readLine();
			controller.parseFiles(path);
			wasWorking=true;
		}
	}
}