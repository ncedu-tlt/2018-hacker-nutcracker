package com.netcracker.edu.servlets;

import com.netcracker.edu.Person;
import com.netcracker.edu.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SavePersonServlet")
public class SavePersonServlet extends HttpServlet {

	private Controller controller = new Controller();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = new Person();
		person.setId(Integer.parseInt(request.getParameter("id")));
		person.setName(request.getParameter("name"));
		person.setWay(request.getParameter("way"));
		person.setUSD(Integer.parseInt(request.getParameter("usd")));

		String format = request.getParameter("format");
		controller.savePersonInFile(person, format);

		ArrayList<Person> persons = controller.getAllPerson();
		request.setAttribute("listOfPersons", persons);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Main.jsp");
		requestDispatcher.forward(request, response);
	}
}