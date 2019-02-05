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

@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {

	private Controller controller = new Controller();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = new Person();
		person.setId(Integer.parseInt(request.getParameter("id")));

		if (controller.getPerson(person.getId()).getId()!=0){
			request.setAttribute("exception", "id");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Exception.jsp");
			requestDispatcher.forward(request, response);
		} else {

			String option = request.getParameter("radio");
			//Если выбрано текстовое поле (option1) и оно пустое - то проверяем значение из списка, если и оно пустое, то exception
			//Если выбрано значение из списка (option2) и оно пустое - то проверяем текстовое, если и оно пустое, то exception
			if (option.equals("option1")){
				if ((request.getParameter("way").trim()).equals("")){
				 	if (request.getParameter("selectWay").equals("NoWay")){
						request.setAttribute("exception", "way");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Exception.jsp");
						requestDispatcher.forward(request, response);
					} else {
						person.setWay(request.getParameter("selectWay"));
					}
				} else {
					person.setWay(request.getParameter("way").trim());
				}
			} else if (request.getParameter("selectWay").equals("NoWay")) {
				if ((request.getParameter("way").trim()).equals("")){
					request.setAttribute("exception", "way");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Exception.jsp");
					requestDispatcher.forward(request, response);
				} else {
					person.setWay(request.getParameter("way").trim());
				}
			} else {
				person.setWay(request.getParameter("selectWay"));
			}

			person.setName(request.getParameter("name"));
			person.setUSD(Integer.parseInt(request.getParameter("usd")));
			controller.createPerson(person);

			ArrayList<Person> persons = controller.getAllPerson();
			request.setAttribute("listOfPersons", persons);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Main.jsp");
			requestDispatcher.forward(request, response);
		}

	}
}
