package com.netcracker.edu.servlets;

import com.netcracker.edu.Person;
import com.netcracker.edu.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet("/DownloadFileServlet")
@MultipartConfig
public class DownloadFileServlet extends HttpServlet {

	private Controller controller = new Controller();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();

		if (!fileName.endsWith(".xml")){
			request.setAttribute("exception", "namefile");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Exception.jsp");
			requestDispatcher.forward(request, response);
		}
		int mark;
		String filetext = "";
		Person person = new Person();

		while ((mark = fileContent.read()) != -1) {
			filetext += (char) mark;
		}

		String[] word = filetext.split("USD");
		StringBuffer sb = new StringBuffer(word[1]);
		sb.delete(0,1);
		int lenght = sb.length();
		sb.delete(lenght-2,lenght);
		person.setUSD(Integer.parseInt(sb.toString()));

		word = filetext.split("id");
		sb = new StringBuffer(word[1]);
		sb.delete(0,1);
		lenght = sb.length();
		sb.delete(lenght-2,lenght);
		person.setId(Integer.parseInt(sb.toString()));

		word = filetext.split("name");
		sb = new StringBuffer(word[1]);
		sb.delete(0,1);
		lenght = sb.length();
		sb.delete(lenght-2,lenght);
		person.setName(sb.toString());

		word = filetext.split("way");
		sb = new StringBuffer(word[1]);
		sb.delete(0,1);
		lenght = sb.length();
		sb.delete(lenght-2,lenght);
		person.setWay(sb.toString());

		controller.createPerson(person);

		ArrayList<Person> persons = controller.getAllPerson();
		request.setAttribute("listOfPersons", persons);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Main.jsp");
		requestDispatcher.forward(request, response);
	}
}