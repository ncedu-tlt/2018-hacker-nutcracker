package com.netcracker.edu.servlets;

import com.netcracker.edu.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/PersonPageServlet")
public class PersonPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = new Person();
		person.setId(Integer.parseInt(request.getParameter("id")));
		person.setName(request.getParameter("name"));
		person.setWay(request.getParameter("way"));
		person.setUSD(Integer.parseInt(request.getParameter("usd")));

		request.setAttribute("Person", person);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PersonPage");
		requestDispatcher.forward(request, response);

	}
}
