package program.view;

import program.Person;
import program.controller.*;
import program.enumFormat;
import program.helpers.CsvHelper;
import program.helpers.PersonHelper;

import java.util.Scanner;

public class View {

	private static View view;

	private View(){}

	public static View getInstance(){
		if (view==null){
			view=new View();
		}
		return view;
	}


	private  Scanner in = new Scanner(System.in);

	Controller controller = Controller.getInstance();
	CsvHelper csvHelper = CsvHelper.getInstance();
	DBConnectController dbConnectController =  DBConnectController.getInstance();
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
				"7. Показать список персон из базы данных\n" +
				"8. Добавить персона в БД\n" +
				"9. Загрузить персону из БД в массив\n" +
				"10. Обновить персону в БД\n" +
				"11. Удалить файл\n"+
				"12. Выйти");

		active();
	}

	public void active()  {
		int act = in.nextInt();

			switch (act){
				case 1:
					System.out.println("Укажите путь и имя файла с указанием формата файла в формате: " +
							"C:\\Users\\FileName");
					String thankYouNextInt = in.nextLine();
					String createFilePath = in.nextLine();
					System.out.println("Укажите формат сохранения файла CSV, XML, JSON");
					String sw = in.nextLine();
					controller.createFile(createFilePath, sw);
					System.out.println("Файл был успешно создан\n");
					hello();

				case 2:
					System.out.println("Укажите путь и имя файла");
					 in.nextLine();
					 String filePath = in.nextLine();
					System.out.println("Укажите формат файла CSV, XML, JSON");
					String fr = in.nextLine();
					 controller.takePersonFromFile(filePath,fr);
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
						if (personHelper.showOnePerson(String.valueOf(personid))!=null){
							params= personToLine(personHelper.showOnePerson(String.valueOf(personid)));
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
							controller.newCsvParams(pathAndName,params,"y");
							System.out.println("Данные перезаписанны\n");
							hello();
						case 2:
							System.out.println("Сохранить оба файла y/n");
							String saverXMl = in.nextLine();
							controller.saveXML(pathAndName,params.trim(),saverXMl);
							System.out.println("Файл в формате .xml был успешно создан!\n");
							hello();
						case 3:
							System.out.println("Сохранить оба файла y/n");
							String saverJSON = in.nextLine();
							controller.saveJSON(pathAndName,params,saverJSON);
							System.out.println("Файл в формате .json был успешно создан!\n");
							hello();
					}

				case 4:
					personHelper.showAllPersons();
					hello();

				case 5:
					System.out.println("Укажите id персоны которую хотите изменить\n");
					in.nextLine();
					String id = in.nextLine();
					System.out.println("id   name           way       USD");
					personHelper.showOnePerson(id).toString();
					System.out.println("Укажите новые параметры выбранной персоны в формате - id,name,way,USD");
					String par = in.nextLine();
					controller.chanchePersonParams(id,par);
					System.out.println("Параметры успешно измененны");
					personHelper.showAllPersons();
					hello();

				case 6:
					System.out.println("Для добавления новой персоны в память укажите параметры выбранной " +
							"персоны в формате - id,name,way,USD \n");
					in.nextLine();
					String paramet= in.nextLine();
					personHelper.createPerson(paramet);
					System.out.println("Персона успешно добавленна\n");
					personHelper.showAllPersons();
					hello();

				case 7:
					dbConnectController.getAllPersonsFromDB();
					System.out.println("Персоны успешно выведенны.\n");
					hello();

				case 8:
					in.nextLine();
					System.out.println("Укажите id персоны");
					String personId = in.nextLine();
					dbConnectController.addPersonToDB(personHelper.showOnePerson(personId));
					System.out.println("Персоны успешно выведенны.\n");
					hello();

				case 9:
					in.nextLine();
					System.out.println("Укажите id персоны");
					String personFromDB = in.nextLine();
					dbConnectController.downloadPersonToM(personFromDB);
					System.out.println("Персона успешно загруженна\n");
					personHelper.showAllPersons();
					hello();

				case 10:
					in.nextLine();
					System.out.println("Укажите id персоны из БД которую хотите обновить");
					String updatePers = in.nextLine();
					dbConnectController.downloadPersonToM(updatePers);
					System.out.println("Укажите новые данные для персоны в формате - id,name,way,USD \n");
					String newParamsForDB = in.nextLine();
					System.out.println("id   name           way       USD");
					controller.chanchePersonParams(updatePers,newParamsForDB);
					dbConnectController.updatePersonFromDB(personHelper.showOnePerson(updatePers));
					System.out.println("Персона была успешно обновленна в БД и Массиве");
					personHelper.showAllPersons();
					dbConnectController.getCurrentelyPersonFromDB(updatePers);
					hello(); // 18,Mac Robson,Haven,888

				case 11:
					System.out.println("Укажите путь и имя файла\n");
					in.nextLine();
					controller.delete(in.nextLine());
					System.out.println("Файл удален");
					hello();

				case 12:
					System.exit(0);

				default:
					System.out.println("Неверная команда!\n");
					hello();
			}
	}


	public  String personToLine(Person person){

		String newline = person.id+","+person.name+","+person.way+","+person.USD;

		return newline;
	}
}