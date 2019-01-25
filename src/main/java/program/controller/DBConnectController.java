package program.controller;

import program.Person;
import program.view.View;

import java.sql.*;

public class DBConnectController {

	private static DBConnectController dbConnectController ;

	private  DBConnectController(){}

	public static DBConnectController getInstance(){
		if (dbConnectController==null){
			dbConnectController = new DBConnectController();
		}
		return dbConnectController;
	}

	OracleDriverManager oracleDriverManager = OracleDriverManager.getInstance();

	CsvHelper csvHelper = CsvHelper.getInstance();// Правильно ли??

	private Connection connection = oracleDriverManager.openOracleConnection();

	public void getAllPersonsFromDB(){
		try {
		String query = "select p.persons_id,p.name,d.name as way, p.usd " +
					   " from persons p, directions d " +
					   " where p.way = d.id";

		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				String personsId = resultSet.getString("PERSONS_ID");
				String name = resultSet.getString("name");
				String way = resultSet.getString("way");
				Integer usd = resultSet.getInt("USD");
				System.out.println("id       name         way     USD");
				System.out.println(String.format("%s,   %s,   %s,   %d", personsId, name, way, usd));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}


	public void downloadPersonToM(String id){
		try {
			String query = "select p.persons_id,p.name,d.name as way, p.usd \n" +
					" from persons p,directions d \n" +
					" where p.persons_id = " + id +
					" and p.way = d.id ";

			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				String par = Integer.parseInt(resultSet.getString("PERSONS_ID"))+","+
								resultSet.getString("name")+","+
								resultSet.getString("way")+","+resultSet.getInt("USD");

				csvHelper.createPerson(par);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void addPersonToDB(Person person)  {
		try {
		String query = "INSERT INTO Persons (PERSONS_ID, name, way, USD) VALUES (?, ?, ?, ?)";
		dbConnectController.checkID(String.valueOf(person.getId()));
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(person.getId()));
		preparedStatement.setString(2, person.getName());
		String checkw = checkWay(person.getWay());
		preparedStatement.setString(3, checkw);
		preparedStatement.setInt(4, person.getUSD());
		preparedStatement.execute();

		System.out.println("Персона успешно добавленна\n");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePersonFromDB(Person person){
		try {
		String queryForUpdatePerson = " UPDATE persons SET  name = ?, way = ?, usd = ? where persons_id = ? ";

		PreparedStatement preparedStatement = connection.prepareStatement(queryForUpdatePerson);

			preparedStatement.setString(1, person.getName());
			String checkw = checkWay(person.getWay());
			preparedStatement.setString(2, checkw);
			preparedStatement.setInt(3, person.getUSD());
			preparedStatement.setString(4, String.valueOf(person.getId()));
			preparedStatement.execute();
			System.out.println("Выбранная персона была успешно обновленна.\n");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void getCurrentelyPersonFromDB(String id){
		try {
			String query = " select p.persons_id,p.name,d.name as way, p.usd \n" +
					" from persons p, directions d \n" +
					" where p.way = d.id "+
					" and p.persons_id = " + id + " ";

			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				String personsId = resultSet.getString("PERSONS_ID");
				String name = resultSet.getString("name");
				String way = resultSet.getString("way");
				Integer usd = resultSet.getInt("USD");
				System.out.println("id       name         way     USD");
				System.out.println(String.format("%s,   %s,   %s,   %d", personsId, name, way, usd));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public String checkWay(String way){
		try{
			String id = "select d.id" +
					" from directions d" +
					" where d.name = " + "'" + way + "'";
			Statement stmt = connection.createStatement();
			ResultSet resultId = stmt.executeQuery(id);
//      19,Fill Chop,Base,784
			if (!resultId.next()){
//				if (resultId.wasNull()) {
					String addQuery = "INSERT INTO directions (id, name) VALUES (TO_NUMBER(DIREC_SEQ.nextval), ?)";
					PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
					preparedStatement.setString(1, way);
					preparedStatement.execute();
					preparedStatement.close();

				String newId = "select d.id" +
						" from directions d" +
						" where d.name = " + "'" + way + "'";

					ResultSet newResultId = stmt.executeQuery(newId);
//					while (newResultId.next()) {//
//						String wayId = resultId.getString("id");
//						System.out.println(wayId);
//						return wayId;
//					}

				if (newResultId !=null){
					while (newResultId.next()) {
						String wayId = resultId.getString("id");
//						System.out.println(wayId);
						return wayId;
					}
					String wayId = resultId.getString("id");
//					System.out.println(wayId);
					return wayId;
				}

//				}
			}
			else {
					String wayId = resultId.getString("id");
					System.out.println(wayId);
					return  wayId;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public void checkID(String id){
		try {
			String query = " select p.persons_id \n" +
					" from persons p \n" +
					" where p.persons_id = " + id + " ";

			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			if (resultSet.next()){
				System.out.println("\n ATTENTION ATTENTION ERROR #16884 ПЕРСОНА С ТАКИМ ID УЖЕ СУЩЕСТВУЕТ \n" +
						"Поменяйте id персоны в памяти или в БД");
				View view = View.getInstance();
				view.hello();
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
