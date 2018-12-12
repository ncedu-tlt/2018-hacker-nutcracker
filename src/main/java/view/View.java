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
				"4. Удалить файл\n" +
				"5. Выйти");

		active();
	}

	public void active() throws IOException {
		int act = in.nextInt();

		if (act<=5){

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
					String params = in.nextLine();
					System.out.println("Выберете формат сохранения файла\n" +
							"1.CSV\n" +
							"2.XML");

					int form = in.nextInt();
					in.nextLine();
						switch (form) {
							case 1:
								controller.newParams(pathAndName,params);
							case 2:
								System.out.println("Сохранить оба файла y/n");
								String saver = in.nextLine();
								controller.saveXML(pathAndName,params.trim(),saver);
							case 3:


						}
					hello();

				case 4:
					System.out.println("Укажите путь и имя файла\n");
					in.nextLine();
					controller.delete(in.nextLine());
					hello();

				case 5:
					System.exit(0);
			}
		}

	}
}