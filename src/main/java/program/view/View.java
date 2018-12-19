package program.view;

import program.controller.Controller;
import program.model.CsvHelper;

import java.io.IOException;
import java.util.Scanner;

public class View {

	private  Scanner in = new Scanner(System.in);

	Controller controller = Controller.getInstance();
	CsvHelper csvHelper = CsvHelper.getInstance();
	public void hello()  {

		System.out.println(
				"Для управление приложением введите номер нужной команды \n" +
				"1. Создать файл \n" +
				"2. Вывести данныe файла \n" +
				"3. Изменить данный файла\n" +
				"4. Удалить файл\n" +
				"5. Выйти");

		active();
	}

	public void active()  {
		int act = in.nextInt();

			switch (act){

				case 1:
					System.out.println("Укажите путь и имя файла с указанием формата файла в формате: " +
							"C:\\Users\\FileName.csv");
					String thankYouNextInt = in.nextLine();
					controller.createCsvFile(in.nextLine());
					System.out.println("Файл был успешно создан\n");
					hello();

				case 2:
					System.out.println("Укажите путь и имя файла");
					 in.nextLine();
					csvHelper.readFile(in.nextLine());
					hello();

				case 3:
					System.out.println("Укажите путь и имя файла");
					in.nextLine();
					String pathAndName = in.nextLine();
					System.out.println("Укажите новые данные для файла в формате - id,name,way,USD");
					String params = in.nextLine();
					System.out.println("Выберете формат сохранения файла\n" +
							"1.CSV\n" +
							"2.XML\n" +
							"3.JSON");

					int form = in.nextInt();
					in.nextLine();
						switch (form) {
							case 1:
								controller.newCsvParams(pathAndName,params);
								System.out.println("Данные перезаписанны\n");
								hello();
							case 2:
								System.out.println("Сохранить оба файла y/n");
								String saver = in.nextLine();
								controller.saveCsvLikeXML(pathAndName,params.trim(),saver);
								System.out.println("Файл в формате .xml был успешно создан!\n");
								hello();
							case 3:
								System.out.println("Сохранить оба файла y/n");
								String saverJSON = in.nextLine();
								controller.saveCsvLikeJSON(pathAndName,params,saverJSON);
								System.out.println("Файл в формате .json был успешно создан!\n");
								hello();
						}
				case 4:
					System.out.println("Укажите путь и имя файла\n");
					in.nextLine();
					controller.delete(in.nextLine());
					System.out.println("Файл удален");
					hello();

				case 5:
					System.exit(0);

				default:
					System.out.println("Неверная команда!\n");
					hello();
			}


	}
}