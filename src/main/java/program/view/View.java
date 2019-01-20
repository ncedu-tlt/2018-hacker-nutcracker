package program.view;

import program.controller.*;

import java.util.Scanner;

public class View {

	private  Scanner in = new Scanner(System.in);

	Controller controller = Controller.getInstance();
	PersonHelper personHelper = PersonHelper.getInstance();

	public void hello()  {

		System.out.println(
				"\nДля управление приложением введите номер нужной команды \n" +
				"1. Создать файл \n" +
				"2. Вывести данныe файла \n" +
				"3. Изменить данные файла\n" +
				"4. Вывести список загруженных персон \n" +
				"5. Изменить данные персоны\n" +
				"6. Добавить персону в массив\n" +
				"7. Удалить файл\n" +
				"8. Выйти");

		active();
	}

	public void active()  {
		int act = in.nextInt();

			switch (act){
				case 1:
					System.out.println("Укажите путь и имя файла с указанием формата файла в формате: " +
							"C:\\Users\\FileName");
					String thankYouNextInt = in.nextLine();
					controller.createCsvFile(in.nextLine());
					System.out.println("Файл был успешно создан\n");
					hello();

				case 2:
					System.out.println("Укажите путь и имя файла");
					 in.nextLine();
					personHelper.readFile(in.nextLine());
					hello();

				case 3:
					System.out.println("Укажите путь и имя файла");
					in.nextLine();
					String pathAndName = in.nextLine();

					System.out.println("Если вы хотите сохранить персону из паияти нажмите 1");
					System.out.println("Если вы хотите указать новые данные нажмите 2");
					int con = in.nextInt();
					in.nextLine();
					String params = null;
					if (con==1){
						System.out.println("Укажите id персоны");
						int personid = in.nextInt();
						in.nextLine();
						if (personHelper.returnSpecificPerson(String.valueOf(personid))!=null){
							params= personHelper.personToLine(personHelper.returnSpecificPerson(String.valueOf(personid)));
						}else hello();

					}
					else{
						System.out.println("Укажите новые данные для файла в формате - id,name,way,USD");
						params = in.nextLine();
					}
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
					personHelper.showAtt();
					hello();

				case 5:
					System.out.println("Укажите id персоны которую хотите изменить\n");
					in.nextLine();
					String id = in.nextLine();
					personHelper.showSpecificPerson(id);
					System.out.println("Укажите новые параметры выбранной персоны в формате - id,name,way,USD");
					String par = in.nextLine();
					controller.chanchePersonParams(id,par);
					System.out.println("Параметры успешно измененны");
					personHelper.showAtt();
					hello();

				case 6:
					System.out.println("Для добавления новой персоны в память укажите параметры выбранной " +
							"персоны в формате - id,name,way,USD \n");
					in.nextLine();
					String paramet= in.nextLine();
					String fornew[] = paramet.split(",");
					personHelper.addPerson(Integer.parseInt(fornew[0]),fornew[1],fornew[2],Integer.parseInt(fornew[3]));
					System.out.println("Персона успешно добавленна\n");
					personHelper.showAtt();
					hello();

				case 7:
					System.out.println("Укажите путь и имя файла\n");
					in.nextLine();
					controller.delete(in.nextLine());
					System.out.println("Файл удален");
					hello();

				case 8:
					System.exit(0);

				default:
					System.out.println("Неверная команда!\n");
					hello();
			}
	}
}