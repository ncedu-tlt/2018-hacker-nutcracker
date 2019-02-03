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

@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {
	private Controller controller = new Controller();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = new Person();
		person.setId(Integer.parseInt(request.getParameter("id")));
		person.setName(request.getParameter("name"));
		person.setWay(request.getParameter("way"));
		person.setUSD(Integer.parseInt(request.getParameter("usd")));

		if (controller.getPerson(person.getId()).getId()!=0){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Exception");
			requestDispatcher.forward(request, response);
		}else {
			controller.createPerson(person);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Navigation");
			requestDispatcher.forward(request, response);
		}

	}
}
