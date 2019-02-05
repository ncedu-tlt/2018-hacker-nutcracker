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

@WebServlet("/UpdatePersonServlet")
public class UpdatePersonServlet extends HttpServlet {

	private Controller controller = new Controller();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = new Person();
		String option = request.getParameter("radio");

		//Если выбрано текстовое поле (option1) и оно пустое - то записываем значение из списка
		//Если не пустое, то значение из текстового поля. Если выбрано значение из списка (option2) то записываем его
		if (option.equals("option1")){
			if((request.getParameter("way").trim()).equals("")){
				person.setWay(request.getParameter("selectWay").trim());
			} else {
				person.setWay(request.getParameter("way").trim());
			}
		} else { person.setWay(request.getParameter("selectWay").trim());
		}

		person.setId(Integer.parseInt(request.getParameter("id")));
		person.setName(request.getParameter("name"));
		person.setUSD(Integer.parseInt(request.getParameter("usd")));

		controller.changePerson(person);

		ArrayList<Person> persons = controller.getAllPerson();
		request.setAttribute("listOfPersons", persons);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Main.jsp");
		requestDispatcher.forward(request, response);

	}
}
