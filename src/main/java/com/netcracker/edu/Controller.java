package com.netcracker.edu;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Controller implements PersonHelper{

	private Integer wayId;
	private Model model = new Model();
	private WorkWithOracle workWithOracle = WorkWithOracle.getInstance(getOracleConnection());

	private Connection getOracleConnection() {
		OracleDriverManager driverManager = new OracleDriverManager();
		return driverManager.openOracleConnection();
	}

	@Override
	public boolean createPerson(String path, String nameFile, Person person, Integer choice) throws SQLException, IOException {
		if (isPersonIdExist(person.getId())) {
			return false;
		} else {
			wayId = isWayExist(person.getWay());
			if (wayId == null) {
				workWithOracle.insertPersonWay(person);
			} else {
				workWithOracle.insertPerson(person, wayId);
			}
			switch (choice){
				case (1):{
					model.saveXML(path, nameFile, person);
				} break;
				case (2):{
					model.saveCSV(path, nameFile, person);
				} break;
				case (3):{
					model.saveJSON(path, nameFile, person);
				} break;
			}
			return true;
		}
	}

	@Override
	public void changePerson(Person person) throws SQLException {
		wayId = isWayExist(person.getWay());
		if(wayId==null) {
			workWithOracle.updatePersonWay(person);
		}else{
			workWithOracle.updatePerson(person, wayId);
		}
	}

	@Override
	public void deletePerson(Integer persId) throws SQLException {
			workWithOracle.delete(persId);
	}

	@Override
	public String getAllPerson() throws SQLException {
		return model.printPerson(workWithOracle.selectAll());
	}

	@Override
	public String getPerson(Integer id) throws SQLException {
		return model.printPerson(workWithOracle.selectId(id));
	}

	@Override
	public void parseFilesInDir(String path) throws SQLException {
		Person person;
		File parent = new File(path);
		File dirToOldFiles = new File(path + "\\" + "Old");
		if (!dirToOldFiles.exists()){
			dirToOldFiles.mkdir();
		}
		String[] nameFiles = parent.list();
		for (String nameFile : nameFiles) {
			if(nameFile.endsWith(".xml")) {
				File file = new File(path +"\\"+nameFile);
				person = model.parsingFile(path, nameFile);
				file.renameTo(new File(path+"\\Old\\"+nameFile));
				if (!isPersonIdExist(person.getId())) {
					if (isWayExist(person.getWay()) == null){
						workWithOracle.insertPersonWay(person);
					} else {
						workWithOracle.insertPerson(person, wayId);
					}
				}
			}
		}
	}

	@Override
	public boolean parseFile(String path, String nameFile) throws SQLException {
		Person person;
		person = model.parsingFile(path, nameFile);
		if (!isPersonIdExist(person.getId())) {
			if (isWayExist(person.getWay()) == null){
				workWithOracle.insertPersonWay(person);
			} else {
				workWithOracle.insertPerson(person, wayId);
			}
			return true;
		} else return false;
	}

	public boolean checkFileInDir(String path, String nameFile){
		File parent = new File(path);
		String[] nameFiles = parent.list();
		for (String name : nameFiles) {
			if (nameFile.equals(name)) {
				return false;
			}
		}
		return true;
	}

	public String addExtension(String nameFile, Integer choice){
		switch (choice){
			case (1):{
				if(!nameFile.endsWith(".xml")) {
					nameFile = nameFile + ".xml";
				}
			} break;

			case (2):{
				if(!nameFile.endsWith(".csv")) {
					nameFile = nameFile + ".csv";
				}
			} break;

			case (3):{
				if(!nameFile.endsWith(".json")) {
					nameFile = nameFile + ".json";
				}
			} break;
		}
		return nameFile;
	}

	private Integer isWayExist(String way) throws SQLException {
		return wayId = model.theWayId(workWithOracle.findWay(way));
	}

	private boolean isPersonIdExist(Integer id) throws SQLException {
		return model.isExistPersonId(workWithOracle.selectId(id), id);
	}
}