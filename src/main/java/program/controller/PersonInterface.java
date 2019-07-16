package program.controller;

import program.Person;

public interface PersonInterface {

    void deletePerson(String id);

    void createPerson(String params);

    void updatePerson(String id, String params);

    void showAllPersons();

    Person showOnePerson(String id);

    void takePersonFromFile(String path);

    void savePersonToFile(String path, String params, String saveOrNot);

    void createFile(String path);
}
