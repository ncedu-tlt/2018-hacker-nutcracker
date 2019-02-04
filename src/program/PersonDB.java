package program;

import Servlets.ChangePersonServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonDB {

    private static PersonDB personDB;

    private PersonDB() {
    }

    public static PersonDB getInstance() {
        if (personDB == null) {
            personDB = new PersonDB();
        }
        return personDB;
    }

     DBConnetcion dbConnetcion = DBConnetcion.getInstance();
     Connection connection = dbConnetcion.openOracleConnection();



    public  ArrayList<Person> selectALL() {
        ArrayList<Person> personArrayList = new ArrayList<>();
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

                Person person = new Person(Integer.parseInt(personsId), name, way, usd);

                personArrayList.add(person);
            }
         return personArrayList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personArrayList;
    }

    public void addPerson(Person person){
        try {
            String query = "INSERT INTO Persons (PERSONS_ID, name, way, USD) VALUES (?, ?, ?, ?)";
            personDB.checkID(String.valueOf(person.getId()));
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(person.getId()));
            preparedStatement.setString(2, person.getName());
            String checkw = getWay(person.getWay());
            preparedStatement.setString(3, checkw);
            preparedStatement.setInt(4, person.getUSD());
            preparedStatement.execute();

            System.out.println("Персона успешно добавленна\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getWay(String way) {
        try {
            String id = "select d.id" +
                    " from directions d" +
                    " where d.name = " + "'" + way + "'";
            Statement stmt = connection.createStatement();
            ResultSet resultId = stmt.executeQuery(id);

            List<String> listorResult = new ArrayList();

            while (resultId.next()) {
                listorResult.add(resultId.getString("id"));
            }

            if (listorResult.isEmpty()) {
                String addQuery = "INSERT INTO directions (id, name) VALUES (TO_NUMBER(DIREC_SEQ.nextval), ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
                preparedStatement.setString(1, way);
                preparedStatement.execute();

                String newId = "select d.id" +
                        " from directions d" +
                        " where d.name = " + "'" + way + "'";

                ResultSet newResultId = stmt.executeQuery(newId);
                List<String> ArrayorResult = new ArrayList();

                while (newResultId.next()) {
                    ArrayorResult.add(newResultId.getString("id"));
                }

                if (!ArrayorResult.isEmpty()) {
                    String wayId = ArrayorResult.get(0);
                    return wayId;
                }
            } else {
                String wayId = listorResult.get(0);
                return wayId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkID(String id) {
        try {
            String query = " select p.persons_id " +
                    " from persons p " +
                    " where p.persons_id = " + id + " ";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            List<String> idArray = new ArrayList();

            while (resultSet.next()) {
                idArray.add(resultSet.getString("persons_id"));
            }
            if (!idArray.isEmpty()) {
                if (idArray.get(0).equals(id)) {
                   // тут надо чёт придумать потому что не знаю как реализовать проверку

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Person findPerson(String id) {
        try {
            String query = " select p.persons_id,p.name,d.name as way, p.usd \n" +
                    " from persons p, directions d \n" +
                    " where p.way = d.id " +
                    " and p.persons_id = " + id + " ";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                String personsId = resultSet.getString("PERSONS_ID");
                String name = resultSet.getString("name");
                String way = resultSet.getString("way");
                Integer usd = resultSet.getInt("USD");
                Person person = new Person(Integer.parseInt(personsId),name,way,usd);
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePerson(String id){
        try {
            String query = "DELETE FROM Persons Where PERSONS_ID=" + id;
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePersonFromDB(Person person) {
        try {
            String queryForUpdatePerson = " UPDATE persons SET  name = ?, way = ?, usd = ? where persons_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(queryForUpdatePerson);

            preparedStatement.setString(1, person.getName());
            String checkw = getWay(person.getWay());
            preparedStatement.setString(2, checkw);
            preparedStatement.setInt(3, person.getUSD());
            preparedStatement.setString(4, String.valueOf(person.getId()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
