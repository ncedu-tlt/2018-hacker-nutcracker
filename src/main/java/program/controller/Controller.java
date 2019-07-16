package program.controller;

import program.enumFormat;
import program.helpers.CsvHelper;
import program.helpers.JSONHelper;
import program.helpers.PersonHelper;
import program.helpers.XMLHelper;

import java.io.*;

import static program.enumFormat.CSV;

public class Controller {


    private static Controller controller;

    private Controller() {
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    CsvHelper csvHelper = CsvHelper.getInstance();

    XMLHelper xmlHelper = XMLHelper.getInstance();

    JSONHelper jsonHelper = JSONHelper.getInstance();

    PersonHelper personHelper = PersonHelper.getInstance();

    public void createFile(String path, String format) {
        switch (format.toUpperCase()) {
            case "CSV":
                csvHelper.createFile(path);
            case "XML":
                xmlHelper.createFile(path);
            case "JSON":
                jsonHelper.createFile(path);
        }
    }

    public void changePersonParams(String id, String paramLine) {
        personHelper.updatePerson(id, paramLine);
    }

    public void saveFileparams(String path, String params, String saveOrNot, String format) {
        switch (format) {
            case "CSV":
                csvHelper.savePersonToFile(path, params, saveOrNot);
            case "XML":
                xmlHelper.savePersonToFile(path, params, saveOrNot);
            case "JSON":
                jsonHelper.savePersonToFile(path, params, saveOrNot);

        }

    }

    public void takePersonFromFile(String path, String format) {
        switch (format.toUpperCase()) {
            case "CSV":
                csvHelper.takePersonFromFile(path);
            case "XML":
                xmlHelper.takePersonFromFile(path);
            case "JSON":
                jsonHelper.takePersonFromFile(path);
        }
    }

    public void delete(String path) {
        if (new File(path + ".csv").delete()) {
        }
    }


}