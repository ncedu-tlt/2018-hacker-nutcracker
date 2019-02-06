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

@WebServlet("/DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {

	private Controller controller = new Controller();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		controller.deletePerson(Integer.parseInt(request.getParameter("id")));

		ArrayList<Person> persons = controller.getAllPerson();
		request.setAttribute("listOfPersons", persons);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Main.jsp");
		requestDispatcher.forward(request, response);

	}
}