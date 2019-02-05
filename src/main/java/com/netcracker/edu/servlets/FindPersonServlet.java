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

@WebServlet("/FindPersonServlet")
public class FindPersonServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Controller controller = new Controller();
		ArrayList<Person> list = controller.getAllPerson();
		ArrayList<Person> listResult = new ArrayList<>();

		String criterion = request.getParameter("criterion");

		for (Person person: list) {
			String str = person.toString();
			Integer index = str.lastIndexOf(criterion);
			if (index != -1) {
				listResult.add(person);
			}
		}

		request.setAttribute("listOfPersons", listResult);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/FindPersons.jsp");
		requestDispatcher.forward(request, response);
	}
}
