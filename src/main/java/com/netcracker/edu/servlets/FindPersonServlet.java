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
		switch (request.getParameter("field")){
			case "id":{
				for(Person person : list){
					if(person.getId()==Integer.parseInt(request.getParameter("criterion"))) listResult.add(person);
				}
			}break;
			case "name":{
				for(Person person : list){
					if(person.getName().equals(request.getParameter("criterion"))) listResult.add(person);
				}
			}break;
			case "way":{
				for(Person person : list){
					if(person.getWay().equals(request.getParameter("criterion"))) listResult.add(person);
				}
			}break;
			case "usd":{
				for(Person person : list){
					if(person.getUSD()==Integer.parseInt(request.getParameter("criterion"))) listResult.add(person);
				}
			}
		}

		request.setAttribute("listOfPersons", listResult);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Find");
		requestDispatcher.forward(request, response);
	}
}
