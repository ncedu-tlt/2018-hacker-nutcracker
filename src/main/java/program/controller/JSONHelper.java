package program.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import program.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JSONHelper {

	private static JSONHelper jsonHelper;

	private JSONHelper(){}

	public static JSONHelper getInstance(){
		if (jsonHelper == null){
			jsonHelper = new JSONHelper();
		}
		return jsonHelper;
	}

	public void saveJSON(String filePath, String paramLine, String saveOrNot)  {

		switch (saveOrNot.trim()){
			case "n":
				if(new File(filePath+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}

		String[] params;
		params = paramLine.split(",");
		Person person = new Person(Integer.parseInt(params[0]),params[1],params[2],Integer.parseInt(params[3]));

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			if (filePath.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(filePath).reverse().toString();

				rev =rev.replaceFirst("vsc","nosj");
				filePath = new StringBuffer(rev).reverse().toString();
			}

			mapper.writeValue(new File(filePath),person);

		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
