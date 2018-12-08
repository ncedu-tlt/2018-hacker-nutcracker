package view;

import controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class View {

	private  Scanner in = new Scanner(System.in);
	private Controller controller = new Controller();

	public void hello() throws IOException {
		System.out.println("\nДобро пожаловать! \n" +
				"Для управление приложением введите номер нужной команды \n" +
				"1. Создать файл \n" +
				"2. Вывести данныe файла \n" +
				"3. Изменить данный файла\n" +
				"4. Удалить файл");

		active();
	}

	public void active() throws IOException {
		int act = in.nextInt();

		if (act<=4){

			switch (act){

				case 1:
					System.out.println("Укажите путь и имя файла с указанием формата файла в формате: " +
							"C:\\Users\\FileName.csv");
					String thankYouNextInt = in.nextLine();
					controller.createFile(in.nextLine());
					hello();

				case 2:
					System.out.println("Укажите путь и имя файла");
					 in.nextLine();
					controller.readFile(in.nextLine());
					hello();

				case 3:
					System.out.println("Укажите путь и имя файла");
					in.nextLine();
					String pathAndName = in.nextLine();
					System.out.println("Укажите новые данные для файла в формате - id,name,way,USD");
					controller.newParams(pathAndName,in.nextLine());
					hello();

				case 4:
					System.out.println("Укажите путь и имя файла");
					in.nextLine();
					controller.delete(in.nextLine());
					hello();
			}
		}

	}
}