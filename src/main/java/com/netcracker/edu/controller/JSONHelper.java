package com.netcracker.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.Person;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class JSONHelper extends AbstractController {

	public void createFileJSON(Person person) {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(filesys.getHomeDirectory()+"\\"+person.getName()+".json"), person);
		}catch (IOException e) {e.printStackTrace();}
	}
}